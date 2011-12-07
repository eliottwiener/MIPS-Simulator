import java.io.BufferedWriter;
import java.io.FileWriter;

public class DebuggerPR{
	
	public IFID ifid;
	public IDEX idex;
	public EXMEM exmem;
	public MEMWB memwb;
	public String output = "";
	
	public DebuggerPR(IFID ifid, IDEX idex, EXMEM exmem, MEMWB memwb){
		this.ifid = ifid;
		this.idex = idex;
		this.exmem = exmem;
		this.memwb = memwb;
	}
	
	public void debugCycle(int cycleCount){

		output += "################################# End of Cycle " + cycleCount +" #################################\n";
		output += "----------------------- IF/ID -----------------------\n";
		output += "PC+4: " + BinaryUtil.pad(print(ifid.PC4),32) + "\t\t" + printDecimal(ifid.PC4) + "\n";
		output += "instruction:" + BinaryUtil.pad(print(ifid.instruction),32) + "\n";
		output += "out PC+4: " + BinaryUtil.pad(print(ifid.outPC4),32) + "\t\t" + printDecimal(ifid.PC4) + "\n";
		output += "out instruction:" + BinaryUtil.pad(print(ifid.outInstr),32) + "\n";
		output += "----------------------- ID/EX -----------------------\n";
		output += "PC+4: " + BinaryUtil.pad(print(idex.PC4),32) + "\t\t" + printDecimal(ifid.PC4) + "\n";
		output += "readData1:" + BinaryUtil.pad(print(idex.readData1), 32) + "\n";
		output += "readData2:" + BinaryUtil.pad(print(idex.readData2), 32) + "\n";
		output += "signExtended:" + BinaryUtil.pad(print(idex.signExtended), 32) + "\n";
		output += "out PC+4: " + BinaryUtil.pad(print(idex.outPC4),32) + "\t\t" + printDecimal(ifid.PC4) + "\n";
		output += "out readData1:" + BinaryUtil.pad(print(idex.outReadData1), 32) + "\n";
		output += "out readData2:" + BinaryUtil.pad(print(idex.outReadData2), 32) + "\n";
		output += "out signExtended:" + BinaryUtil.pad(print(idex.outSignExtended), 32) + "\n";
		output += "------------------=---- EX/MEM -----------------------\n";
		output += "add ALU result:" + BinaryUtil.pad(print(exmem.addALUresult),32) + "\n";
		output += "zero:" + BinaryUtil.pad(print(exmem.zero),1) + "\n";
		output += "general ALU result:" + BinaryUtil.pad(print(exmem.genALUResult), 32) + "\n";
		output += "reaData2:" + BinaryUtil.pad(print(exmem.readData2), 32) + "\n";
		output += "out add ALU result:" + BinaryUtil.pad(print(exmem.outAddALUresult),32) + "\n";
		output += "out zero:" + BinaryUtil.pad(print(exmem.outZero),1) + "\n";
		output += "out general ALU result:" + BinaryUtil.pad(print(exmem.outGenALUresult), 32) + "\n";
		output += "out reaData2:" + BinaryUtil.pad(print(exmem.outReadData2), 32) + "\n";
		output += "----------------------- MEM/WB -----------------------\n";
		output += "readData:" + BinaryUtil.pad(print(memwb.readData),32) + "\n";
		output += "general ALU result:" + BinaryUtil.pad(print(memwb.genALUresult),32) + "\n";
		output += "out readData:" + BinaryUtil.pad(print(memwb.outReadData),32) + "\n";
		output += "out general ALU result:" + BinaryUtil.pad(print(memwb.outGenALUresult),32) + "\n";
	}
	
	public String print(Pin p){
		if(p.getValue() == null){
			return "null";
		}else{
			return Long.toBinaryString(p.getValue());
		}
			
	}
	
	public String printDecimal(Pin p){
		if(p.getValue() == null){
			return "null";
		}else{
			return Long.toString(p.getValue());
		}
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