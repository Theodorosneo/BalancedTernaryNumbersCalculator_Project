package hw1;

/**
 * This class implements a {@link Memory} value.
 * 
 * @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/02/2025
 * 
 */

public class Memory {
	
	/**
	 * The {@link BalancedTernary} number that is stored in the {@link Memory}.
	 */
	
	private static BalancedTernary mem;
	
	/**
	 * A constructor that sets the {@link Memory} value to 0.
	 */
	
	public Memory() {
		mem=new BalancedTernary();
	}
	
	/**
	 * This method stores the {@link BalancedTernary} provided via the parameters to the {@link Memory} variable replacing whatever value it had before.
	 * @param bt-A balanced ternary number.
	 */
	
	public  void store(BalancedTernary bt) {
		mem.setValue(bt.getValue());
	}
	
	/**
	 * This method returns the Integer value of the {@link Memory} variable.
	 * @return An Integer number. 
	 */
	
	public int memory_as_int() {
		return mem.bt_to_int();
	}
	
	/**
	 * This method returns the String of the {@link Memory} value.
	 * @return A String value.
	 */
	
	public String memory_as_bt() {
		return mem.getValue();
	}
	
	/**
	 * This method returns the value as a String .
	 * @return A String value.
	 */
	public String toString() {
		return mem.getValue();
	}
	
}
