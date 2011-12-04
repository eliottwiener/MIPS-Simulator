//Combines PC +4 [31-28] with instruction [25-0]
public class Combiner implements Clockable{
	Pin pcIn = new Pin();
	Pin jumpAddr = new Pin();
	Pin out = new Pin();
	
	public Combiner(){}

	@Override
	public void clockEdge() {
		String pcinc3128 = BinaryUtil.pad(Long.toBinaryString(pcIn.getValue())).substring(0,4);
		String ja250 = BinaryUtil.pad(Long.toBinaryString(jumpAddr.getValue())).substring(6);
		String outString = pcinc3128 + ja250;
		out.setValue(Long.parseLong(outString,2));
	}
	
	
}
