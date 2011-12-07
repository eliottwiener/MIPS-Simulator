public class SignExtend implements Clockable{
	
	public Pin input = new Pin();
	public Pin output = new Pin();
	
	public SignExtend(){}
	
	public void clockEdge(){
		String s = Long.toBinaryString(input.getValue());
		if(s.length() > 32){
			throw new RuntimeException("Attempt to sign-extend long string that is > 32 bits: ");
		}else {
			// pads with the most significant bit
			String msb = s.substring(0, 1);
			String strOut = String.format("%32s", s).replace(" ", msb);
			output.setValue(Long.parseLong(strOut,2));
		}
	}
	
}