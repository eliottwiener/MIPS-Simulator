import java.util.List;

public class Main {

	/**
	 * Entry point for program
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Read mem from program file
		List<Long> rawMemory = ProgramReader.getMemory(args[0]);
		
		Memory memory = new Memory(rawMemory);
		
		// Initialize the 5 MUX's
		Mux aluSrcMux = new Mux();
		Mux memToRegMux = new Mux();
		Mux regDstMux = new Mux();
		Mux jumpMux = new Mux();
		Mux branchMux = new Mux();
		Mux jumpRegMux = new Mux();
		
		// Initialize the 2 SLTs
	 	ShiftLeftTwo sltTarget = new ShiftLeftTwo(28);
		ShiftLeftTwo sltAdd = new ShiftLeftTwo(32);
		
		// Initialize the 3 ALUs
		ALU alu = new ALU();
		ALU aluAdd = new ALU();
		ALU aluP4 = new ALU();
		// set the static controls/inputs for certain ALU
		aluAdd.control.setValue((long)2);
		aluP4.control.setValue((long)2);
		aluP4.input2.setValue((long)4);
		
		// initalize Memory IO
		MemoryIO memoryIo = new MemoryIO(memory);
		
		// initialize main control and ALU control
		Control control = new Control();
		ALUControl aluControl = new ALUControl();
		
		// initialize the register file
		RegisterFile regFile = new RegisterFile();
		
		// initialize fetch and decode stages
		Fetch fetch = new Fetch(memory);
		Decode decode = new Decode();

		// initialize the AND going to branch MUX
		And branchMuxAnd = new And();
		
		// initialize the combiner (jump addr + (PC + 4))
		Combiner combiner = new Combiner();
		
		// initialize the sign-extend
		SignExtend signExtend = new SignExtend();
		
		// initialize the inverter
		Inverter inv = new Inverter();
		
		// initialize the Program Counter
		ProgramCounter pc = new ProgramCounter();
		
		// initialize the Pipeline Registers
		IFID ifid = new IFID();
		IDEX idex = new IDEX();
		EXMEM exmem = new EXMEM();
		MEMWB memwb = new MEMWB();
		
		// For debugging
		Debugger debug = new Debugger(regFile, decode, pc, control, aluControl,
				memoryIo, alu, aluP4, aluAdd, regDstMux, memToRegMux, aluSrcMux, 
				branchMux, jumpMux, signExtend, sltAdd, sltTarget, jumpRegMux,
				inv);
		DebuggerPR pipelineDebug = new DebuggerPR(ifid, idex, exmem, memwb);
		
		// Connect output of PC
		pc.pcOut.connectTo(fetch.pc);
		pc.pcOut.connectTo(aluP4.input1);
		
		// Connect output of fetch
		fetch.instr.connectTo(ifid.instruction);
		
		// Connect the outputs of the decode
		decode.opcode.connectTo(control.opcode);
		decode.rs.connectTo(regFile.readReg1);
		decode.rt.connectTo(regFile.readReg2);
		decode.rt.connectTo(regDstMux.input0);
		decode.rd.connectTo(regDstMux.input1);
		decode.target.connectTo(sltTarget.in);
		decode.immediate.connectTo(signExtend.input);
		decode.funct.connectTo(aluControl.func);
		decode.funct.connectTo(control.funct);
		
		// connect the outputs of sign-extend
		signExtend.output.connectTo(idex.signExtended);
		
		// Connect outputs of regFile
		regFile.readData1.connectTo(idex.readData1);
		regFile.readData2.connectTo(idex.readData2);
				
		// Connect outputs of ALU and related
		aluSrcMux.output.connectTo(alu.input2);
		alu.result.connectTo(memoryIo.address);
		alu.result.connectTo(exmem.genALUResult);
		alu.zero.connectTo(exmem.zero);
		inv.out.connectTo(branchMuxAnd.input1);
		aluP4.result.connectTo(ifid.PC4);
		aluP4.result.connectTo(branchMux.input0);
		aluP4.result.connectTo(combiner.pcIn);
		aluAdd.result.connectTo(exmem.addALUresult);
		
		// Connect output of memoryIo
		memoryIo.readData.connectTo(memwb.readData);
		
		// Connect the outputs of SLTs
		sltTarget.out.connectTo(combiner.jumpAddr);
		sltAdd.out.connectTo(aluAdd.input2);
		
		// connect output of combiner
		combiner.out.connectTo(jumpMux.input1);
		
		memToRegMux.output.connectTo(regFile.writeData);
		regDstMux.output.connectTo(regFile.writeReg);
		branchMuxAnd.output.connectTo(branchMux.switcher);
		branchMux.output.connectTo(jumpMux.input0);
		jumpMux.output.connectTo(jumpRegMux.input0);
		jumpRegMux.output.connectTo(pc.pcIn);
		combiner.out.connectTo(jumpMux.input1);
		
		// connect the control signals
		control.regDst.connectTo(regDstMux.switcher);
		control.jump.connectTo(jumpMux.switcher);
		control.branch.connectTo(branchMuxAnd.input0);
		control.memRead.connectTo(memoryIo.memRead);
		control.memToReg.connectTo(memToRegMux.switcher);
		control.aluOp.connectTo(aluControl.aluOp);
		control.memWrite.connectTo(memoryIo.memWrite);
		control.aluSrc.connectTo(aluSrcMux.switcher);
		control.regWrite.connectTo(regFile.regWrite);
		control.jumpReg.connectTo(jumpRegMux.switcher);
		control.branchBNE.connectTo(inv.branchBNE);
		aluControl.aluControl.connectTo(alu.control);
		
		// connect the outputs of the pipeline registers
		ifid.outInstr.connectTo(decode.instruction);
		ifid.outPC4.connectTo(idex.PC4);
		idex.outPC4.connectTo(aluAdd.input1);
		idex.outReadData1.connectTo(alu.input1);
		idex.outReadData1.connectTo(jumpRegMux.input1);
		idex.outReadData2.connectTo(aluSrcMux.input0);
		idex.outReadData2.connectTo(exmem.readData2);
		idex.outSignExtended.connectTo(sltAdd.in);
		idex.outSignExtended.connectTo(aluSrcMux.input1);
		exmem.outAddALUresult.connectTo(branchMux.input1);
		exmem.outGenALUresult.connectTo(memoryIo.address);
		exmem.outGenALUresult.connectTo(memToRegMux.input0);
		exmem.outReadData2.connectTo(memoryIo.writeData);
		exmem.outZero.connectTo(inv.in);
		memwb.outGenALUresult.connectTo(memToRegMux.input1);
		memwb.outReadData.connectTo(memToRegMux.input1);
		
		pc.pcIn.setValue(Long.parseLong("1000",16));
		int cycleCount = 0;
		for(;;){
			try{
				
				// increase the cycle count
				cycleCount ++;

				// send PC to fetch object
				pc.clockEdge();
				
				// fetch the instruction
				fetch.clockEdge();
				
				// clock the P4 ALU (increment the PC)
				aluP4.clockEdge();
				
				ifid.clockEdge();
				
				// decode the instruction
				decode.clockEdge();
				if(decode.isHalt()){
					// terminate when we see a "HLT" instruction
					break;
				}
				
				// clock the sign-extend
				signExtend.clockEdge();
				
				
				// clock the regfile
				// we do this before clocking the control
				// because if regWrite ends up being a 1, we won't have
				// write data until this cycle is finished. So we clock 
				// regFile first in order to handle the read registers
				regFile.clockEdge();
				
				// clock the SLT
				sltTarget.clockEdge();
				
				idex.clockEdge();
				
				sltAdd.clockEdge();

				// clock the Add ALU
				aluAdd.clockEdge();
				
				// set the output control signals
				control.setSignals();
				
				// set ALU control signals
				aluControl.update();
								
				
				// clock the RegDST Mux
				regDstMux.clockEdge();
				
				// clock the ALUSrc Mux
				aluSrcMux.clockEdge();
				
				// clock the ALU
				alu.clockEdge();
				
				exmem.clockEdge();
				
				// clock the Inverter
				inv.clockEdge();
				
				// clock the AND
				branchMuxAnd.clockEdge();
				
				// clock the Memory IO
				memoryIo.clockEdge();
				
				memwb.clockEdge();
				
				// clock the combiner
				combiner.clockEdge();
				
				// clock the Branch Mux
				branchMux.clockEdge();
	
				// clock the memToRegMux
				memToRegMux.clockEdge();
	
				// clock the jump Mux
				jumpMux.clockEdge();
				
				// clock the jump register Mux
				jumpRegMux.clockEdge();
				
				// clock the regFile
				regFile.clockEdge();
				
				// Add this cycle to the debug stream
				debug.debugCycle(cycleCount);
				pipelineDebug.debugCycle(cycleCount);
			
			}
			
			catch (Exception e){
				e.printStackTrace();
				System.out.println("Error occured at Cycle: " + cycleCount);
				try{
					debug.debugCycle(cycleCount);
					pipelineDebug.debugCycle(cycleCount);
				}catch (Exception f){
					break;
				}
				break;
			}
				

		}
		
		debug.dump("debug.txt");
		pipelineDebug.dump("pipelineDebug.txt");
		int instrCount = cycleCount;
		System.out.println("Execution Complete!\n");
		System.out.println("The total number of cycles: " + cycleCount);
		System.out.println("The total number of instructions executed: " + instrCount);
		System.out.println("CPI: " + (new Float(cycleCount/instrCount)));
		System.out.println("Final Register Values:");
		String output = "Register\tBinary\t\t\t  Hexadecimal\tDecimal\n";
		for(int i = 0 ; i < 32 ; i++){
			Long thisVal = regFile.getVal(i);
			//print out register values in binary
			output+= "$r" + i +":\t" + BinaryUtil.pad(Long.toBinaryString(thisVal), 32)
			+ "\t0x" + Long.toHexString(thisVal) + "\t" + thisVal + "\n";
		}
		System.out.println(output);
	}

}
