import java.util.ArrayList;


public class Mux implements Clockable{
	
	public final Pin output = new Pin(); 
	
	public final Pin switcher = new Pin();
	
	public final Pin input0 = new Pin();
	
	public final Pin input1 = new Pin();
	
	Mux(){}
	
	public void clockEdge() {
		System.out.println("[DEBUG] Class:Mux");
		System.out.println("switcher:" + BinaryUtil.pad(Long.toBinaryString(switcher.getValue()), 32));
		if(switcher.getValue().equals((long)0)){
			output.setValue(input0.getValue());
			System.out.println("input0:" + BinaryUtil.pad(Long.toBinaryString(input0.getValue()), 32));
		} else {
			output.setValue(input1.getValue());
			System.out.println("input1:" + BinaryUtil.pad(Long.toBinaryString(input1.getValue()), 32));			
		}
		
		
		System.out.println("\noutput:" + BinaryUtil.pad(Long.toBinaryString(output.getValue()), 32));
	}
}
