import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Memory {
	List<BinaryNum> mem = new ArrayList<BinaryNum>();
	
	public Memory(final List<BinaryNum> contents){
		Iterator<BinaryNum> it = contents.iterator();
		while(it.hasNext()){
			BinaryNum next = it.next();
			mem.add(next);
		}
	}
	
	public final BinaryNum loadWord(int addr){
		try{
			String a = mem.get(addr+3).pad(8).toString();
			String b = mem.get(addr+2).pad(8).toString();
			String c = mem.get(addr+1).pad(8).toString();
			String d = mem.get(addr).pad(8).toString();
			return new BinaryNum(a+b+c+d);
		} catch(Exception e){
			System.out.println(e + " : " + e.getMessage());
			throw new RuntimeException("unable to load word at address: " + addr);
		}
	}
	
	public final void storeWord(int addr, BinaryNum val){
			BinaryNum a = val.getRange(31, 24);
			BinaryNum b = val.getRange(23, 16);
			BinaryNum c = val.getRange(15, 8);
			BinaryNum d = val.getRange(7, 0);
			mem.set(addr+3, a);
			mem.set(addr+2, b);
			mem.set(addr+1, c);
			mem.set(addr+0, d);
	}
}
