package hw1;

/**
 * This class implements {@link BalancedTernary} numbers.
 * 
 * @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/02/2025
 * 
 */

public class BalancedTernary {
	
	 /**
	  * The value of the {@link BalancedTernary} number.
	  */
	
	private String value;
	
	/**
	 * The default constructor.
	 */
	
	public BalancedTernary() {
		value="0000";
	}
	
	/**
	 * The constructor that saves the value of the {@link BalancedTernary} provided by the user. 
	 * @param v- The value of the {@link BalancedTernary}.
	 */
	
	public BalancedTernary(String v) {
		value=v;
	}
	
	/**
	 * This method returns the value of the {@link BalancedTernary} number.
	 * @return A String value.
	 * 
	 */
	
	public String getValue() {
		return value;
	}
	
	/**
	 * This method sets the value of a {@link BalancedTernary}.
	 * @param st- A String variable.
	 */
	
	public void setValue(String st) {
		value=st;
	}
	
	/**
	 *This method returns the value of the {@link BalancedTernary} number.
	 *@return A String value. 
	 */
	
	public String toString() {
		return this.value;
	}
	
	/**
	 * This method adds two  numbers -the one that calls the method and the parameter.
	 * @param bt- A {@link BalancedTernary} number.
	 * @return A {@link BalancedTernary} number.
	 */
	
	public BalancedTernary add(BalancedTernary bt) {
	    int result= this.bt_to_int()+ bt. bt_to_int();
	    BalancedTernary bt2=int_to_bt(result);
	    return   bt2.simplify();
	}
	
	/**
	 * This method subtracts the parameter {@link BalancedTernary} number from the one that calls the method.
	 * @param bt- A {@link BalancedTernary} number.
	 * @return A {@link BalancedTernary} number.
	 */
	
	public BalancedTernary subtract(BalancedTernary bt) {
		int result= this.bt_to_int()- bt. bt_to_int();
		BalancedTernary bt2=int_to_bt(result);
		return bt2.simplify();
	}
	
	/**
	 * This method multiplies two {@link BalancedTernary} numbers - the one that calls the method and the parameter one.
	 * @param bt- A {@link BalancedTernary} number.
	 * @return A {@link BalancedTernary} number.
	 */
	
	public BalancedTernary multiply(BalancedTernary bt) {
		int result= this.bt_to_int()* bt. bt_to_int();
		BalancedTernary bt2=int_to_bt(result);
		return bt2.simplify();
	}
	
	/**
	 * This method divides the {@link BalancedTernary} number that calls the method with the parameter one.
	 * @param bt- A {@link BalancedTernary} number.
	 * @return A {@link BalancedTernary} number.
	 */
	
	public BalancedTernary divide(BalancedTernary bt) {
		int result= this.bt_to_int()/ bt. bt_to_int();
		BalancedTernary bt2=int_to_bt(result);
		return bt2.simplify();
	}
	
	/**
	 * This method gives the remainder of the division between the {@link BalancedTernary} number that calls the method and the parameter one.
	 * @param bt- A {@link BalancedTernary} number.
	 * @return A {@link BalancedTernary} number.
	 */
	
	public BalancedTernary remainder(BalancedTernary bt ) {
		int result= this.bt_to_int()% bt. bt_to_int();
		BalancedTernary bt2=int_to_bt(result);
		return bt2.simplify();
	}
	
	/**
	 * This method negates the {@link BalancedTernary} number that calls it.
	 * @return A {@link BalancedTernary} number.
	 * 
	 */
	
	public BalancedTernary negate() {
		StringBuilder st=new StringBuilder();
		for(int i=0;i<this.value.length();i++) {
			if(this.value.charAt(i)=='1')
				st.append('N');
			else if(this.value.charAt(i)=='N')
				st.append('1');
			else st.append('0');
		}
		return new BalancedTernary(st.toString());
	}
	
	/**
	 * This method converts the {@link BalancedTernary} number that calls it to an integer.
	 * @return An Integer number.
	 */
	
	public  int bt_to_int() {
		int sum=0;
		for(int i=0;i<this.value.length();i++) {
			if(this.value.charAt(this.value.length()-1-i)=='N')
				sum-=Math.pow(3,i );
			else if (this.value.charAt(this.value.length()-1-i)=='1')
				sum+=Math.pow(3, i);
		}
		return sum;
	}
	
	/**
	 * This method converts the integer number provided via the parameters and returns its {@link BalancedTernary} value.
	 * @param num- An Integer number.
	 * @return A {@link BalancedTernary} number.
	 */
	
	public static BalancedTernary int_to_bt(int num) {
		if(num==0)
			return new BalancedTernary();
		StringBuilder st=new StringBuilder();
		boolean negative=num<0;
		num=Math.abs(num);
		while(num!=0) {
			int remainder=num%3;
			num/=3;
			if(remainder==2) {
				st.append('N');
				num+=1;
			}
			else if (remainder==1)
				st.append('1');
			else 
				st.append('0');
		}
		st.reverse();
		if(negative)
			return new BalancedTernary(st.toString()).negate();
		else return new BalancedTernary(st.toString());
	}
	
	/**
	 * This method simplifies the {@link BalancedTernary} number by removing all the 0s in the front.
	 */
	
	private BalancedTernary simplify() {
		int first_app=0;
		for(int i=0;i<this.value.length();i++) {
			if(this.value.charAt(i)!='0') {
				first_app=i;
				break;
			}
		}
		 return new BalancedTernary(value.substring(first_app,value.length()));
	}
}
 