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
		Mux regFileMux = new Mux();
		ALUControl aluControl = new ALUControl();
		ALU alu = new ALU();
		MemoryIO memoryIo = new MemoryIO(dataMemory);
		Control control = new Control();
		RegisterFile regFile = new RegisterFile();
		Fetch fetch = new Fetch(instrMemory);
		aluSrcMux.output.connectTo(alu.input2);
		aluControl.aluControl.connectTo(alu.control);
		alu.result.connectTo(memoryIo.address);
		alu.result.connectTo(memToRegMux.input0);
		memoryIo.readData.connectTo(memToRegMux.input1);
		memToRegMux.output.connectTo(regFile.writeData);
		regFileMux.output.connectTo(regFile.writeReg);
		
		for(Long instruction : instructions){
			fetch.clockEdge();
		}
	}

}
