import java.util.ArrayList;


public class Mux implements Clockable{
	
	private final Pin output = new Pin(); 
	
	private final Pin switcher = new Pin();
	
	private final ArrayList<Pin> inputs = new ArrayList<Pin>();
	
	Mux(){}

	public void addInput(Pin p){
		inputs.add(p);
	}
	
	public Pin getOutput(){
		return output;
	}
	
	public Pin getSwitcher(){
		return switcher;
	}
	
	public Pin getInput(final int i){
		return inputs.get(i);
	}
	
	@Override
	public void clockEdge() {
		output.setValue(inputs.get((int)switcher.getValue()).getValue());
	}
}
