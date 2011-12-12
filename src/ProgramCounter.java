
public class ProgramCounter implements Clockable{
	Pin pcIn = new Pin();
	Pin pcOut = new Pin();
	Pin control = new Pin();
	int instrCount = 0;
	
	public ProgramCounter(){
		pcIn.setValue(new BinaryNum("0").pad(32));
		pcOut.setValue(new BinaryNum("0").pad(32));
		control.setValue(new BinaryNum("0"));
	}

	public void clockEdge() {
		if(new BinaryNum("0").equals(control.getValue())){
			instrCount++;
			pcOut.setValue(pcIn.getValue());
		}
	}
}
