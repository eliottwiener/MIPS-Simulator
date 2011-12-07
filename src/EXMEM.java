public class EXMEM implements Clockable{
	//inputs
	public Pin addALUresult = new Pin();
	public Pin zero = new Pin();
	public Pin genALUResult = new Pin();
	public Pin readData2 = new Pin();
	//outputs
	public Pin outAddALUresult = new Pin();
	public Pin outZero = new Pin();
	public Pin outGenALUresult = new Pin();
	public Pin outReadData2 = new Pin();
	
	public void clockEdge(){
		outAddALUresult.setValue(addALUresult.getValue());
		outZero.setValue(zero.getValue());
		outGenALUresult.setValue(genALUResult.getValue());
		outReadData2.setValue(readData2.getValue());
	}
}