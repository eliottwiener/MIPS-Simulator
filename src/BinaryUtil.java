
public class BinaryUtil {
	public static final String pad(String s){
		int len = s.length();
		if(len == 32){
			return s;
		} else if(len > 32){
			throw new RuntimeException("Attempt to pad out long string that is too many bits: " + s);
		} else {
			for(int z = 32 - len ; z > 0 ; z--){
				s = "0" + s;
			}
			return s;
		}
	}
}
