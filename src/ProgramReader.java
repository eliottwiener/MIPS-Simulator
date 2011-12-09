import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProgramReader {
	public static List<BinaryNum> getMemory(String fileName) {
		File file = new File(fileName);
		DataInputStream fin = null;
		List<BinaryNum> individualBytes = new ArrayList<BinaryNum>();
		try
		{
			fin = new DataInputStream(new FileInputStream(file));
			while(true){
				int b = fin.readUnsignedByte();
				String s = Integer.toBinaryString(b);
				BinaryNum bn = new BinaryNum(s);
				bn = bn.pad(8);
				individualBytes.add(bn);
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
		return individualBytes;
	}
}
