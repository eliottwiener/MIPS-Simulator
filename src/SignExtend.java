public class SignExtend implements Clockable{
	public Pin input = new Pin();
	public Pin output = new Pin();
	
	public SignExtend(){
		input.setValue(new BinaryNum("0").pad(16));
		output.setValue(new BinaryNum("0").pad(32));
	}
	
	
	public void clockEdge(){
		output.setValue(input.getValue().extend(32));
	}

	
}