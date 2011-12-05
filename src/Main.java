import java.util.Iterator;
import java.util.List;


public class Main {

	/**
	 * Entry point for program
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Read instructions from program file
		List<Long> instructions = ProgramReader.getInstructions(args[0]);
		
		//load instructions into instruction memory
		Long numInstr = (long) instructions.size();
		Long firstInstrAddr = Long.parseLong("1000", 16);
		Memory instrMemory = new Memory(firstInstrAddr,firstInstrAddr+(numInstr*4)-1);

		for(int i = 0; i < numInstr ; i++){
			instrMemory.storeWord(firstInstrAddr+i*4, instructions.get(i));
		}
		
		
		//initialize data memory
		Memory dataMemory = new Memory(Long.parseLong("0", 16),Long.parseLong("ffc", 16));
		
		//initialize everything
		Mux aluSrcMux = new Mux();
		Mux memToRegMux = new Mux();
		Mux regDstMux = new Mux();
		Mux jumpMux = new Mux();
		Mux branchMux = new Mux();
		ShiftLeftTwo slt1 = new ShiftLeftTwo();
		ShiftLeftTwo slt2 = new ShiftLeftTwo();
		ALUControl aluControl = new ALUControl();
		ALU alu = new ALU();
		ALU aluAdd = new ALU();
		aluAdd.control.setValue((long)2);
		ALU aluP4 = new ALU();
		aluP4.control.setValue((long)2);
		aluP4.input2.setValue((long)4);
		MemoryIO memoryIo = new MemoryIO(dataMemory);
		Control control = new Control();
		RegisterFile regFile = new RegisterFile();
		Fetch fetch = new Fetch(instrMemory);
		And branchMuxAnd = new And();
		ProgramCounter pc = new ProgramCounter();
		Decode decode = new Decode();
		Combiner combiner = new Combiner();
		
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
		decode.offset.connectTo(slt2.in);
		decode.offset.connectTo(aluSrcMux.input1);
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
		
		pc.pcIn.setValue(firstInstrAddr);
		for(Long instruction : instructions){
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
			
			// clock the SLTs
			slt1.clockEdge();
			slt2.clockEdge();
			
			// clock the Add ALU
			aluAdd.clockEdge();

			// set the output control signals
			control.setSignals();
			
			// set ALU control signals
			aluControl.update();
			
			// clock the RegDST Mux
			regDstMux.clockEdge();
			
			// clock the regfile
			regFile.clockEdge();
			
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
		}
		System.out.println("Final register values:");
		for(int i = 0 ; i < 32 ; i++){
			System.out.println("    $r" + i + ": 0x" + Long.toHexString(regFile.getVal(i)));
		}
	}

}
