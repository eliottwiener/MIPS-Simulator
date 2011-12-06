import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		
		//initialize everything
		Mux aluSrcMux = new Mux();
		Mux memToRegMux = new Mux();
		Mux regDstMux = new Mux();
		Mux jumpMux = new Mux();
		Mux branchMux = new Mux();
	 	ShiftLeftTwo slt1 = new ShiftLeftTwo(28);
		ShiftLeftTwo slt2 = new ShiftLeftTwo(32);
		ALUControl aluControl = new ALUControl();
		ALU alu = new ALU();
		ALU aluAdd = new ALU();
		aluAdd.control.setValue((long)2);
		ALU aluP4 = new ALU();
		aluP4.control.setValue((long)2);
		aluP4.input2.setValue((long)4);
		MemoryIO memoryIo = new MemoryIO(memory);
		Control control = new Control();
		RegisterFile regFile = new RegisterFile();
		Fetch fetch = new Fetch(memory);
		And branchMuxAnd = new And();
		ProgramCounter pc = new ProgramCounter();
		Decode decode = new Decode();
		Combiner combiner = new Combiner();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Debugger debug = new Debugger(regFile, decode, pc, control, aluControl,
				memoryIo, alu, regDstMux, memToRegMux, aluSrcMux, branchMux,
				jumpMux);
		
		// Connect output of PC
		pc.pcOut.connectTo(fetch.pc);
		pc.pcOut.connectTo(aluP4.input1);
		
		// Connect output of fetch
		fetch.instr.connectTo(decode.instruction);
		
		// Connect the outputs of the decode
		decode.opcode.connectTo(control.opcode);
		decode.rs.connectTo(regFile.readReg1);
		decode.rt.connectTo(regFile.readReg2);
		decode.rt.connectTo(regDstMux.input0);
		decode.rd.connectTo(regDstMux.input1);
		decode.target.connectTo(slt1.in);
		decode.immediate.connectTo(slt2.in);
		decode.immediate.connectTo(aluSrcMux.input1);
		decode.funct.connectTo(aluControl.func);
		
		// Connect outputs of regFile
		regFile.readData1.connectTo(alu.input1);
		regFile.readData2.connectTo(aluSrcMux.input0);
		regFile.readData2.connectTo(memoryIo.writeData);
		
		// Connect outputs of ALU and related
		aluSrcMux.output.connectTo(alu.input2);
		alu.result.connectTo(memoryIo.address);
		alu.result.connectTo(memToRegMux.input0);
		alu.zero.connectTo(branchMuxAnd.input1);
		aluP4.result.connectTo(aluAdd.input1);
		aluP4.result.connectTo(branchMux.input0);
		aluP4.result.connectTo(combiner.pcIn);
		aluAdd.result.connectTo(branchMux.input1);
		
		// Connect output of memoryIo
		memoryIo.readData.connectTo(memToRegMux.input1);
		
		// Connect the outputs of SLTs
		slt1.out.connectTo(combiner.jumpAddr);
		slt2.out.connectTo(aluAdd.input2);
		
		// connect output of combiner
		combiner.out.connectTo(jumpMux.input1);
		memToRegMux.output.connectTo(regFile.writeData);
		regDstMux.output.connectTo(regFile.writeReg);
		branchMuxAnd.output.connectTo(branchMux.switcher);
		branchMux.output.connectTo(jumpMux.input0);
		jumpMux.output.connectTo(pc.pcIn);
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
		aluControl.aluControl.connectTo(alu.control);
		
		pc.pcIn.setValue(Long.parseLong("1000",16));
		int cycleCount = 1;
		for(;;){
			try{
				// send PC to fetch object
				pc.clockEdge();
				
				// fetch the instruction
				fetch.clockEdge();
				
				// clock the P4 ALU (increment the PC)
				aluP4.clockEdge();
				
				// decode the instruction
				decode.clockEdge();
				if(decode.isHalt()){
					break;
				}
				
				// clock the SLT
				slt1.clockEdge();
				slt2.clockEdge();
				
				// clock the Add ALU
				aluAdd.clockEdge();
				
				// clock the regfile
				// we do this before clocking the control
				// because if regWrite ends up being a 1, we won't have
				// write data until this cycle is finished. So we clock 
				// regFile first in order to handle the read registers
				regFile.clockEdge();
				
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
				
				// clock the AND
				branchMuxAnd.clockEdge();
				
				// clock the Memory IO
				memoryIo.clockEdge();
				
				// clock the combiner
				combiner.clockEdge();
				
				// clock the Branch Mux
				branchMux.clockEdge();
	
				// clock the memToRegMux
				memToRegMux.clockEdge();
	
				// clock the jump Mux
				jumpMux.clockEdge();
				
				// clock the regFile
				regFile.clockEdge();
				
				debug.debugCycle(cycleCount);
				cycleCount++;
		/*		System.out.println(pc.pcOut.getValue());
				System.out.print("next line?");
				try {
					br.readLine();
				} catch (IOException e) {
					//  Auto-generated catch block
					e.printStackTrace();
				}*/
			
				if(cycleCount == 50){
					break;
				}
			}
			
			catch (Exception e){
				e.printStackTrace();
				System.out.println(cycleCount);
				debug.debugCycle(cycleCount);
				break;
			}
				

		}
		
		debug.dump("debug.txt");
//		System.out.println("Final register values:");
//		for(int i = 0 ; i < 32 ; i++){
//			System.out.println("    $r" + i + ": 0x" + Long.toHexString(regFile.getVal(i)));
//		}
	}

}
