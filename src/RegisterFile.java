public class RegisterFile implements Clockable{
	
	public Register[] regFile;
	
	public Pin writeReg = new Pin();
	public Pin readReg1 = new Pin();
	public Pin readReg2 = new Pin();	
	public Pin writeData = new Pin();
	public Pin regWrite = new Pin();
	public Pin readData1 = new Pin();
	public Pin readData2 = new Pin();
	
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
	
	public void setReadData1(long val){
		readData1.setValue(val);
	}
	
	public void setReadData2(long val){
		readData2.setValue(val);
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
		setReadData1(readReg1.getValue());
		setReadData2(readReg2.getValue());
		
		if(regWrite.getValue() == 1){
			regFile[writeReg.getValue().intValue()].update();
		}
	}
	
}
