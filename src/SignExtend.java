public class SignExtend implements Clockable{
	public Pin input = new Pin();
	public Pin output = new Pin();
	public Pin immediate = new Pin();
	
	public SignExtend(){}
	
	
	public void clockEdge(){
		output.setValue(input.getValue().extend(32));
	}

	
}