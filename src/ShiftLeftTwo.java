public class ShiftLeftTwo implements Clockable{
	Pin in = new Pin();
	Pin out = new Pin();
	int outputBits;
	
	ShiftLeftTwo(int outputBits){
		this.outputBits = outputBits;
	}

	public void clockEdge() {
		String myStr = BinaryUtil.pad(Long.toBinaryString(in.getValue()), outputBits);
	    out.setValue(Long.parseLong(myStr.substring(2,outputBits) + "00", 2));
	}
	
	
}
