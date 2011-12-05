public class Splitter{
	public int numOutputs;
	public Pin[] connectToList;
	
	public Splitter(int numOutputs){
		this.numOutputs = numOutputs;
		connectToList = new Pin[this.numOutputs];
	}
	
	public final void setValue(final long value){
		for(int x = 0; x < numOutputs ; x++){
			connectToList[x].setValue(value);
		}
	}
}