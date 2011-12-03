
public class ALU implements Clockable{

	Pin input1;
	
	Pin input2;
	
	Pin control;
	
	Pin result;
	
	Pin zero;
	
	@Override
	public void clockEdge() {
		Long f = null;
		Long a = input1.getValue();
		Long b = input2.getValue();
		if(control.equals(0)){
			f = a & b;
		} else if(control.equals(1)){
			f = a ^ b;
		} else if(control.equals(2)){
			f = a+b;
		} else if(control.equals(6)){
			f = a-b;
		} else if(control.equals(7)){
			f = (long) ((a < b) ? 1 : 0);
		} else if(control.equals(12)){
			f = ~(a^b);
		} else {
			throw new RuntimeException("Unhandled ALU control: " + Long.toBinaryString(control.getValue()));
		}
		if(f==0) zero.setValue((long)1);
		result.setValue(f);
	}
}
