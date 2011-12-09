public class Mux3 implements Clockable{
	
	public final Pin output = new Pin(); 	
	public final Pin switcher = new Pin();
	public final Pin input0 = new Pin();
	public final Pin input1 = new Pin();
	public final Pin input2 = new Pin();
	
	Mux3(){}
	
	public void clockEdge() {
		if(switcher.getValue().equals(new BinaryNum("0"))){
			output.setValue(input0.getValue());
		}
		else if(switcher.getValue().equals(new BinaryNum("1"))){
			output.setValue(input1.getValue());
		}
		else if(switcher.getValue().equals(new BinaryNum("2"))){
			output.setValue(input2.getValue());
		}
	}
}
