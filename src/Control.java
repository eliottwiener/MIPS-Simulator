public class Control{
	public String opcode;
	public long output;
	
	long jump;
	long regDst;
	long branch;
	long memRead;
	long memToReg;
	long aluOp;
	long memWrite;
	long aluSrc;
	long regWrite;
	
	public Control(long input){
		this.opcode = Long.toBinaryString(input);
	}
	
	public void setSignals(){
		
		// R-Type instructions
		if(opcode.equals("000000")){
			jump = 0;
			regDst = 1;
			branch = 0;
			memRead = 0;
			memToReg = 0;
			aluOp = 2;
			memWrite = 0;
			aluSrc = 0;
			regWrite = 1;
		}
		
		// LW instruction
		if(opcode.equals("100011")){
			jump = 0;
			regDst = 0;
			branch = 0;
			memRead = 1;
			memToReg = 1;
			aluOp = 0;
			memWrite = 0;
			aluSrc = 1;
			regWrite = 1;
		}
		
		// SW instruction
		if(opcode.equals("101011")){
			jump = 0;
			regDst = 0;
			branch = 0;
			memRead = 0;
			memToReg = 0;
			aluOp = 0;
			memWrite = 1;
			aluSrc = 1;
			regWrite = 0;
		}
		
		// branch instructions
		if(opcode.equals("000100") || opcode.equals("000101")){
			jump = 0;
			regDst = 0;
			branch = 1;
			memRead = 0;
			memToReg = 0;
			aluOp = 1;
			memWrite = 0;
			aluSrc = 1;
			regWrite = 0;
		}
		
		// jump instruction
		if(opcode.equals("000010")){
			jump = 1;
			regDst = 0;
			branch = 0;
			memRead = 0;
			memToReg = 0;
			aluOp = 0;
			memWrite = 0;
			aluSrc = 1;
			regWrite = 0;
		}
		
		// I-type instructions
		// addi, andi, ori, slti
		if(opcode.equals("001000") ||
				opcode.equals("001100") ||
				opcode.equals("001101") ||
				opcode.equals("001010")){
			jump = 0;
			regDst = 0;
			branch = 0;
			memRead = 0;
			memToReg = 0;
			aluOp = 0;
			memWrite = 0;
			aluSrc = 1;
			regWrite = 1;
		}
	}
}