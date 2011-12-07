public class Decode{
	// the instruction to decode
	public Pin instruction = new Pin();
	
	// op code of instruction[31:26]
	public Pin opcode = new Pin();
	
	// rs field of instruction[25:21]
	// read register 1 of RegFile
	public Pin rs = new Pin();
	
	// rt field of instruction[20:16]
	// read register 2 of RegFile
	public Pin rt = new Pin();
	
	// rd field of instruction[15:11]
	// sent to MUX to determine Write Register of RegFile
	public Pin rd = new Pin();
	
	// funct field of instruction[5:0]
	// sent to ALU control for R-Type instructions
	public Pin funct = new Pin();
	
	// 16-bit offset for branch, equal, load, and store
	// sent to sign-extend
	public Pin immediate = new Pin();
	
	// 25-bit for target of jump
	public Pin target = new Pin();


	// 0 for R-Type, 1 for I-Type, 2 for J-Type, 3 for HLT
	// -1 for unrecognized opcode
	public int opType = -1;
	
	// creates this object with an instruction from fetch stage
	Decode(){}
	
	// takes first 6 bits as opcode
	public void setOpcode(String instr){
		opcode.setValue(Long.parseLong(instr.substring(0,6), 2));
	}
	
	// takes next 5 bits as rs value
	public void setRS(String instr){
		rs.setValue(Long.parseLong(instr.substring(6,11), 2));
	}
	
	// takes next 5 bits as rt value
	public void setRT(String instr){
		rt.setValue(Long.parseLong(instr.substring(11,16), 2));
		
	}
	
	// sets the rd and func value if r-type
	public void setRD(String instr){
		rd.setValue(Long.parseLong(instr.substring(16,21), 2));
	}
	
	public void setFunct(String instr){
		// funct field is last 6 bits if not I-Type
		if(opType == 1){
			String strOp = BinaryUtil.pad(Long.toBinaryString(opcode.getValue()),6);
			// and instruction
			if(strOp.equals("001000")){
				funct.setValue(Long.parseLong("100000",2));
			}
			
			// andi instruction
			if(strOp.equals("001100")){
				funct.setValue(Long.parseLong("100100",2));
			}
			
			// ori instruction
			if(strOp.equals("001101")){
				funct.setValue(Long.parseLong("100101",2));
			}
			
			// slti instruction
			if(strOp.equals("001010")){
				funct.setValue(Long.parseLong("101010",2));
			}
		}
		else{
			funct.setValue(Long.parseLong(instr.substring(26,32), 2));
		}	
		
	}
	
	// sets the offset value for i-type and j-type
	public void setImmediate(String instr){
		immediate.setValue(Long.parseLong(instr.substring(16,32), 2));
	}
	
	// set jump target for j-type
	public void setTarget(String instr){
		target.setValue(Long.parseLong(instr.substring(6,32), 2));
	}
	
	// determines if this instruction is R-Type, I-Type, or J-Type
	public void setOpType(String op){
		
		// R-Type instructions all have opcode as 000000
		if(op.equals("000000")){
			opType = 0;
		}
		
		// I-Type instruction are any opcodes other than
		// 000000, 00001x, 0100xx, and 111111
		if(!(op.equals("000000") ||
				op.equals("000010") ||
				op.equals("000011") ||
				op.equals("010000") ||
				op.equals("010001") ||
				op.equals("010011") ||
				op.equals("010010") ||
				op.equals("111111"))){
			opType = 1;
		}
		
		// J-type instructions have opcodes of 00001x
		if(op.equals("000010") || op.equals("000011")){
			opType = 2;
		}
		
		// HLT's opcode is 111111
		if(op.equals("111111")){
			opType = 3;
		}
		
		// throw an error for unsupported opcode
		if(opType == -1){
			throw new Error("Unsupported opcode: " + op);
		}
	}
	
	// function that returns True if this instruction is a halt.
	public boolean isHalt(){
		return opType == 3;
	}
	
	
	// Decodes the instruction
	public void clockEdge(){		
		// converts the instruction into a binary String
		String instrStr = BinaryUtil.pad(Long.toBinaryString(instruction.getValue()),32);
		
		// decode the instruction
		setOpcode(instrStr);
		setOpType(BinaryUtil.pad(Long.toBinaryString(opcode.getValue()),6));
		setRS(instrStr);
		setRT(instrStr);
		setTarget(instrStr);
		setRD(instrStr);
		setFunct(instrStr);
		setImmediate(instrStr);							  
	}
}