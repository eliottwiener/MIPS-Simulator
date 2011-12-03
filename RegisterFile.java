public class RegisterFile implements Clockable{
	
	public Register[] regFile;
	
	public RegisterFile(){
		
		// initializes with 32 words of memory set to 0
		regFile = new Register[32];
		for(int x=0; x<32; x++){
			regFile[x] = new Register(0);
		}
	}
	
	public long getVal(int index){
		return regFile[index].getValue();
	}
	
	public void update(int index, long val){
		if(index >=0 && index < 32){
			regFile[index].setValue(val);
		}
	}
	
	public void clockEdge(){
		for(int x=0; x<32; x++){
			regFile[x].update();
		}
	}
	
}
