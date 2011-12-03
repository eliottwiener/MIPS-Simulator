
public class And implements Clockable {
	public final Pin output = new Pin(); 
	
	public final Pin input0 = new Pin();
	
	public final Pin input1 = new Pin();
	
	And(){}
	
	@Override
	public void clockEdge() {
		if(input0.getValue().equals(1) && input1.getValue().equals(1)){
			output.setValue((long)1);
		} else {
			output.setValue((long)0);
		}
	}

}
