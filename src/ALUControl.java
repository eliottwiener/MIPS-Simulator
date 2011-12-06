public class ALUControl {
	public final Pin func = new Pin();
	public final Pin aluOp = new Pin();
	public final Pin aluControl = new Pin();
	
	public ALUControl(){}
	
	public final void update(){
		String funcString = BinaryUtil.pad(Long.toBinaryString(func.getValue()),6);
		
		boolean f0 = funcString.substring(5,6).equals("1");
		boolean f1 = funcString.substring(4,5).equals("1");
		boolean f2 = funcString.substring(3,4).equals("1");
		boolean f3 = funcString.substring(2,3).equals("1");
		
		String aluOpString = BinaryUtil.pad(Long.toBinaryString(aluOp.getValue()),2);
		
		boolean aluOp0 = aluOpString.substring(1,2).equals("1");
		boolean aluOp1 = aluOpString.substring(0,1).equals("1");
		
		boolean aluControl0 = aluOp1 && (f0 || f3);
		boolean aluControl1 = !aluOp1 || !f2;
		boolean aluControl2 = aluOp0 || (aluOp1 && f1);
		String aluControlString = "";
		if(aluControl2){
			aluControlString += "1";
		} else {
			aluControlString += "0";
		}
		if(aluControl1){
			aluControlString += "1";
		} else {
			aluControlString += "0";
		}
		if(aluControl0){
			aluControlString += "1";
		} else {
			aluControlString += "0";
		}
		aluControl.setValue(Long.parseLong(aluControlString,2));
		
	}
}
