import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProgramReader {
	public static List<Long> getInstructions(String fileName) {
		File file = new File(fileName);
		int chunk;
		DataInputStream fin = null;
		List<Integer> individualBytes = new ArrayList<Integer>();
		List<Long> instructions = new ArrayList<Long>();
		try
		{
			fin = new DataInputStream(new FileInputStream(file));
			while(true){
				int b = fin.readUnsignedByte();
				individualBytes.add(b);
			}
		} catch(EOFException e){
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File " + file.getAbsolutePath() +
			" could not be found on filesystem");
		}
		catch(IOException ioe)
		{
			System.out.println("Exception while reading the file" + ioe);
		}
		Iterator<Integer> it = individualBytes.iterator();
		while(it.hasNext()){
			String instr = BinaryUtil.pad(Integer.toBinaryString(it.next()), 8);
			instr += BinaryUtil.pad(Integer.toBinaryString(it.next()), 8);
			instr += BinaryUtil.pad(Integer.toBinaryString(it.next()), 8);
			instr += BinaryUtil.pad(Integer.toBinaryString(it.next()), 8);
			Long instrLong = Long.parseLong(instr,2);
			System.out.println(instr);
			instructions.add(instrLong);
		}
		return instructions;
	}
}
