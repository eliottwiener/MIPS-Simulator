import java.util.HashMap;


public class Memory {
	HashMap<Long,Long> mem = new HashMap<Long,Long>();
	
	public Memory(final Long lower,final Long upper){
		for(long addr = lower ; addr <= upper; addr++){
			mem.put(addr, (long)0);
		}
	}
	
	public final Long loadWord(Long addr){
		try{
			String a = BinaryUtil.pad(Long.toBinaryString(mem.get(addr)));
			String b = BinaryUtil.pad(Long.toBinaryString(mem.get(addr+1)));
			String c = BinaryUtil.pad(Long.toBinaryString(mem.get(addr+2)));
			String d = BinaryUtil.pad(Long.toBinaryString(mem.get(addr+3)));
			return Long.parseLong(a+b+c+d,2);
		} catch(Exception e){
			throw new RuntimeException("unable to load word at address: " + addr);
		}
	}
	
	public final void storeWord(Long addr, Long val){
		if(mem.containsKey(addr) && mem.containsKey(addr+1) && mem.containsKey(addr+2) && mem.containsKey(addr+3)){
			String valString = BinaryUtil.pad(Long.toBinaryString(val));
			Long a = Long.parseLong(valString.substring(0,8));
			Long b = Long.parseLong(valString.substring(8,16));
			Long c = Long.parseLong(valString.substring(16,24));
			Long d = Long.parseLong(valString.substring(24,32));
			mem.put(addr, a);
			mem.put(addr+1, b);
			mem.put(addr+2, c);
			mem.put(addr+3, d);
		} else {
			throw new RuntimeException("Can't put word at: " + Long.toBinaryString(addr));
		}
	}
}
