public class Register{
	
	private long value;
	private long nextVal;
	
	Register(long value) {
		this.value = value;
	}
	
	public long getValue(){
		return value;
	}
	
	public void setValue(long v){
		nextVal = v;
	}
	
	public void update(){
		value = nextVal;
	}
	
}