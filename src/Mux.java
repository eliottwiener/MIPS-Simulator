import java.util.ArrayList;


public class Mux implements Clockable{
	
	public final Pin output = new Pin(); 
	
	public final Pin switcher = new Pin();
	
	public final Pin input0 = new Pin();
	
	public final Pin input1 = new Pin();
	
	Mux(){}
	
	@Override
	public void clockEdge() {
		if(switcher.equals(0)){
			output.setValue(input0.getValue());
		} else {
			output.setValue(input1.getValue());
		}
	}
}
