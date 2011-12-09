public class Register{

	private BinaryNum value;
	
	Register(BinaryNum value) {
		this.value = value;
	}

	public BinaryNum getValue(){
		return value;
	}

	public void setValue(BinaryNum v){
		value = v;
	}

}