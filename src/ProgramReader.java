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
	public static List<Long> getMemory(String fileName) {
		File file = new File(fileName);
		int chunk;
		DataInputStream fin = null;
		List<Long> individualBytes = new ArrayList<Long>();
		try
		{
			fin = new DataInputStream(new FileInputStream(file));
			while(true){
				int b = fin.readUnsignedByte();
				individualBytes.add(new Long(b));
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
