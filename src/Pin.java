import java.util.ArrayList;
import java.util.List;

public class Pin {
	private Long value;
	private List<Pin> connectedPins = new ArrayList<Pin>();
	
	Pin(){}
	
	public final void setValue(final Long val){
		this.value = val;
		for(Pin p : connectedPins){
			p.setValue(val);
		}
	}
	
	public final Long getValue(){
		return this.value;
	}
	
	public final void connectTo(final Pin somePin){
		connectedPins.add(somePin);
	}
}
