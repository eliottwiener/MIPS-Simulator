
public class MemoryIO implements Clockable {

	public final Pin address = new Pin();
	public final Pin writeData = new Pin();
	public final Pin readData = new Pin();
	public final Pin memWrite = new Pin();
	public final Pin memRead = new Pin();
	public final Memory mem;
	
	
	public MemoryIO(final Memory dataMemory){
		this.mem = dataMemory;
	}
	
	public void clockEdge() {
		System.out.println("[DEBUG] Class: MemoryIO");
		if(memRead.getValue().equals(1)){
			readData.setValue(mem.loadWord(address.getValue()));
			System.out.println("readData:" + BinaryUtil.pad(Long.toBinaryString(readData.getValue()), 32));
		} else if(memWrite.getValue().equals(1)){
			mem.storeWord(address.getValue(), writeData.getValue());
		}
		
		System.out.println("address:" + BinaryUtil.pad(Long.toBinaryString(address.getValue()), 32) +
				         "\nwriteData:" + BinaryUtil.pad(Long.toBinaryString(writeData.getValue()), 32) +
				         "\nmemWrite:" + BinaryUtil.pad(Long.toBinaryString(memWrite.getValue()), 32) +
						 "\nmemRead:" + BinaryUtil.pad(Long.toBinaryString(memRead.getValue()), 32));
	}

}
