
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
			for(int i = length; i > 0 ; i--){
				if(i > bits.length ){
					newBits[i] = bits[bits.length-1];
				} else {
					newBits[i] = bits[i];
				}
			}
			return new BinaryNum(newBits);
		}
	}
	
	public final BinaryNum twosComplement(){
		return this;
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
	
	public final BinaryNum add(final BinaryNum other){
		if(other.length() < bits.length){
			return this.add(other.extend(bits.length));
		} else if(bits.length < other.length()){
			return this.extend(other.length()).add(other);
		} else {
			boolean carry = false;
			boolean[] newBits = new boolean[bits.length];
			for(int i = 0 ; i < bits.length ; i++){
				newBits[i] = bits[i] ^ other.getBits()[i];
				carry = bits[i] && other.getBits()[i];
			}
			return new BinaryNum(newBits);
		}
		
		
	}
	
	public final BinaryNum nor(BinaryNum other){
		return this.or(other).not();
	}
	
	public final String toString(){
		String s = "0b";
		for(int i = 0 ; i < bits.length;i++){
			if(bits[i]){
				s += "1";
			} else {
				s += "0";
			}
		}
		return s;
	}
	
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

}
