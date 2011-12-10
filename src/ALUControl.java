public class ALUControl {
	public final Pin func = new Pin();
	public final Pin aluOp = new Pin();
	public final Pin aluControl = new Pin();

	public ALUControl(){}

	public final void update(){
		BinaryNum funcBits = func.getValue();

		boolean f0 = funcBits.getBit(0);
		boolean f1 = funcBits.getBit(1);
		boolean f2 = funcBits.getBit(2);
		boolean f3 = funcBits.getBit(3);

		BinaryNum aluOpBits = aluOp.getValue();

		boolean aluOp0 = aluOpBits.getBit(0);
		boolean aluOp1 = aluOpBits.getBit(1);

		boolean[] aluControlBits = new boolean[3];
		aluControlBits[2] = aluOp1 && (f0 || f3);
		aluControlBits[1] = !aluOp1 || !f2;
		aluControlBits[0] = aluOp0 || (aluOp1 && f1);
		aluControl.setValue(new BinaryNum(aluControlBits));

	}
}