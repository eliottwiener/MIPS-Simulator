public class ForwardingUnit implements Clockable{
	public Pin idex_rs = new Pin();
	public Pin idex_rt = new Pin();
	public Pin exmem_rd = new Pin();
	public Pin memwb_rd = new Pin();
	public Pin exmem_regWrite = new Pin();
	public Pin memwb_regWrite = new Pin();
	
	public Pin forwardA = new Pin();
	public Pin forwardB = new Pin();
	public ForwardingUnit(){}
	
	public void clockEdge(){
		BinaryNum idex_rs_val = idex_rs.getValue();
		BinaryNum idex_rt_val = idex_rt.getValue();
		BinaryNum exmem_rd_val = exmem_rd.getValue();
		BinaryNum exmem_regWrite_val = exmem_regWrite.getValue();
		BinaryNum memwb_rd_val = memwb_rd.getValue();
		BinaryNum memwb_regWrite_val = memwb_regWrite.getValue();
		
		// (page 367 & 369)
		// EX hazard
		if(exmem_regWrite_val.equals(new BinaryNum("1"))
				&& !(exmem_rd_val.equals(new BinaryNum("0")))
				&& exmem_rd_val.equals(idex_rs_val)){
			forwardA.setValue(new BinaryNum("10"));
		} else forwardA.setValue(new BinaryNum("0"));
		if(exmem_regWrite_val.equals(new BinaryNum("1"))
				&& !(exmem_rd_val.equals(new BinaryNum("0")))
				&& exmem_rd_val.equals(idex_rt_val)){
			forwardB.setValue(new BinaryNum("10"));
		} else forwardB.setValue(new BinaryNum("0"));
		
		// MEM hazard
		if(memwb_regWrite_val.equals(new BinaryNum("1"))
				&& !(memwb_rd_val.equals(new BinaryNum("0")))
				&& !(exmem_regWrite_val.equals(new BinaryNum("1"))
						&& !(exmem_rd_val.equals(new BinaryNum("0")))
						&& !(exmem_rd_val.equals(idex_rs_val)))
				&& memwb_rd_val.equals(idex_rs_val)){
			forwardA.setValue(new BinaryNum("1"));
		} else forwardA.setValue(new BinaryNum("0"));
		if(memwb_regWrite_val.equals(new BinaryNum("1"))
				&& !(memwb_rd_val.equals(new BinaryNum("0")))
				&& !(exmem_regWrite_val.equals(new BinaryNum("1"))
						&& !(exmem_rd_val.equals(new BinaryNum("0")))
						&& !(exmem_rd_val.equals(idex_rt_val)))
				&& memwb_rd_val.equals(idex_rt_val)){
			forwardB.setValue(new BinaryNum("1"));
		} else forwardB.setValue(new BinaryNum("0"));
	}
}