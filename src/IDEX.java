public class IDEX implements Clockable{
	// inputs
	public Pin PC4 = new Pin();
	public Pin readData1 = new Pin();
	public Pin readData2 = new Pin();
	public Pin signExtended = new Pin();
	// outputs
	public Pin outPC4 = new Pin();
	public Pin outReadData1 = new Pin();
	public Pin outReadData2 = new Pin();
	public Pin outSignExtended = new Pin();
	public void clockEdge(){
		outPC4.setValue(PC4.getValue());
		outReadData1.setValue(readData1.getValue());
		outReadData2.setValue(readData2.getValue());
		outSignExtended.setValue(signExtended.getValue());
	}
}