public class Control{
	public String opcode;
	public long output;
	
	public Pin jump = new Pin();
	public Pin regDst = new Pin();
	public Pin branch = new Pin();
	public Pin memRead = new Pin();
	public Pin memToReg = new Pin();
	public Pin aluOp = new Pin();
	public Pin memWrite = new Pin();
	public Pin aluSrc = new Pin();
	public Pin regWrite = new Pin();
	
	
	public Control(){}
	
	public void setSignals(long input){
		
		this.opcode = Long.toBinaryString(input);
		
		// R-Type instructions
		if(opcode.equals("000000")){
			jump.setValue((long)0);
			regDst.setValue((long)1);
			branch.setValue((long)0);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)2);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)0);
			regWrite.setValue((long)1);
		}
		
		// LW instruction
		if(opcode.equals("100011")){
			jump.setValue((long)0);
			regDst.setValue((long)0);
			branch.setValue((long)0);
			memRead.setValue((long)1);
			memToReg.setValue((long)1);
			aluOp.setValue((long)0);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)1);
			regWrite.setValue((long)1);
		}
		
		// SW instruction
		if(opcode.equals("101011")){
			jump.setValue((long)0);
			regDst.setValue((long)0);
			branch.setValue((long)0);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)0);
			memWrite.setValue((long)1);
			aluSrc.setValue((long)1);
			regWrite.setValue((long)0);
		}
		
		// branch instructions
		if(opcode.equals("000100") || opcode.equals("000101")){
			jump.setValue((long)0);
			regDst.setValue((long)0);
			branch.setValue((long)1);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)1);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)1);
			regWrite.setValue((long)0);
		}
		
		// jump instruction
		if(opcode.equals("000010")){
			jump.setValue((long)1);
			regDst.setValue((long)0);
			branch.setValue((long)0);
			memRead.setValue((long)0);
			memToReg.setValue((long)0);
			aluOp.setValue((long)0);
			memWrite.setValue((long)0);
			aluSrc.setValue((long)1);
			regWrite.setValue((long)0);
		}
		
		// I-type instructions
		// addi, andi, ori, slti
		if(opcode.equals("001000") ||
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
		}
	}
}