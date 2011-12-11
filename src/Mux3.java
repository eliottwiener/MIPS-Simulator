public class Mux3 implements Clockable{
	
	public final Pin output = new Pin(); 	
	public final Pin switcher = new Pin();
	public final Pin input0 = new Pin();
	public final Pin input1 = new Pin();
	public final Pin input2 = new Pin();
	
	Mux3(){
		output.setValue(new BinaryNum("0").pad(32));
		switcher.setValue(new BinaryNum("0").pad(32));
		input0.setValue(new BinaryNum("0").pad(32));
		input1.setValue(new BinaryNum("0").pad(32));
		input2.setValue(new BinaryNum("0").pad(32));

	}
	
	public void clockEdge() {
		if(switcher.getValue().equals(new BinaryNum("00"))){
			output.setValue(input0.getValue());
		}
		else if(switcher.getValue().equals(new BinaryNum("01"))){
			output.setValue(input1.getValue());
		}
		else if(switcher.getValue().equals(new BinaryNum("10"))){
			output.setValue(input2.getValue());
		}
	}
}
