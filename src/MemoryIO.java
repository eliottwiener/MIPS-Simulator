
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
	
	@Override
	public void clockEdge() {
		if(memRead.getValue().equals(1)){
			readData.setValue(mem.loadWord(address.getValue()));
		} else if(memWrite.getValue().equals(1)){
			mem.storeWord(address.getValue(), writeData.getValue());
		}
	}

}
