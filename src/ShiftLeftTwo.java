
public class ShiftLeftTwo implements Clockable{
	Pin in = new Pin();
	Pin out = new Pin();
	
	ShiftLeftTwo(){}

	public void clockEdge() {
		out.setValue(in.getValue()<<2);
		System.out.println("[DEBUG] Class: ShiftLeftTwo" + 
						  "\nin:" + BinaryUtil.pad(Long.toBinaryString(in.getValue()), 32) +
						  "\nout:" + BinaryUtil.pad(Long.toBinaryString(out.getValue()), 32));
	}
	
	
}
