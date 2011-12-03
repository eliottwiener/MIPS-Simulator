import java.util.*;

public class Fetch implements Clockable{
	
	//index of the program counter
	public long PC;
	
	// the instruction to fetch
	public long instruction;
	
	// initialize program counter to zero
	public Fetch(){
		PC = 0;
	}
	
	// fetch the instruction from instruction memory
	public void doFetch(HashMap<Long,Long> mem){	
		instruction = mem.get(PC);
		PC++;
	}
	
	public long getInstruction(){
		return instruction;
	}
	
	public void clockEdge(){
		// Todo
	}
}