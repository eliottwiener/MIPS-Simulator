public class Splitter{
	public Pin[] connectToList;
	
	public Splitter(int numOutputs){
		connectToList = new Pin[numOutputs];
		for(int x = 0; x < connectToList.length ; x++){
			connectToList[x] = new Pin();
		}
	}
	
	public final void setValue(final long value){
		for(int x = 0; x < connectToList.length ; x++){
			connectToList[x].setValue(value);
		}
	}
}