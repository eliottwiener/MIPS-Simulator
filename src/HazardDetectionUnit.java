public class HazardDetectionUnit implements Clockable{
	public Pin idex_memRead = new Pin();
	public Pin idex_rt = new Pin();
	public Pin ifid_rs = new Pin();
	public Pin ifid_rt = new Pin();
	public Pin output = new Pin();
	
	public HazardDetectionUnit(){
		output.setValue(new BinaryNum("0"));
		idex_memRead.setValue(new BinaryNum("0"));
		idex_rt.setValue(new BinaryNum("0").pad(5));
		ifid_rs.setValue(new BinaryNum("0").pad(5));
		ifid_rt.setValue(new BinaryNum("0").pad(5));
	}
	public void clockEdge(){
		if(new BinaryNum("1").equals(idex_memRead.getValue())
				&& (idex_rt.getValue().equals(ifid_rs.getValue())
						|| idex_rt.getValue().equals(ifid_rt.getValue()))){
			// stalls the pipeline
			System.out.println("Hazard here stall pipeline");
			output.setValue(new BinaryNum("1"));
		}else output.setValue(new BinaryNum("0"));
				
	}
}