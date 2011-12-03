public class RegisterFile implements Clockable{
	
	public Register[] regFile;
	
	public Pin writeReg = new Pin();
	
	public Pin readReg1 = new Pin();
	
	public Pin readReg2 = new Pin();
	
	public Pin writeData = new Pin();
	
	public RegisterFile(){
		
		// initializes with 32 words of memory set to 0
		regFile = new Register[32];
		for(int x=0; x<32; x++){
			regFile[x] = new Register(0);
		}
	}
	
	public void setWriteReg(long val){
		writeReg.setValue(val);
	}
	
	public void setRegReg1(long val){
		readReg1.setValue(val);
	}
	
	public void setReadReg2(long val){
		readReg2.setValue(val);
	}
	
	public void setWriteData(long val){
		writeData.setValue(val);
	}
	
	public long getVal(int index){
		return regFile[index].getValue();
	}
	
	public void update(int index, long val){
		if(index >=1 && index < 32){
			regFile[index].setValue(val);
		}
	}
	
	public void clockEdge(){
		for(int x=1; x<32; x++){
			regFile[x].update();
		}
	}
	
}
