public class EXMEM implements Clockable{
	//inputs
	public Pin addALUresult = new Pin();
	public Pin zero = new Pin();
	public Pin genALUResult = new Pin();
	public Pin readData2 = new Pin();
	public Pin rd = new Pin();
	//outputs
	public Pin outAddALUresult = new Pin();
	public Pin outZero = new Pin();
	public Pin outGenALUresult = new Pin();
	public Pin outReadData2 = new Pin();
	public Pin outRd = new Pin();
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

	public EXMEM(){
	}
	
	public void clockEdge(){
		outAddALUresult.setValue(addALUresult.getValue());
		outZero.setValue(zero.getValue());
		outGenALUresult.setValue(genALUResult.getValue());
		outReadData2.setValue(readData2.getValue());
		outregWrite.setValue(regWrite.getValue());
		outmemToReg.setValue(memToReg.getValue());
		outjump.setValue(jump.getValue());
		outjumpReg.setValue(jumpReg.getValue());
		outmemWrite.setValue(memWrite.getValue());
		outmemRead.setValue(memRead.getValue());
		outbranch.setValue(branch.getValue());
		outRd.setValue(rd.getValue());
	}
}