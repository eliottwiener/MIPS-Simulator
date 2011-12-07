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
		Long idex_rs_val = idex_rs.getValue();
		Long idex_rt_val = idex_rt.getValue();
		Long exmem_rd_val = exmem_rd.getValue();
		Long exmem_regWrite_val = exmem_regWrite.getValue();
		Long memwb_rd_val = memwb_rd.getValue();
		Long memwb_regWrite_val = memwb_regWrite.getValue();
		
		// (page 367 & 369)
		// EX hazard
		if(exmem_regWrite_val.equals((long)1)
				&& !(exmem_rd_val.equals((long)0))
				&& exmem_rd_val.equals((long)idex_rs_val)){
			forwardA.setValue((long)2);
		} else forwardA.setValue((long)0);
		if(exmem_regWrite_val.equals((long)1)
				&& !(exmem_rd_val.equals((long)0))
				&& exmem_rd_val.equals((long)idex_rt_val)){
			forwardB.setValue((long)2);
		} else forwardB.setValue((long)0);
		
		// MEM hazard
		if(memwb_regWrite_val.equals((long)1)
				&& !(memwb_rd_val.equals((long)0))
				&& !(exmem_regWrite_val.equals((long)1)
						&& !(exmem_rd_val.equals((long)0))
						&& !(exmem_rd_val.equals((long)idex_rs_val)))
				&& memwb_rd_val.equals((long)idex_rs_val)){
			forwardA.setValue((long)1);
		} else forwardA.setValue((long)0);
		if(memwb_regWrite_val.equals((long)1)
				&& !(memwb_rd_val.equals((long)0))
				&& !(exmem_regWrite_val.equals((long)1)
						&& !(exmem_rd_val.equals((long)0))
						&& !(exmem_rd_val.equals((long)idex_rt_val)))
				&& memwb_rd_val.equals((long)idex_rt_val)){
			forwardB.setValue((long)1);
		} else forwardB.setValue((long)0);
	}
}