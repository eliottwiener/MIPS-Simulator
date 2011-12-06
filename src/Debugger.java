import java.io.*;

public class Debugger{
	
	public RegisterFile regFile;
	public String output = "";
	
	public Debugger(RegisterFile regFile){
		this.regFile = regFile;
	}
	
	public void debugCycle(int cycleCount){
		output += "----- End of Cycle " + cycleCount +" -----\n";
		output += "Register\t\tBinary Value\t\tHex Value\t\tLong Value\n";
		for(int i = 0 ; i < 32 ; i++){
			Long thisVal = regFile.getVal(i);
			//print out register values in binary
			output+= "$r" + i +":\t" + BinaryUtil.pad(Long.toBinaryString(thisVal), 32)
			+ "\t0x" + Long.toHexString(thisVal) + "\t" + thisVal + "\n";
		}
		output += "\n\n";
	}
	
	public void dump(String fileName){
		try{
			// Create file 
			FileWriter fstream = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(output);
			//Close the output stream
			out.close();
			}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
			}
		}
}