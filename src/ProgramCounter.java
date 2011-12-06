
public class ProgramCounter implements Clockable{
	Pin pcIn = new Pin();
	Pin pcOut = new Pin();
	
	public ProgramCounter(){}

	public void clockEdge() {
		System.out.println("[DEBUG] Class:ProgramCounter" + 
				   "\npcIn:" + BinaryUtil.pad(Long.toBinaryString(pcIn.getValue()),32) +
				   "\npcOut:" + pcOut.getValue());
		pcOut.setValue(pcIn.getValue());
	}
}
