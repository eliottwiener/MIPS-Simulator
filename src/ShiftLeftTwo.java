
public class ShiftLeftTwo implements Clockable{
	Pin in = new Pin();
	Pin out = new Pin();
	
	ShiftLeftTwo(){}

	@Override
	public void clockEdge() {
		out.setValue(in.getValue()<<2);
	}
	
	
}
