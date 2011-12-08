public class SignExtend implements Clockable{
	public Pin input = new Pin();
	public Pin output = new Pin();
	public Pin immediate = new Pin();
	
	public SignExtend(){}
	
	
	public void clockEdge(){
		String s = BinaryUtil.pad(Long.toBinaryString(input.getValue()),16);
		String strOut;
		if(s.length() > 16){
			throw new RuntimeException("Attempt to sign-extend long string that is > 32 bits: ");
		}else {
			if(immediate.getValue().equals((long)1)){
				char msb = s.charAt(0);
				if(msb == '0'){
					strOut = BinaryUtil.pad(s,32);
				}else{
					strOut = BinaryUtil.pad(s,31);
					strOut = "1" + strOut;
				}
			}
			else strOut = BinaryUtil.pad(s,32);
			
			output.setValue(Long.parseLong(strOut,2));
		}
	}

	
}