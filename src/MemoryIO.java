
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
		System.out.println(address.getValue());
		if(memRead.getValue().equals(new BinaryNum("1"))){
			readData.setValue(mem.loadWord(address.getValue().toLong().intValue()));
		}else if(memWrite.getValue().equals(new BinaryNum("1"))){
			mem.storeWord(address.getValue().toLong().intValue(), writeData.getValue());
		}
	}

}
