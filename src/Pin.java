
public class Pin {
	private Binary value;
	
	Pin(){}
	
	public final void setValue(final Binary b){
		this.value = b;
	}
	
	public final Binary getValue(){
		return this.value;
	}
}
