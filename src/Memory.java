import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Memory {
	HashMap<Long,Long> mem = new HashMap<Long,Long>();
	
	public Memory(final List<Long> contents){
		Iterator<Long> it = contents.iterator();
		while(it.hasNext()){
			Long next = it.next();
			mem.put((long)contents.indexOf(next), next);
		}
	}
	
	public final Long loadWord(Long addr){
		try{
			String a = BinaryUtil.pad(Long.toBinaryString(mem.get(addr)),8);
			String b = BinaryUtil.pad(Long.toBinaryString(mem.get(addr+1)),8);
			String c = BinaryUtil.pad(Long.toBinaryString(mem.get(addr+2)),8);
			String d = BinaryUtil.pad(Long.toBinaryString(mem.get(addr+3)),8);
			return Long.parseLong(a+b+c+d,2);
		} catch(Exception e){
			System.out.println(e.getMessage());
			throw new RuntimeException("unable to load word at address: " + addr);
		}
	}
	
	public final void storeWord(Long addr, Long val){
		if(mem.containsKey(addr) && mem.containsKey(addr+1) && mem.containsKey(addr+2) && mem.containsKey(addr+3)){
			String valString = BinaryUtil.pad(Long.toBinaryString(val),32);
			Long a = Long.parseLong(valString.substring(0,8), 2);
			Long b = Long.parseLong(valString.substring(8,16), 2);
			Long c = Long.parseLong(valString.substring(16,24), 2);
			Long d = Long.parseLong(valString.substring(24,32), 2);
			mem.put(addr, a);
			mem.put(addr+1, b);
			mem.put(addr+2, c);
			mem.put(addr+3, d);
		} else {
			throw new RuntimeException("Can't put word at: " + Long.toBinaryString(addr));
		}
	}
}
