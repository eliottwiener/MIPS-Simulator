
public class Pin {
	private Long value;
	private Pin connectedPin;
	
	Pin(){}
	
	public final void setValue(final Long val){
		this.value = val;
		if(connectedPin != null){
			connectedPin.setValue(val);
		}
	}
	
	public final Long getValue(){
		return this.value;
	}
	
	public final void connectTo(final Pin somePin){
		connectedPin = somePin;
	}
}
