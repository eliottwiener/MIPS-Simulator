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
	public void setOpcode(BinaryNum instr){
		opcode.setValue(instr.getRange(31,26));
	}

	// takes next 5 bits as rs value
	public void setRS(BinaryNum instr){
		rs.setValue(instr.getRange(25,21));
	}

	// takes next 5 bits as rt value
	public void setRT(BinaryNum instr){
		rt.setValue(instr.getRange(20,16));
	}

	// sets the rd and func value if r-type
	public void setRD(BinaryNum instr){
		rd.setValue(instr.getRange(15,11));
	}

	public void setFunct(BinaryNum instr){
		// funct field is last 6 bits if not I-Type
		if(opType == 1){
			BinaryNum thisOp = opcode.getValue();
			// and instruction
			if(thisOp.equals(new BinaryNum("001000"))){
				funct.setValue(new BinaryNum("100000"));
			}

			// andi instruction
			if(thisOp.equals(new BinaryNum("001100"))){
				funct.setValue(new BinaryNum("100100"));
			}

			// ori instruction
			if(thisOp.equals(new BinaryNum("001101"))){
				funct.setValue(new BinaryNum("100101"));
			}

			// slti instruction
			if(thisOp.equals(new BinaryNum("001010"))){
				funct.setValue(new BinaryNum("101010"));
			}
		}
		else{
			funct.setValue(instr.getRange(5, 0));
		}	

	}

	// sets the offset value for i-type and j-type
	public void setImmediate(BinaryNum instr){
		immediate.setValue(instr.getRange(15,0));
	}

	// set jump target for j-type
	public void setTarget(BinaryNum instr){
		target.setValue(instr.getRange(25,0));
	}

	// determines if this instruction is R-Type, I-Type, or J-Type
	public void setOpType(){

		BinaryNum op = opcode.getValue();
		
		// R-Type instructions all have opcode as 000000
		if(op.equals(new BinaryNum("000000"))){
			opType = 0;
		}

		// I-Type instruction are any opcodes other than
		// 000000, 00001x, 0100xx, and 111111
		if(!(op.equals(new BinaryNum("000000")) ||
				op.equals(new BinaryNum("000010")) ||
				op.equals(new BinaryNum("000011")) ||
				op.equals(new BinaryNum("010000")) ||
				op.equals(new BinaryNum("010001")) ||
				op.equals(new BinaryNum("010011")) ||
				op.equals(new BinaryNum("010010")) ||
				op.equals(new BinaryNum("111111")))){
			opType = 1;
		}

		// J-type instructions have opcodes of 00001x
		if(op.equals(new BinaryNum("000010")) || 
				op.equals(new BinaryNum("000011"))){
			opType = 2;
		}

		// HLT's opcode is 111111
		if(op.equals(new BinaryNum("111111"))){
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
		BinaryNum instrbin = instruction.getValue();
		// decode the instruction
		setOpcode(instrbin);
		setOpType();
		setRS(instrbin);
		setRT(instrbin);
		setTarget(instrbin);
		setRD(instrbin);
		setFunct(instrbin);
		setImmediate(instrbin);
		
		
	}
}