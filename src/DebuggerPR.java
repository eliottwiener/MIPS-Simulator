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
		output += "\t\t\tData information\n";
		output += "PC+4 in:" + BinaryUtil.pad(print(idex.PC4),32) + "\t\t" + printDecimal(ifid.PC4) + "\n";
		output += "PC+4 out: " + BinaryUtil.pad(print(idex.outPC4),32) + "\t\t" + printDecimal(ifid.PC4) + "\n";
		output += "readData1 in:" + BinaryUtil.pad(print(idex.readData1), 32) + "\n";
		output += "readData1 out:" + BinaryUtil.pad(print(idex.outReadData1), 32) + "\n";
		output += "readData2 in:" + BinaryUtil.pad(print(idex.readData2), 32) + "\n";
		output += "readData2 out:" + BinaryUtil.pad(print(idex.outReadData2), 32) + "\n";
		output += "signExtended in:" + BinaryUtil.pad(print(idex.signExtended), 32) + "\n";
		output += "signExtended out:" + BinaryUtil.pad(print(idex.outSignExtended), 32) + "\n";
		output += "rt in:" + BinaryUtil.pad(print(idex.rt), 32) + "\n";
		output += "rt out:" + BinaryUtil.pad(print(idex.outRt), 32) + "\n";
		output += "rd in:" + BinaryUtil.pad(print(idex.rd), 32) + "\n";
		output += "rd out:" + BinaryUtil.pad(print(idex.outRd), 32) + "\n";
		output += "funct in:" + BinaryUtil.pad(print(idex.funct), 32) + "\n";
		output += "funct out:" + BinaryUtil.pad(print(idex.outFunct), 32) + "\n";
		output += "rs in:" + BinaryUtil.pad(print(idex.rs), 32) + "\n";
		output += "rs out:" + BinaryUtil.pad(print(idex.outRs), 32) + "\n";
		output += "\t\t\t Control information\n";
		output += "regWrite in:" + BinaryUtil.pad(print(idex.regWrite), 1) + "\n";
		output += "regWrite out:" + BinaryUtil.pad(print(idex.outregWrite), 1) + "\n";
		output += "memToReg in:" + BinaryUtil.pad(print(idex.memToReg), 1) + "\n";
		output += "memToReg out:" + BinaryUtil.pad(print(idex.outmemToReg), 1) + "\n";
		output += "jump in:" + BinaryUtil.pad(print(idex.jump), 1) + "\n";
		output += "jump out:" + BinaryUtil.pad(print(idex.outjump), 1) + "\n";
		output += "jumpReg in:" + BinaryUtil.pad(print(idex.jumpReg), 1) + "\n";
		output += "jumpReg out:" + BinaryUtil.pad(print(idex.outjumpReg), 1) + "\n";
		output += "memWrite in:" + BinaryUtil.pad(print(idex.memWrite), 1) + "\n";
		output += "memWrite out:" + BinaryUtil.pad(print(idex.outmemWrite), 1) + "\n";
		output += "memRead in:" + BinaryUtil.pad(print(idex.memRead), 1) + "\n";
		output += "memRead out:" + BinaryUtil.pad(print(idex.outmemRead), 1) + "\n";
		output += "branch in:" + BinaryUtil.pad(print(idex.branch), 1) + "\n";
		output += "branch out:" + BinaryUtil.pad(print(idex.outbranch), 1) + "\n";
		output += "regDST in:" + BinaryUtil.pad(print(idex.regDST), 1) + "\n";
		output += "regDST out:" + BinaryUtil.pad(print(idex.outregDST), 1) + "\n";
		output += "aluOP in:" + BinaryUtil.pad(print(idex.aluOp), 2) + "\n";
		output += "aluOP out:" + BinaryUtil.pad(print(idex.outaluOp), 2) + "\n";
		output += "aluSrc in:" + BinaryUtil.pad(print(idex.aluSrc), 1) + "\n";
		output += "aluSrc out:" + BinaryUtil.pad(print(idex.outaluSrc), 1) + "\n";
		output += "branchBNE in:" + BinaryUtil.pad(print(idex.branchBNE), 1) + "\n";
		output += "branchBNE out:" + BinaryUtil.pad(print(idex.outbranchBNE), 1) + "\n";
		output += "------------------=---- EX/MEM -----------------------\n";
		output += "add ALU result:" + BinaryUtil.pad(print(exmem.addALUresult),32) + "\n";
		output += "zero:" + BinaryUtil.pad(print(exmem.zero),1) + "\n";
		output += "general ALU result:" + BinaryUtil.pad(print(exmem.genALUResult), 32) + "\n";
		output += "reaData2:" + BinaryUtil.pad(print(exmem.readData2), 32) + "\n";
		output += "out add ALU result:" + BinaryUtil.pad(print(exmem.outAddALUresult),32) + "\n";
		output += "out zero:" + BinaryUtil.pad(print(exmem.outZero),1) + "\n";
		output += "out general ALU result:" + BinaryUtil.pad(print(exmem.outGenALUresult), 32) + "\n";
		output += "out reaData2:" + BinaryUtil.pad(print(exmem.outReadData2), 32) + "\n";
		output += "\t\t\t Control information\n";
		output += "regWrite in:" + BinaryUtil.pad(print(exmem.regWrite), 1) + "\n";
		output += "regWrite out:" + BinaryUtil.pad(print(exmem.outregWrite), 1) + "\n";
		output += "memToReg in:" + BinaryUtil.pad(print(exmem.memToReg), 1) + "\n";
		output += "memToReg out:" + BinaryUtil.pad(print(exmem.outmemToReg), 1) + "\n";
		output += "jump in:" + BinaryUtil.pad(print(exmem.jump), 1) + "\n";
		output += "jump out:" + BinaryUtil.pad(print(exmem.outjump), 1) + "\n";
		output += "jumpReg in:" + BinaryUtil.pad(print(exmem.jumpReg), 1) + "\n";
		output += "jumpReg out:" + BinaryUtil.pad(print(exmem.outjumpReg), 1) + "\n";
		output += "memWrite in:" + BinaryUtil.pad(print(exmem.memWrite), 1) + "\n";
		output += "memWrite out:" + BinaryUtil.pad(print(exmem.outmemWrite), 1) + "\n";
		output += "memRead in:" + BinaryUtil.pad(print(exmem.memRead), 1) + "\n";
		output += "memRead out:" + BinaryUtil.pad(print(exmem.outmemRead), 1) + "\n";
		output += "branch in:" + BinaryUtil.pad(print(exmem.branch), 1) + "\n";
		output += "branch out:" + BinaryUtil.pad(print(exmem.outbranch), 1) + "\n";
		output += "----------------------- MEM/WB -----------------------\n";
		output += "readData:" + BinaryUtil.pad(print(memwb.readData),32) + "\n";
		output += "general ALU result:" + BinaryUtil.pad(print(memwb.genALUresult),32) + "\n";
		output += "out readData:" + BinaryUtil.pad(print(memwb.outReadData),32) + "\n";
		output += "out general ALU result:" + BinaryUtil.pad(print(memwb.outGenALUresult),32) + "\n";
		output += "\t\t\t Control information\n";
		output += "regWrite in:" + BinaryUtil.pad(print(memwb.regWrite), 1) + "\n";
		output += "regWrite out:" + BinaryUtil.pad(print(memwb.outregWrite), 1) + "\n";
		output += "memToReg in:" + BinaryUtil.pad(print(memwb.memToReg), 1) + "\n";
		output += "memToReg out:" + BinaryUtil.pad(print(memwb.outmemToReg), 1) + "\n";

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