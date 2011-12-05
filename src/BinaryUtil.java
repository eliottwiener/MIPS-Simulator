
public class BinaryUtil {
	public static final String pad(String s){
		if(s.length() > 32){
			throw new RuntimeException("Attempt to pad out long string that is too many bits: " + s);
		} else return String.format("%32s", s).replace(" ", "0");
	}
}
