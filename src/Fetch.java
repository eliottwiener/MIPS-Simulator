public class Fetch implements Clockable{
	
	//index of the program counter
	public long PC;
	
	// the complete set of instructions
	public Memory instructionSet;
		
	// the instruction to fetch
	public long instruction;
	
	// initialize program counter to zero
	public Fetch(Memory instructionSet){
		this.instructionSet = instructionSet;
		PC = 0;
	}
	
	public void setInstruction(){
		instruction = instructionSet.loadWord(PC);
	}
	
	public long getInstruction(){
		return instruction;
	}
	
	public void clockEdge(){
		setInstruction();
		PC++;
	}
}