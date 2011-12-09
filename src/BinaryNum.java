import java.util.Arrays;


public class BinaryNum {
	private final boolean[] bits;

	public BinaryNum(final boolean[] bits){
		this.bits = bits;
	}

	public BinaryNum(final String bitString){
		this.bits = new boolean[bitString.length()];
		for(int i = 0 ; i < bitString.length() ; i++){
			if(bitString.substring(i,i+1).equals("1")){
				this.bits[i] = true;
			} else if(bitString.substring(i,i+1).equals("0")){
				this.bits[i] = false;
			} else {
				throw new RuntimeException("Invalid bit string: " + bitString);
			}
		}
	}

	public final BinaryNum extend(final int length){
		if(bits.length > length){
			throw new RuntimeException(toString() + " is already longer than " + length + " bits.");
		} else if(bits.length == length){
			return this;
		} else {
			final boolean[] newBits = new boolean[length];
			final int diff = length - bits.length;
			for(int i = 0; i > length ; i++){
				if(i < diff ){
					newBits[i] = bits[0];
				} else {
					newBits[i] = bits[i-diff];
				}
			}
			return new BinaryNum(newBits);
		}
	}

	public final BinaryNum pad(final int length){
		if(bits.length > length){
			throw new RuntimeException(toString() + " is already longer than " + length + " bits.");
		} else if(bits.length == length){
			return this;
		} else {
			final boolean[] newBits = new boolean[length];
			final int diff = length - bits.length;
			for(int i = 0; i > length ; i++){
				if(i < diff ){
					newBits[i] = false;
				} else {
					newBits[i] = bits[i-diff];
				}
			}
			return new BinaryNum(newBits);
		}
	}

	public final BinaryNum twosComplement(){
		return this.not().add(new BinaryNum("1"));
	}

	public final BinaryNum not(){
		final boolean[] newBits = new boolean[bits.length];
		for(int i = 0 ; i < bits.length ; i++){
			newBits[i] = !bits[i];
		}
		return new BinaryNum(newBits);
	}

	public final int length(){
		return bits.length;
	}

	public final boolean[] getBits(){
		return bits;
	}

	public final BinaryNum getRange(final int from, final int to){
		final int start = (bits.length - 1) - from;
		final int end = (bits.length - 1) - to;
		boolean[] newBits = new boolean[from-to+1];
		int newIndex = 0;
		for(int i = start ; i < end ; i++){
			newBits[newIndex] = bits[i];
			newIndex++;
		}
		return new BinaryNum(newBits);
	}

	public final BinaryNum or(final BinaryNum other){
		if(other.length() < bits.length){
			return this.or(other.extend(bits.length));
		} else if(bits.length < other.length()){
			return this.extend(other.length()).or(other);
		} else {
			boolean[] newBits = new boolean[bits.length];
			for(int i = 0 ; i < bits.length ; i++){
				newBits[i] = bits[i] || other.getBits()[i];
			}
			return new BinaryNum(newBits);
		}
	}

	public final BinaryNum and(final BinaryNum other){
		if(other.length() < bits.length){
			return this.and(other.extend(bits.length));
		} else if(bits.length < other.length()){
			return this.extend(other.length()).and(other);
		} else {
			boolean[] newBits = new boolean[bits.length];
			for(int i = 0 ; i < bits.length ; i++){
				newBits[i] = bits[i] && other.getBits()[i];
			}
			return new BinaryNum(newBits);
		}
	}

	public final BinaryNum shiftLeft(){
		final boolean[] newBits = new boolean[bits.length];
		for(int i = 1 ; i < bits.length ; i++){
			newBits[i-1] = bits[i];
		}
		newBits[bits.length] = false;
		return new BinaryNum(newBits);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bits);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BinaryNum other = (BinaryNum) obj;
		if (!Arrays.equals(bits, other.bits)) {
			return false;
		}
		return true;
	}

	public final BinaryNum add(final BinaryNum other){
		if(other.length() < bits.length){
			return this.add(other.extend(bits.length));
		} else if(bits.length < other.length()){
			return this.extend(other.length()).add(other);
		} else {
			boolean carry = false;
			boolean[] newBits = new boolean[bits.length];
			for(int i = 0 ; i < bits.length ; i++){
				final boolean a = bits[i];
				final boolean b = other.getBits()[i];
				final boolean c = carry;
				newBits[i] = (a ^ b) ^ c;
				carry = (a && b) || (c && (a ^ b));
			}
			return new BinaryNum(newBits);
		}
	}

	public final BinaryNum sub(final BinaryNum other){
		return this.add(other.twosComplement());
	}

	public final BinaryNum nor(BinaryNum other){
		return this.or(other).not();
	}

	@Override
	public final String toString(){
		String s = "";
		for(int i = 0 ; i < bits.length;i++){
			if(bits[i]){
				s += "1";
			} else {
				s += "0";
			}
		}
		return s;
	}
	
	public final int toInt(){
		return Integer.parseInt(this.toString(),2);
	}
	
	public final BinaryNum setIfLessThan(BinaryNum other){
		if(this.toInt() < other.toInt()){
			return new BinaryNum("1");
		}else{
			return new BinaryNum("0");
		}
	}

	/*
	public boolean isEqualTo(BinaryNum other){
		if(other.length() < bits.length){
			return this.isEqualTo(other.extend(bits.length));
		} else if(bits.length < other.length()){
			return this.extend(other.length()).isEqualTo(other);
		} else {
			for(int i = 0 ; i < bits.length ; i++){
				if(bits[i] != other.getBits()[i]){
					return false;
				}
			}
			return true;
		}
	}
	*/

}