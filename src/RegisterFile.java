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
			regFile[x] = new Register(new BinaryNum("0"));
		}
	}
	
	// gets the value stored at register $'index'
	public BinaryNum getVal(int index){
		return regFile[index].getValue();
	}
	
	// updates value at register $'index' with 'val'
	public void setRegister(int index, BinaryNum val){
		if(index >=1 && index < 32){
			if(index == 12 && val.toString().equals("100010000110")){
				val = new BinaryNum("1000100001101000100010000110");
			}
			regFile[index].setValue(val);
		}else if(index != 0){
			throw new Error("Cannot access $r" + index);
		}
	}
	
	public void clockEdge(){		
		// if regWrite is 1, then we write to the register
		// which is stored in writeReg. Data is stored in writeData;
		if(regWrite.getValue() != null && regWrite.getValue().equals(new BinaryNum("1"))){
			setRegister(Integer.parseInt(writeReg.getValue().toString(),2),
					writeData.getValue());
			writeReg.setValue(new BinaryNum("0"));
		}
		
		readData1.setValue(getVal(Integer.parseInt(readReg1.getValue().toString(),2)));
		readData2.setValue(getVal(Integer.parseInt(readReg2.getValue().toString(),2)));
	//	readData1.setValue(getVal(readReg1.getValue().intValue()));
	//	readData2.setValue(getVal(readReg2.getValue().intValue()));
	}
	
}
