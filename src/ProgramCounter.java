
public class ProgramCounter implements Clockable{
	Pin pcIn = new Pin();
	Splitter pcOut = new Splitter(2);
	
	public ProgramCounter(){}

	public void clockEdge() {
		pcOut.setValue(pcIn.getValue());
	}
}
