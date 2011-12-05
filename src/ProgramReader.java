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
		FileInputStream fin = null;
		List<Integer> individualBytes = new ArrayList<Integer>();
		List<Long> instructions = new ArrayList<Long>();

		try
		{

			fin = new FileInputStream(file);

			while( (chunk = fin.read()) != -1){
				individualBytes.add(chunk);
			}
			/*
			 * To close the FileInputStream, use
			 * void close() method of FileInputStream class.
			 *
			 * close method also throws IOException.
			 */
			fin.close();

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
			String instr = BinaryUtil.pad8(Integer.toBinaryString(it.next()));
			instr += BinaryUtil.pad8(Integer.toBinaryString(it.next()));
			instr += BinaryUtil.pad8(Integer.toBinaryString(it.next()));
			instr += BinaryUtil.pad8(Integer.toBinaryString(it.next()));
			instructions.add(Long.parseLong(instr,2));
		}
		
		System.out.println(instructions);
		return instructions;
	}
}
