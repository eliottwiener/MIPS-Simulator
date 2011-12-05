public class BinaryUtil {
	public static final String pad(String s, int numBytes){
		if(s.length() > numBytes){
			throw new RuntimeException("Attempt to pad out long string that is too many bits: " + s);
		} else {
			return String.format("%" + numBytes + "s", s).replace(" ", "0");
		}
	}
	
}
