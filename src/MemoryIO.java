
public class MemoryIO implements Clockable {

	public final Pin address = new Pin();
	public final Pin writeData = new Pin();
	public final Pin readData = new Pin();
	public final Pin memWrite = new Pin();
	public final Pin memRead = new Pin();
	public final Memory mem;
	
	
	public MemoryIO(final Memory dataMemory){
		this.mem = dataMemory;
		address.setValue(new BinaryNum("0").pad(32));
		writeData.setValue(new BinaryNum("0").pad(32));
		readData.setValue(new BinaryNum("0").pad(32));
		memWrite.setValue(new BinaryNum("0"));
		memRead.setValue(new BinaryNum("0"));
	}
	
	public void clockEdge() {
		if(memRead.getValue().equals(new BinaryNum("1"))){
			readData.setValue(mem.loadWord(address.getValue().toLong().intValue()));
		}else if(memWrite.getValue().equals(new BinaryNum("1"))){
			System.out.println("Storing word at: " + address.getValue().toLong().intValue());
			System.out.println("value is: " + writeData.getValue());
			mem.storeWord(address.getValue().toLong().intValue(), writeData.getValue());
		}
	}

}
