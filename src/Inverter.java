public class Inverter implements Clockable{
	Pin in = new Pin();
	Pin out = new Pin();
	Pin branchBNE = new Pin();
	
	public void clockEdge(){
		if(branchBNE.getValue().equals((long)1)){
			if(in.getValue().equals((long)1)){
				out.setValue((long)0);
			}else out.setValue((long)1);
		} else out.setValue(in.getValue());
	}
}