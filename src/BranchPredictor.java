public class BranchPredictor{
	
	// control signals
	public Pin branchCleared = new Pin();
	public Pin branchBNE = new Pin();
	public Pin branch = new Pin();
	// inputs
	public Pin readData1 = new Pin();
	public Pin readData2 = new Pin();
	public Pin instruction = new Pin();
	//output
	public Pin flush = new Pin();
	
	
	public BranchPredictor(){
		branchCleared.setValue(new BinaryNum("0"));
		branchBNE.setValue(new BinaryNum("0"));
		branch.setValue(new BinaryNum("0"));
		readData1.setValue(new BinaryNum("0").pad(32));
		readData2.setValue(new BinaryNum("0").pad(32));
		instruction.setValue(new BinaryNum("0").pad(32));
		flush.setValue(new BinaryNum("0"));
	}
	
	public boolean isNop(){
		return instruction.getValue().equals(new BinaryNum("0").pad(32));
	}
	
	public void clockEdge(){
		
		// The case where 
		// flush is set to 1, but we received a signal
		// saying that the branch is no longer taken.
		// So we set flush ands set branchTaken to 0
		if(flush.getValue().equals(new BinaryNum("1")) &&
				branchCleared.getValue().equals(new BinaryNum("1"))){
			flush.setValue(new BinaryNum("0"));
			branchCleared.setValue(new BinaryNum("0"));
		}
		
		// The case where 
		// flush is set to 1, and the branchCleared
		// signal tells us that the branch is still taken
		// So we keep flush as 1
		if(flush.getValue().equals(new BinaryNum("1")) &&
				branchCleared.getValue().equals(new BinaryNum("0"))){
			flush.setValue(new BinaryNum("1"));
		}
		
		// The case where
		// flush is set to zero
		// determine if we need to set
		// flush/branchTaken to 1
		if(flush.getValue().equals(new BinaryNum("0"))){
				
			BinaryNum f = null;
			String output;
			f = readData1.getValue().sub(readData2.getValue());
			
			if(f.equals(new BinaryNum("0").pad(32))){
				output = "1";
			}else{
				output = "0";
			}
			
			if(branchBNE.getValue().equals(new BinaryNum("1"))){
				if(output.equals("1")){
					output = "0";
				}else output = "1";
			}
			
			// control signal is branch, and result is true
			// so we flush and set branch taken to 1
			if(branch.getValue().equals(new BinaryNum("1")) && 
					output.equals("1")){
				flush.setValue(new BinaryNum("1"));
			} else {
				flush.setValue(new BinaryNum("0"));
			}
		}
		
		
		
		
	}
}