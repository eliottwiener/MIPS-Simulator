public class Decode{
	// the instruction to decode
	public long instruction;
	
	// op code of instruction[31:26]
	public long opcode;	
	
	// rs field of instruction[25:21]
	// read register 1 of RegFile
	public long rs;
	
	// rt field of instruction[20:16]
	// read register 2 of RegFile
	public long rt;
	
	// rd field of instruction[15:11]
	// sent to MUX to determine Write Register of RegFile
	public long rd;
	
	// funct field of instruction[5:0]
	// sent to ALU control for R-Type instructions
	public long funct;
	
	// 16-bit offset for branch, equal, load, and store
	// sent to sign-extend
	public long offset;


	// 0 for R-Type, 1 for I-Type, 2 for J-Type
	public int opType;
	
	// creates this object with an instruction from fetch stage
	Decode(Long instruction){
		this.instruction = instruction;
	}
	
	// takes first 6 bits as opcode
	public void setOpcode(String instr){
		opcode = Long.parseLong(instr.substring(0,5));
	}
	
	// takes next 5 bits as rs value
	public void setRS(String instr){
		rs = Long.parseLong(instr.substring(6,10));
	}
	
	// takes next 5 bits as rt value
	public void setRT(String instr){
		rt = Long.parseLong(instr.substring(11,15));
		
	}
	
	// sets the rd and func value if r-type
	public void setRD(String instr){
		rd = Long.parseLong(instr.substring(16,20));
	}
	public void setFunct(String instr){
		funct = Long.parseLong(instr.substring(26,31));
	}
	
	// sets the offset value for i-type and j-type
	public void setOffset(String instr){
		offset = Long.parseLong(instr.substring(16,31));
	}
	
	// determines if this instruction is R-Type, I-Type, or J-Type
	public void setOpType(String op){
		if(op.equals("000000")){
			opType = 0;
			// R-Type instruction
		}
		
		if(!op.equals("000000") &&
				!op.equals("000010") &&
				!op.equals("000011") &&
				!op.equals("010000") &&
				!op.equals("010001") &&
				!op.equals("010011") &&
				!op.equals("010010")){
			opType = 1;
			// I-Type instruction
		}
		
		if(op.equals("000010") || op.equals("000011")){
			opType = 2;
			// J-Type instruction
		}
	}
	
	// accessor methods
	public long getOpCode(){
		return opcode;
	}
	public long getRs(){
		return rs;
	}
	public long getRt(){
		return rt;
	}
	public long getRd(){
		return rd;
	}
	public long getFunct(){
		return funct;
	}
	public long getOffset(){
		return offset;
	}
	
	// Decodes the instruction
	public void clockEdge(){
		String instrStr = Long.toBinaryString(instruction);
		if(instrStr.length() < 32){
			opcode = Long.parseLong(instrStr.substring(0,5));
			setOpType(instrStr.substring(0,5));
			setRS(instrStr);
			setRT(instrStr);
			if(opType == 0){
				setRD(instrStr);
				setFunct(instrStr);
			}
			else setOffset(instrStr);
		}
	}
}