public class Control{

	// each control output is a Pin object
	public Pin opcode = new Pin();
	public Pin funct = new Pin();
	public Pin jump = new Pin();
	public Pin regDst = new Pin();
	public Pin branch = new Pin();
	public Pin memRead = new Pin();
	public Pin memToReg = new Pin();
	public Pin aluOp = new Pin();
	public Pin memWrite = new Pin();
	public Pin aluSrc = new Pin();
	public Pin regWrite = new Pin();
	public Pin jumpReg = new Pin();
	public Pin branchBNE = new Pin();
	public Pin branchTakenIn = new Pin();
	public Pin branchTakenOut = new Pin();

	public Control(){
		opcode.setValue(new BinaryNum("00000"));
		funct.setValue(new BinaryNum("000000"));
		jump.setValue(new BinaryNum("0"));
		regDst.setValue(new BinaryNum("0"));
		branch.setValue(new BinaryNum("0"));
		memRead.setValue(new BinaryNum("0"));
		memToReg.setValue(new BinaryNum("0"));
		aluOp.setValue(new BinaryNum("00"));
		memWrite.setValue(new BinaryNum("0"));
		aluSrc.setValue(new BinaryNum("0"));
		regWrite.setValue(new BinaryNum("0"));
		jumpReg.setValue(new BinaryNum("0"));
		branchBNE.setValue(new BinaryNum("0"));
		branchTakenIn.setValue(new BinaryNum("0"));
		branchTakenOut.setValue(new BinaryNum("0"));
	}

	// Set the control signals depending on the opcode
	public void setSignals(){

		String myOpcode = opcode.getValue().toString();
		// R-Type instructions
		if(myOpcode.equals("000000")){
			jump.setValue(new BinaryNum("0"));
			branch.setValue(new BinaryNum("0"));
			memRead.setValue(new BinaryNum("0"));
			memToReg.setValue(new BinaryNum("0"));
			memWrite.setValue(new BinaryNum("0"));
			aluSrc.setValue(new BinaryNum("0"));
			branchBNE.setValue(new BinaryNum("0"));

			String myFunct = funct.getValue().toString();
			if(myFunct.equals("001000")){
				regDst.setValue(new BinaryNum("0"));
				jumpReg.setValue(new BinaryNum("1"));
				aluOp.setValue(new BinaryNum("00"));
				regWrite.setValue(new BinaryNum("0"));
			}else{
				regDst.setValue(new BinaryNum("1"));
				jumpReg.setValue(new BinaryNum("0"));
				aluOp.setValue(new BinaryNum("10"));
				regWrite.setValue(new BinaryNum("1"));

			}
		}

		// LW instruction
		if(myOpcode.equals("100011")){
			jump.setValue(new BinaryNum("0"));
			regDst.setValue(new BinaryNum("0"));
			branch.setValue(new BinaryNum("0"));
			memRead.setValue(new BinaryNum("1"));
			memToReg.setValue(new BinaryNum("1"));
			aluOp.setValue(new BinaryNum("00"));
			memWrite.setValue(new BinaryNum("0"));
			aluSrc.setValue(new BinaryNum("1"));
			regWrite.setValue(new BinaryNum("1"));
			jumpReg.setValue(new BinaryNum("0"));
			branchBNE.setValue(new BinaryNum("0"));
		}

		// SW instruction
		if(myOpcode.equals("101011")){
			jump.setValue(new BinaryNum("0"));
			regDst.setValue(new BinaryNum("0"));
			branch.setValue(new BinaryNum("0"));
			memRead.setValue(new BinaryNum("0"));
			memToReg.setValue(new BinaryNum("0"));
			aluOp.setValue(new BinaryNum("00"));
			memWrite.setValue(new BinaryNum("1"));
			aluSrc.setValue(new BinaryNum("1"));
			regWrite.setValue(new BinaryNum("0"));
			jumpReg.setValue(new BinaryNum("0"));
			branchBNE.setValue(new BinaryNum("0"));
		}

		// beq
		if(myOpcode.equals("000100")){
			jump.setValue(new BinaryNum("0"));
			regDst.setValue(new BinaryNum("0"));
			branch.setValue(new BinaryNum("1"));
			memRead.setValue(new BinaryNum("0"));
			memToReg.setValue(new BinaryNum("0"));
			aluOp.setValue(new BinaryNum("01"));
			memWrite.setValue(new BinaryNum("0"));
			aluSrc.setValue(new BinaryNum("0"));
			regWrite.setValue(new BinaryNum("0"));
			jumpReg.setValue(new BinaryNum("0"));
			branchBNE.setValue(new BinaryNum("0"));
		}

		// bne
		if(myOpcode.equals("000101")){
			jump.setValue(new BinaryNum("0"));
			regDst.setValue(new BinaryNum("0"));
			branch.setValue(new BinaryNum("1"));
			memRead.setValue(new BinaryNum("0"));
			memToReg.setValue(new BinaryNum("0"));
			aluOp.setValue(new BinaryNum("01"));
			memWrite.setValue(new BinaryNum("0"));
			aluSrc.setValue(new BinaryNum("0"));
			regWrite.setValue(new BinaryNum("0"));
			jumpReg.setValue(new BinaryNum("0"));
			branchBNE.setValue(new BinaryNum("1"));
		}

		// jump instruction
		if(myOpcode.equals("000010")){
			jump.setValue(new BinaryNum("1"));
			regDst.setValue(new BinaryNum("0"));
			branch.setValue(new BinaryNum("0"));
			memRead.setValue(new BinaryNum("0"));
			memToReg.setValue(new BinaryNum("0"));
			aluOp.setValue(new BinaryNum("00"));
			memWrite.setValue(new BinaryNum("0"));
			aluSrc.setValue(new BinaryNum("1"));
			regWrite.setValue(new BinaryNum("0"));
			jumpReg.setValue(new BinaryNum("0"));
			branchBNE.setValue(new BinaryNum("0"));
		}


		// I-type instructions
		// addi, andi, ori, slti
		if(myOpcode.equals("001000") ||
				myOpcode.equals("001100") ||
				myOpcode.equals("001101") ||
				myOpcode.equals("001010")){
			jump.setValue(new BinaryNum("0"));
			regDst.setValue(new BinaryNum("0"));
			branch.setValue(new BinaryNum("0"));
			memRead.setValue(new BinaryNum("0"));
			memToReg.setValue(new BinaryNum("0"));
			aluOp.setValue(new BinaryNum("10"));
			memWrite.setValue(new BinaryNum("0"));
			aluSrc.setValue(new BinaryNum("1"));
			regWrite.setValue(new BinaryNum("1"));
			jumpReg.setValue(new BinaryNum("0"));
			branchBNE.setValue(new BinaryNum("0"));
		}
		
		branchTakenOut.setValue(branchTakenIn.getValue());

	}
}