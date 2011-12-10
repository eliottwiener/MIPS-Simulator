public class IFID implements Clockable{
	// inputs
	public Pin IFIDWrite = new Pin();
	public Pin PC4 = new Pin();
	public Pin instruction = new Pin();
	//outputs
	public Pin outPC4 = new Pin();
	public Pin outInstr = new Pin();
	
	public IFID(){
	}
	
	public void clockEdge(){
		if(new BinaryNum("0").equals(IFIDWrite.getValue())){
			outPC4.setValue(PC4.getValue());
			outInstr.setValue(instruction.getValue());
		}
	}
	
}