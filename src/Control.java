public class Control{
	
	// each control output is a Pin object
	public Pin opcode = new Pin();
	public Pin jump = new Pin();
	public Pin regDst = new Pin();
	public Pin branch = new Pin();
	public Pin memRead = new Pin();
	public Pin memToReg = new Pin();
	public Pin aluOp = new Pin();
	public Pin memWrite = new Pin();
	public Pin aluSrc = new Pin();
	public Pin regWrite = new Pin();
	public Pin branchOp1 = new Pin();
	public Pin branchOp2 = new Pin();
	
	
	public Control(){}
	
	// Set the control signals depending on the opcode
	public void setSignals(){
		
		String myOpcode = BinaryUtil.pad(Long.toBinaryString(opcode.getValue()), 6);
		// R-Type instructions
		if(myOpcode.equals("000000")){
			jump.setValue((long)0);
			regDst.setValue((long)1);
			branch.setValue((long)0);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)2);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)0);
			regWrite.setValue((long)1);
			branchOp1.setValue((long)0);
			branchOp2.setValue((long)0);
		}
		
		// LW instruction
		if(myOpcode.equals("100011")){
			jump.setValue((long)0);
			regDst.setValue((long)0);
			branch.setValue((long)0);
			memRead.setValue((long)1);
			memToReg.setValue((long)1);
			aluOp.setValue((long)0);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)1);
			regWrite.setValue((long)1);
			branchOp1.setValue((long)0);
			branchOp2.setValue((long)0);
		}
		
		// SW instruction
		if(myOpcode.equals("101011")){
			jump.setValue((long)0);
			regDst.setValue((long)0);
			branch.setValue((long)0);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)0);
			memWrite.setValue((long)1);
			aluSrc.setValue((long)1);
			regWrite.setValue((long)0);
			branchOp1.setValue((long)0);
			branchOp2.setValue((long)0);
		}
		
		// beq
		if(myOpcode.equals("000100")){
			jump.setValue((long)0);
			regDst.setValue((long)0);
			branch.setValue((long)1);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)1);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)0);
			regWrite.setValue((long)0);
			branchOp1.setValue((long)1);
			branchOp2.setValue((long)0);
		}
		
		// bne
		if(myOpcode.equals("000101")){
			jump.setValue((long)0);
			regDst.setValue((long)0);
			branch.setValue((long)1);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)1);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)0);
			regWrite.setValue((long)0);
			branchOp1.setValue((long)1);
			branchOp2.setValue((long)1);
		}
		
		// jump instruction
		if(myOpcode.equals("000010")){
			jump.setValue((long)1);
			regDst.setValue((long)0);
			branch.setValue((long)0);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)0);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)1);
			regWrite.setValue((long)0);
			branchOp1.setValue((long)0);
			branchOp2.setValue((long)0);
		}
		
		
		// I-type instructions
		// addi, andi, ori, slti
		if(myOpcode.equals("001000") ||
				opcode.equals("001100") ||
				opcode.equals("001101") ||
				opcode.equals("001010")){
			jump.setValue((long)0);
			regDst.setValue((long)0);
			branch.setValue((long)0);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)0);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)1);
			regWrite.setValue((long)1);
			branchOp1.setValue((long)0);
			branchOp2.setValue((long)0);
		}
		
	}
}