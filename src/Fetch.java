public class Fetch implements Clockable{
	
	//index of the program counter
	public Pin pc = new Pin();
	public Pin instr = new Pin(); 
	
	// the complete set of instructions
	private final Memory mem;
	
	// initialize program counter to zero
	public Fetch(final Memory mem){
		this.mem = mem;
		pc.setValue(new BinaryNum("0").pad(32));
		instr.setValue(new BinaryNum("0").pad(32));
	}
	
	public void clockEdge(){
		instr.setValue(mem.loadWord(pc.getValue().toLong().intValue()));
	}
}