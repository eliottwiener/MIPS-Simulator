import java.util.HashMap;


public class Memory {
	HashMap<Long,Long> mem = new HashMap<Long,Long>();
	
	public Memory(final Long lower,final Long upper){
		for(long addr = lower ; addr <= upper; addr++){
			mem.put(addr, (long)0);
		}
	}
	
	public final Long loadWord(Long addr){
		return mem.get(addr);
	}
	
	public final void storeWord(Long addr, Long val){
		if(mem.containsKey(addr)){
			mem.put(addr, val);
		} else {
			throw new RuntimeException("No such memory location: " + Long.toBinaryString(addr));
		}
	}
}
