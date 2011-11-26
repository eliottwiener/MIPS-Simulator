
public final class Binary {
	private final String value;
	
	Binary(final String value){
		this.value = value;
	}
	
	public final String getValue(){
		return this.value;
	}
	
	public final int getIntValue(){
		return Integer.parseInt(this.value, 2);
	}
	
	public final int getLength(){
		return this.value.length();
	}
	
	public final Binary getBit(final int i){
		return new Binary(this.value.substring(i, i+1));
	}
}
