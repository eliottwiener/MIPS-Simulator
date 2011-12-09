
public class And implements Clockable {
	
	public final Pin output = new Pin(); 
	public final Pin input0 = new Pin();
	public final Pin input1 = new Pin();
	
	And(){}
	
	public void clockEdge() {
		if(input0.getValue().equals(new BinaryNum("1")) && 
				input1.getValue().equals(new BinaryNum("1"))){
			output.setValue(new BinaryNum("1"));
		} else {
			output.setValue(new BinaryNum("0"));
		}
	}

}
