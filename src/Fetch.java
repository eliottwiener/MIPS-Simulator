public class Fetch implements Clockable{
	
	//index of the program counter
	public Pin pc = new Pin();
	public Pin instr = new Pin(); 
	
	// the complete set of instructions
	private final Memory mem;
	
	// initialize program counter to zero
	public Fetch(final Memory mem){
		this.mem = mem;
	}
	
	public void clockEdge(){
		instr.setValue(mem.loadWord(pc.getValue()));
		System.out.println("[DEBUG] Class:Fetch (Post-Fetch), instr:" + 
		BinaryUtil.pad(Long.toBinaryString(instr.getValue()), 32) + " pc:" + 
				Long.toHexString(pc.getValue()));
	}
}