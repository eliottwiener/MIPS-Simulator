public class ShiftLeftTwo implements Clockable{
	Pin in = new Pin();
	Pin out = new Pin();
	int outputBits;
	
	ShiftLeftTwo(int outputBits){
		this.outputBits = outputBits;
		out.setValue(new BinaryNum("0").pad(outputBits));
		in.setValue(new BinaryNum("0"));
	}

	public void clockEdge() {
		if(outputBits == 32){
			out.setValue(in.getValue().shiftLeft().shiftLeft());
		}
		else{
			out.setValue(in.getValue().shiftLeftChangeSize().shiftLeftChangeSize());
		}
	}
	
	
}
