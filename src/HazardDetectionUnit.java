public class HazardDetectionUnit implements Clockable{
	public Pin idex_memRead = new Pin();
	public Pin idex_rt = new Pin();
	public Pin ifid_rs = new Pin();
	public Pin ifid_rt = new Pin();
	public Pin output = new Pin();
	
	public void clockEdge(){
		if(new BinaryNum("1").equals(idex_memRead.getValue())
				&& (idex_rt.getValue().equals(ifid_rs.getValue())
						|| idex_rt.getValue().equals(ifid_rt.getValue()))){
			output.setValue(new BinaryNum("1"));
		}else output.setValue(new BinaryNum("0"));
				
	}
}