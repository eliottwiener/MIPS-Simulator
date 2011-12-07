public class BinaryUtil {
	public static final String pad(String s, int numBytes){
		// used for debug purposes
		if(s.equals("null")){
			return "null";
		}
		
		if(s.length() > numBytes){
			throw new RuntimeException("Attempt to pad out long string that is too many bits: " + s);
		} else {
			return String.format("%" + numBytes + "s", s).replace(" ", "0");
		}
	}
	
	public static final String signExtend(String s, int numBytes){
		if(s.length() > numBytes){
			throw new RuntimeException("Attempt to sign-extend long string that is too many bits: " + s);
		}else {
			// pads with the most significant bit
			String msb=s.substring(0, 1);
			return String.format("%" + numBytes + "s", s).replace(" ", msb);
		}
	}
	
}
