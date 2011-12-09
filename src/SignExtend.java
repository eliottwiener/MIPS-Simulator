public class SignExtend implements Clockable{
	public Pin input = new Pin();
	public Pin output = new Pin();
	public Pin immediate = new Pin();
	
	public SignExtend(){}
	
	
	public void clockEdge(){
		//if(immediate.getValue().equals((long)1)){
			String s = BinaryUtil.stringValue(input.getValue());
			output.setValue(Long.parseLong(s,2));
		//}
	}
	
}