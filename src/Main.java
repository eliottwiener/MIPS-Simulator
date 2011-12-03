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
	}

}
