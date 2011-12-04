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
		Memory instrMemory = new Memory(firstInstrAddr,firstInstrAddr+numInstr-1);
		Iterator<Long> it = instructions.iterator();
		while(it.hasNext()){
			Long instr = it.next();
			instrMemory.storeWord(firstInstrAddr + instructions.indexOf(instr), instr);
		}
		
		//initialize data memory
		Memory dataMemory = new Memory(Long.parseLong("0", 16),Long.parseLong("ffc", 16));
		
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
		MemoryIO memoryIo = new MemoryIO(dataMemory);
		Control control = new Control();
		RegisterFile regFile = new RegisterFile();
		Fetch fetch = new Fetch(instrMemory);
		And branchMuxAnd = new And();
		
		// connect output of ALUs
		alu.result.connectTo(memoryIo.address);
		alu.result.connectTo(memToRegMux.input0);
		alu.zero.connectTo(branchMuxAnd.input1);
		aluAdd.result.connectTo(branchMux.input1);
		
		// connect output of Muxes
		memToRegMux.output.connectTo(regFile.writeData);
		regDstMux.output.connectTo(regFile.writeReg);
		jumpMux.output.connectTo(fetch.PC);
		aluSrcMux.output.connectTo(alu.input2);
		branchMux.output.connectTo(jumpMux.input0);
		
		// connect output of SLT's
		slt1.out.connectTo(jumpMux.input1);
		slt2.out.connectTo(aluAdd.input2);
		
		// connect the control signal outputs from Control and ALU Control
		control.regDst.connectTo(regDstMux.switcher);
		control.jump.connectTo(jumpMux.switcher);
		control.branch.connectTo(branchMuxAnd.input0);
		control.memRead.connectTo(memoryIo.memRead);
		control.memToReg.connectTo(memToRegMux.input1);
		control.aluOp.connectTo(aluControl.aluOp);
		control.memWrite.connectTo(memoryIo.memWrite);
		control.aluSrc.connectTo(aluSrcMux.switcher);
		control.regWrite.connectTo(regFile.regWrite);
		aluControl.aluControl.connectTo(alu.control);
		
		// connect the register file outputs
		regFile.readData1.connectTo(alu.input1);
		regFile.readData2.connectTo(aluSrcMux.input0);
		regFile.readData2.connectTo(memoryIo.writeData);
		
		// connect data memory output
		memoryIo.readData.connectTo(memToRegMux.input1);
		
		// connect output of AND
		branchMuxAnd.output.connectTo(branchMux.switcher);
		
		// connect PC to Add ALU
		fetch.PC.connectTo(aluAdd.input1);
		
		fetch.PC.setValue(Long.parseLong("1000", 16));
		for(Long instruction : instructions){
			// fetch instruction and increment PC
			fetch.clockEdge();
			
			// create decode object with this instruction
			Decode decode = new Decode(fetch.getInstruction());
			
			// Connect the outputs of the decode
			decode.opcode.connectTo(control.opcode);
			decode.rs.connectTo(regFile.readReg1);
			decode.rt.connectTo(regFile.readReg2);
			decode.rt.connectTo(regDstMux.input0);
			decode.rd.connectTo(regDstMux.input1);
			decode.target.connectTo(slt1.in);
			decode.offset.connectTo(slt2.in);
			decode.funct.connectTo(aluControl.func);
			// decode the instruction
			decode.clockEdge();
			
			// set the output control signals
			control.setSignals();
			
			// clock the mux that takes regDST as switcher
			regDstMux.clockEdge();
			
			// clock the regfile
			regFile.clockEdge();
		}
	}

}
