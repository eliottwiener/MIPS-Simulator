public class SignExtend implements Clockable{
	
	public Pin input = new Pin();
	public Pin output = new Pin();
	
	public SignExtend(){}
	
	public static final Long uncode(String s){
		if(s.substring(0,1).equals("1")){
			System.out.println(s);
			return -1 * Long.parseLong(s.substring(15),2);
		}else
		{
			return Long.parseLong(s.substring(15),2);
		}
	}
	
	/*
	public void clockEdge(){
		String s = Long.toBinaryString(input.getValue());
		if(s.length() > 32){
			throw new RuntimeException("Attempt to sign-extend long string that is > 32 bits: ");
		}else {
			// pads with the most significant bit
			String msb = BinaryUtil.pad(s,16).substring(0, 1);
			int origLength = s.length();
			s = String.format("%32s", s).replace(" ", "0");
			if(msb.equals("1") && origLength == 16){
				s = "1" + s.substring(1);
			}
			else{
				s = String.format("%32s", s).replace(" ", "0");
			}
				output.setValue(Long.parseLong(s,2));
		}
	}*/
	
	public void clockEdge(){
		String s = BinaryUtil.pad(Long.toBinaryString(input.getValue()),16);
		if(s.length() > 32){
			throw new RuntimeException("Attempt to sign-extend long string that is > 32 bits: ");
		}else {
			// pads with the most significant bit
			String msb = s.substring(0, 1);
			String strOut = String.format("%32s", s).replace(" ", "0");
			output.setValue(Long.parseLong(strOut,2));

		}
	}

	
}