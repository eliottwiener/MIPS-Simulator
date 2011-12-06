import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Memory {
	List<Long> mem = new ArrayList<Long>();
	
	public Memory(final List<Long> contents){
		System.out.println(contents.size());
		Iterator<Long> it = contents.iterator();
		while(it.hasNext()){
			Long next = it.next();
			mem.add(next);
		}
	}
	
	public final Long loadWord(Long addr){
		try{
			String a = BinaryUtil.pad(Long.toBinaryString(mem.get(addr.intValue())),8);
			String b = BinaryUtil.pad(Long.toBinaryString(mem.get(addr.intValue()+1)),8);
			String c = BinaryUtil.pad(Long.toBinaryString(mem.get(addr.intValue()+2)),8);
			String d = BinaryUtil.pad(Long.toBinaryString(mem.get(addr.intValue()+3)),8);
			return Long.parseLong(a+b+c+d,2);
		} catch(Exception e){
			System.out.println(e + " : " + e.getMessage());
			throw new RuntimeException("unable to load word at address: " + addr);
		}
	}
	
	public final void storeWord(Long addr, Long val){
			String valString = BinaryUtil.pad(Long.toBinaryString(val),32);
			Long a = Long.parseLong(valString.substring(0,8), 2);
			Long b = Long.parseLong(valString.substring(8,16), 2);
			Long c = Long.parseLong(valString.substring(16,24), 2);
			Long d = Long.parseLong(valString.substring(24,32), 2);
			mem.set(addr.intValue(), a);
			mem.set(addr.intValue()+1, b);
			mem.set(addr.intValue()+2, c);
			mem.set(addr.intValue()+3, d);
	}
}
