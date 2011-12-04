public class Fetch implements Clockable{
	
	//index of the program counter
	public Pin PC = new Pin();
	
	// the complete set of instructions
	public Memory instructionSet;
		
	// the instruction to fetch
	public long instruction;
	
	// initialize program counter to zero
	public Fetch(Memory instructionSet){
		this.instructionSet = instructionSet;
	}
	
	public void setInstruction(){
		instruction = instructionSet.loadWord(PC.getValue());
	}
	
	public long getInstruction(){
		return instruction;
	}
	
	public void clockEdge(){
		setInstruction();
		PC.setValue(PC.getValue() + 1);
	}
}