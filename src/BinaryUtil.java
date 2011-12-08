public class BinaryUtil {
	
	public static final String pad(final String s, final int numBits){
		return chomp(extend(s)).substring(32-numBits);
	}
	
	public static final String stringValue(final Long n){
		String s = Long.toBinaryString(n);
		s = extend(s);
		s = chomp(s);
		return s;
	}
	
	public static final long getBits(final long n, final int from, final int to){
		String s = stringValue(n);
		return Long.parseLong(s.substring(31-from, 31-to),2);
	}
	
	//chop off all but the rightmost 32 bits
	private static final String chomp(final String s){
		final int len = s.length();
		if(len > 32){
			return s.substring(len-32);
		} else {
			return s;
		}
	}
	
	//sign extend
	private static final String extend(final String s){
		final int len = s.length();
		if(len < 32){
			String p = s;
			for(int i = 0 ; i < 32-len ; i++){
				p = "0" + p;
			}
			return s.substring(len-32);
		} else {
			return s;
		}
	}
	
}
