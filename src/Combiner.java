//Combines PC +4 [31-28] with instruction [25-0]
public class Combiner implements Clockable{
	Pin pcIn = new Pin();
	Pin jumpAddr = new Pin();
	Pin out = new Pin();
	
	public Combiner(){
		pcIn.setValue(new BinaryNum("0").pad(32));
		jumpAddr.setValue(new BinaryNum("0").pad(28));
		out.setValue(new BinaryNum("0").pad(32));
	}

	public void clockEdge() {
		String pcinc3128 = pcIn.getValue().getRange(31, 28).toString();
		String ja250 = jumpAddr.getValue().toString();
		String outString = pcinc3128 + ja250;
		out.setValue(new BinaryNum(outString));
	}
	
	
}
