public class MEMWB implements Clockable{
	// inputs
	public Pin readData = new Pin();
	public Pin genALUresult = new Pin();
	// outputs
	public Pin outReadData = new Pin();
	public Pin outGenALUresult = new Pin();
	
	public void clockEdge(){
		outReadData.setValue(readData.getValue());
		outGenALUresult.setValue(readData.getValue());
	}
}