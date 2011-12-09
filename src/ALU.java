
public class ALU implements Clockable{

	Pin input1 = new Pin();
	Pin input2 = new Pin();
	Pin control = new Pin();	
	Pin immediate = new Pin();
	Pin result = new Pin();
	Pin zero = new Pin();
	
	public ALU(){}
	
	
	public void clockEdge() {
		BinaryNum f = null;
		BinaryNum a = input1.getValue();
		BinaryNum b = input2.getValue();

		if(control.getValue().equals((long)0)){
			// bitwise and
			f = a.and(b);
		} else if(control.getValue().equals((long)1)){
			// bitwise or
			f = a.or(b);
		} else if(control.getValue().equals((long)2)){
			// add
			f = a.add(b);
		} else if(control.getValue().equals((long)6)){
			// subtract
			f = a.sub(b);
		} else if(control.getValue().equals((long)7)){
			// set if less than
			f = a.setIfLessThan(b);
		} else if(control.getValue().equals((long)5)){
			// bitwise nor
			f = a.nor(b);
		}
			else {
			throw new RuntimeException("Unhandled ALU control: " + control.getValue().toString());
		}
		if(f.toString().equals("0")) zero.setValue(new BinaryNum("1"));
		
		result.setValue(f);
	}
}
