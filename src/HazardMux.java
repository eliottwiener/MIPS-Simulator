
public class HazardMux implements Clockable{
	public Pin jumpIn = new Pin();
	public Pin regDstIn = new Pin();
	public Pin branchIn = new Pin();
	public Pin memReadIn = new Pin();
	public Pin memToRegIn = new Pin();
	public Pin aluOpIn = new Pin();
	public Pin memWriteIn = new Pin();
	public Pin aluSrcIn = new Pin();
	public Pin regWriteIn = new Pin();
	public Pin jumpRegIn = new Pin();
	public Pin branchBNEIn = new Pin();
	public Pin immediateIn = new Pin();
	
	public Pin hazard = new Pin();

	// control signals (WB)
	public Pin regWrite = new Pin();
	public Pin memToReg = new Pin();
	// control signals (MEM)
	public Pin jump = new Pin();
	public Pin jumpReg = new Pin();
	public Pin memWrite = new Pin();
	public Pin memRead = new Pin();
	public Pin branch = new Pin();
	// control signals (EX)
	public Pin regDST = new Pin();
	public Pin aluSrc = new Pin();
	public Pin aluOp = new Pin();
	public Pin branchBNE = new Pin();
	public Pin immediate = new Pin();
	
	public void clockEdge() {
		if(hazard.getValue().equals(new BinaryNum("0"))){
			// control signals (WB)
			regWrite.setValue(regWriteIn.getValue());
			memToReg.setValue(memToRegIn.getValue());
			// control signals (MEM)
			jump.setValue(jumpIn.getValue());
			jumpReg.setValue(jumpRegIn.getValue());
			memWrite.setValue(memWriteIn.getValue());
			memRead.setValue(memReadIn.getValue());
			branch.setValue(branchIn.getValue());
			// control signals (EX)
			regDST.setValue(regDstIn.getValue());
			aluSrc.setValue(aluSrcIn.getValue());
			aluOp.setValue(aluOpIn.getValue());
			branchBNE.setValue(branchBNEIn.getValue());
			immediate.setValue(immediateIn.getValue());
		} else {
			regWrite.setValue(new BinaryNum("0"));
			memToReg.setValue(new BinaryNum("0"));
			// control signals (MEM)
			jump.setValue(new BinaryNum("0"));
			jumpReg.setValue(new BinaryNum("0"));
			memWrite.setValue(new BinaryNum("0"));
			memRead.setValue(new BinaryNum("0"));
			branch.setValue(new BinaryNum("0"));
			// control signals (EX)
			regDST.setValue(new BinaryNum("0"));
			aluSrc.setValue(new BinaryNum("0"));
			aluOp.setValue(new BinaryNum("00"));
			branchBNE.setValue(new BinaryNum("0"));
			immediate.setValue(new BinaryNum("0").pad(16));
		}
		
	}
	
}
