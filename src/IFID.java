public class IFID implements Clockable{
	// inputs
	public Pin IFIDWrite = new Pin();
	public Pin PC4 = new Pin();
	public Pin instruction = new Pin();
	public Pin Flush = new Pin();
	//outputs
	public Pin outPC4 = new Pin();
	public Pin outInstr = new Pin();
	
	public IFID(){
		outPC4.setValue(new BinaryNum("0").pad(32));
		outInstr.setValue(new BinaryNum("0").pad(32));
		Flush.setValue(new BinaryNum("0"));
	}
	
	public void clockEdge(){
		if(new BinaryNum("1").equals(Flush.getValue())){
			outInstr.setValue(new BinaryNum("0").pad(32));
		}
		else if(new BinaryNum("0").equals(IFIDWrite.getValue())){
			outPC4.setValue(PC4.getValue());
			outInstr.setValue(instruction.getValue());
		}
	}
	
}