
public class ProgramCounter implements Clockable{
	Pin pcIn = new Pin();
	Pin pcOut = new Pin();
	
	public ProgramCounter(){}

	public void clockEdge() {
		pcOut.setValue(pcIn.getValue());
	}
}
