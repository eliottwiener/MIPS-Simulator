public class IDEX implements Clockable{
	// inputs
	public Pin PC4 = new Pin();
	public Pin readData1 = new Pin();
	public Pin readData2 = new Pin();
	public Pin signExtended = new Pin();
	public Pin rt = new Pin();
	public Pin rd = new Pin();
	public Pin funct = new Pin();
	public Pin rs = new Pin();
	// outputs
	public Pin outPC4 = new Pin();
	public Pin outReadData1 = new Pin();
	public Pin outReadData2 = new Pin();
	public Pin outSignExtended = new Pin();
	public Pin outRt = new Pin();
	public Pin outRd = new Pin();
	public Pin outFunct = new Pin();
	public Pin outRs = new Pin();
	// control signals (WB)
	public Pin regWrite = new Pin();
	public Pin memToReg = new Pin();
	public Pin outregWrite = new Pin();
	public Pin outmemToReg = new Pin();
	// control signals (MEM)
	public Pin jump = new Pin();
	public Pin jumpReg = new Pin();
	public Pin memWrite = new Pin();
	public Pin memRead = new Pin();
	public Pin branch = new Pin();
	public Pin outjump = new Pin();
	public Pin outjumpReg = new Pin();
	public Pin outmemWrite = new Pin();
	public Pin outmemRead = new Pin();
	public Pin outbranch = new Pin();
	// control signals (EX)
	public Pin regDST = new Pin();
	public Pin aluSrc = new Pin();
	public Pin aluOp = new Pin();
	public Pin branchBNE = new Pin();
	public Pin immediate = new Pin();
	public Pin outregDST = new Pin();
	public Pin outaluSrc = new Pin();
	public Pin outaluOp = new Pin();
	public Pin outbranchBNE = new Pin();
	public Pin outimmediate = new Pin();
	
	public void clockEdge(){
		outPC4.setValue(PC4.getValue());
		outReadData1.setValue(readData1.getValue());
		outReadData2.setValue(readData2.getValue());
		outSignExtended.setValue(signExtended.getValue());
		outregWrite.setValue(regWrite.getValue());
		outmemToReg.setValue(memToReg.getValue());
		outjump.setValue(jump.getValue());
		outjumpReg.setValue(jumpReg.getValue());
		outmemWrite.setValue(memWrite.getValue());
		outmemRead.setValue(memRead.getValue());
		outbranch.setValue(branch.getValue());
		outimmediate.setValue(immediate.getValue());
		outregDST.setValue(regDST.getValue());
		outaluSrc.setValue(aluSrc.getValue());
		outaluOp.setValue(aluOp.getValue());
		outbranchBNE.setValue(branchBNE.getValue());
		outRt.setValue(rt.getValue());
		outRd.setValue(rd.getValue());
		outFunct.setValue(funct.getValue());
		outRs.setValue(rs.getValue());
	}
}