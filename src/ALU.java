
public class ALU implements Clockable{

	Pin input1 = new Pin();
	Pin input2 = new Pin();
	Pin control = new Pin();	
	Pin result = new Pin();
	Pin zero = new Pin();
	
	public ALU(){}
	
	// a helper for nor, flips each 1 to a 0 and vice versa
	public String flipBits(Long val){
		String strVal = Long.toBinaryString(val);
		String strOut = "";
		for(int i = 0; i < strVal.length(); i++){
			if(strVal.charAt(i) == '0'){
				strOut += "1";
			}else strOut += "0";
		}
		return strOut;
	}
	
	public void clockEdge() {
		Long f = null;
		Long a = input1.getValue();
		Long b = input2.getValue();

		if(control.getValue().equals((long)0)){
			// bitwise and
			f = a & b;
		} else if(control.getValue().equals((long)1)){
			// bitwise or
			f = a | b;
		} else if(control.getValue().equals((long)2)){
			// add
			f = a+b;
		} else if(control.getValue().equals((long)6)){
			// substract
			f = a-b;
		} else if(control.getValue().equals((long)7)){
			// set if less than
			f = (long) ((a < b) ? 1 : 0);
		} else if(control.getValue().equals((long)5)){
			// bitwise nor
			f = Long.parseLong(flipBits(a | b), 2);
		}
			else {
			throw new RuntimeException("Unhandled ALU control: " + Long.toBinaryString(control.getValue()));
		}
		if(f==0) zero.setValue((long)1);
		result.setValue(f);
	}
}
