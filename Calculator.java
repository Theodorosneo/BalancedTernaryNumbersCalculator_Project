package hw1;
import java.util.Scanner;

/**
 * This class implements a {@link Calculator} specified for {@link BalancedTernary} numbers.
 * 
 * @author Theodoros Neofytou.
 * @version 1.0
 * @since 18/02/2025
 * @see Memory
 * @see BalancedTernary
 */
public class Calculator {
	
	/**
	 * The default constructor of a {@link Calculator}.
	 */
	public Calculator(){}
	
   
	/**
	 * The {@link Memory} value.
	 */
	private static Memory mem=new Memory();
    
    /**
     * This method evaluates the calculation that the user provides, stores the result in the {@link Memory} and returns the {@link Memory} as a String.
     * @param equation- A String value.
     * @return A String value.
     */
	
	public static String evaluate(String equation) {
		equation=removeSpaces(equation);
		if((equation.startsWith("1") ||equation.startsWith("0") || equation.startsWith("N") || equation.startsWith("mem") ) && containsOp(equation)){
			if(validEquation(equation)) {
				if(numOfOps(equation)==1) {
					int index=indexOfOp(equation);
					String sub1=equation.substring(0,index);
					String sub2=equation.substring(index+1,equation.length());
					sub1=removeSpaces(sub1);
					sub2=removeSpaces(sub2);
					char operator=equation.charAt(index);
					if(is3Base(sub1) && is3Base(sub2)) {
						mem.store(new BalancedTernary(calculations(operator,sub1,sub2)));
						return mem.toString();
					}
					else if((is3Base(sub1) && sub2.equals("mem")) ) {
						mem.store(new BalancedTernary(calculations(operator,sub1,mem.toString())));
						return mem.toString();	
					}
					else if((is3Base(sub2) && sub1.equals("mem"))) {
						mem.store(new BalancedTernary(calculations(operator,sub2,mem.toString())));
						return mem.toString();
					}
					else if(sub1.equals("mem") && sub2.equals("mem")) {
						mem.store(new BalancedTernary(calculations(operator,mem.toString(),mem.toString())));
						return mem.toString();
					}
					else return "Error:I dont understand :  " +equation;	
				}
				else if(numOfOps(equation)>1) {
					if(containsMDM(equation)){
						int index=0;
						int index1=0;
						int index2=equation.length()-1;
						for (int i=0;i<equation.length();i++) {
							if(isMDM(equation.charAt(i))) {
							    index=i;
							    break;
							}
						}
						for(int i=index-1; i>=0;i--) {
							if(isOp(equation.charAt(i))) {
								index1=i;
								break;
							}
						}
						for(int i=index+1; i<equation.length();i++) {
							if(isOp(equation.charAt(i))) {
								index2=i;
								break;
							}
						}
						if(index1==index-1 || index2==index+1) {
							return "Error:I dont understand :  " +equation;
						}
						String sub_equation;
						if(index1==0 && index2!=equation.length()-1) {
							sub_equation=equation.substring(index1,index2);
						}
						else if(index1!=0 && index2==equation.length()-1){
							sub_equation=equation.substring(index1+1,index2+1);
						}
						else {
							sub_equation=equation.substring(index1+1,index2);
						}
						String result=evaluate(sub_equation);
						if(is3Base(result)) {
							String new_equation;
							if(index1==0) 
								 new_equation=result+equation.substring(index2,equation.length());
							else if(index2==equation.length()-1)
								 new_equation=equation.substring(0,index1+1)+result;
							else 
								new_equation=equation.substring(0,index1+1)+result+equation.substring(index2,equation.length());
							 return evaluate(new_equation);
						}
						else return result;
						
					}
					else {
						int index=0;
						int index2=0;
						int count=0;
						for(int i=0;i<equation.length();i++) {
							if(isOp(equation.charAt(i))&& count==1) {
								index2=i;
								break;
							}
							else if(isOp(equation.charAt(i))&& count==0) {
								index=i;
								count++;
							}
						}
						if(index2==index+1) {
							return " Error: I don't understand : "+equation;
						}
						String sub_equation=equation.substring(0,index2);
						String result=evaluate(sub_equation);
						if(is3Base(result)) {
							String new_equation=result+equation.substring(index2,equation.length());
							return evaluate(new_equation);
						}
						else return  result;
					}
				}
			}
			else return " Error: I don't understand : "+equation;
		}
		else if(equation.startsWith("=")) {
				String st=equation.substring(1,equation.length());
				st=removeSpaces(st);
				if(is3Base(st)) {
					mem.store(new BalancedTernary(st));
					return "Stored "+st;
				}
				else if(validEquation(st)) {
					String result=evaluate(st);
					mem.store(new BalancedTernary(result));
					if(is3Base(result)) {
						return "Stored "+result;
					}
					else return result;
				}
				else {
					return "Error :I dont understand : "+equation;
				}
		}
		else if(equation.startsWith("-")) {
					String st=equation.substring(1,equation.length());
					st=removeSpaces(st);
					if(st.equals("mem")) {
					  mem.store(new BalancedTernary(mem.memory_as_bt()).negate());
					  return mem.toString();
					}else if(is3Base(st)) {
						mem.store(new BalancedTernary(st).negate());
						return mem.toString();
					}
					else return "Error:I dont understand : " +equation;	
		}
		else if(equation.startsWith("dec")) {
				String st=equation.substring(3,equation.length());
				st=removeSpaces(st);
				if(is3Base(st)) {
					return ""+new BalancedTernary(st).bt_to_int();
				}
				else if(st.equals("mem")) {
					return ""+new BalancedTernary(mem.toString()).bt_to_int();
				}
				else return "Error: I dont understand : "+equation;
		}
		else if(equation.startsWith("bt")) {
				String st=equation.substring(2,equation.length());
				st=removeSpaces(st);
				if(isInteger(st)) {
					mem.store(BalancedTernary.int_to_bt(st_to_int(st)));
					return mem.toString();
				}
				else return "Error: I dont understand : "+equation;
			}
		return "Error: I dont understand : "+equation;
		
	}
	
	/**
	 * This method reads the equation provided from the user, evaluates it and shows the output of the calculation.
	 * Then it repeats the same process until the user quits the {@link Calculator}.
	 */
	public static void REPL() {
		Scanner In=new Scanner(System.in);
		String equation;
		System.out.print(">");
		equation=In.nextLine();
		equation=removeSpaces(equation);
		while(!equation.equals("quit")) {
			System.out.println(evaluate(equation));
			System.out.println(">");
			equation=In.nextLine();
			equation=removeSpaces(equation);
		}
		System.out.println("Done.");
		In.close();
		return;
	}
	
	/**
	 * This is the main function of the {@link Calculator}.
	 * @param args[]- The arguments of the main function.
	 */
	
	public static void main(String args[]) {
		REPL();
	}
	
	/**
	 * This function indicates if the provided String is a {@link BalancedTernary} number.
	 * @param num-A String value.
	 * @return A boolean value.
	 */
	
	private static boolean is3Base(String num) {
		for(int i=0;i<num.length();i++) {
			if(num.charAt(i)!='0' && num.charAt(i)!='1' && num.charAt(i)!='N' ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method converts a String to an Integer.
	 * @param st- A String value.
	 * @return An Integer value.
	 */
	
	private static int st_to_int(String st) {
		return Integer.parseInt(st);
	}
	
	/**
	 * This method returns true if the provided String has an operator.
	 * @param st- A String value.
	 * @return A boolean value.
	 */
	private static boolean containsOp(String st) {
		 return st.contains("+") || st.contains("-") || st.contains("*") || st.contains("/") || st.contains("%");
	}
	
	/**
	 * This method returns true if a character is an operator.
	 * @param c- A char value.
	 * @return A boolean value;
	 */
	
	private static boolean isOp(char c) {
		return c=='+' || c=='-' || c=='*' || c=='/' || c=='%';
	}
	
	/**
	 * This method removes the spaces at the front and at the end of the String provided.
	 * @param st- A String value.
	 * @return A String value.
	 */
	
	private static String removeSpaces(String st) {
		int index1=0;
		for(int i=0;i<st.length();i++) {
			if(st.charAt(i)!=' '){
				index1=i;
				break;
			}
		}
		int index2=st.length()-1;
		for(int i=st.length()-1;i>=0;i--) {
			if(st.charAt(i)!=' '){
				index2=i;
				break;
			}
		}
		return st.substring(index1,index2+1);
	}
	
	
	/**
	 * This method returns the index of the operator in the provided String value.
	 * @param st- A String value. 
	 * @return An Integer value.
	 */
	
	private static int indexOfOp(String st) {
		for(int i=0;i<st.length();i++) {
			if(isOp(st.charAt(i)))
				return i;
		}
		return -1;
	}
	
	/**
	 * This method returns the result of the equation between the provided String values by identifying the operator used.
	 * @param operator- A char value.
	 * @param st1- A String value.
	 * @param st2- A String value.
	 * @return A String value.
	 */
	private static String calculations(char operator,String st1,String st2) {
		BalancedTernary bt1=new BalancedTernary(st1);
		BalancedTernary bt2=new BalancedTernary(st2);
		switch(operator) {
		case '+': 
			return (bt1.add(bt2)).getValue();
		case '-':
			return (bt1.subtract(bt2)).getValue();
		case '*':
			return (bt1.multiply(bt2)).getValue();
		case '/':{
			if(bt2.bt_to_int()==0)
				return "Error: Cannot divide by 0!";
			else
				return (bt1.divide(bt2)).getValue();
		}
		case '%':{
			if(bt2.bt_to_int()==0)
				return "Error: Cannot get the remainder of the division by 0!";
			else 
				return (bt1.remainder(bt2)).getValue();
		}
		}
		return "";
	}
	
	/**
	 * This method identifies if a String value is an Integer.
	 * @param st- A String value.
	 * @return A boolean value.
	 */
	private static boolean isInteger(String st) {
	        return st.matches("-?\\d+");
	}
	
	/**
	 * This method returns the number of operators a equation has.
	 * @param st- A String value.
	 * @return An Integer value.
	 */
	
	private static int numOfOps(String st) {
		int count=0;
		for(int i=0;i<st.length();i++) {
			if(isOp(st.charAt(i))) 
				count++;
		}
		return count;
	}
	
	/**
	 * This method returns if an equation contains either a multiply, divide or modulo symbol.
	 * @param st- A String value.
	 * @return An Integer value.
	 */
	private static boolean containsMDM(String st) {
		return st.contains("*") || st.contains("/") || st.contains("%") ;
	}
	
	/**
	 * This method returns if a character value is either a multiply, divide or modulo symbol.
	 * @param c- A char value.
	 * @return A boolean value.
	 */
	
	private static boolean isMDM(char c) {
		return c=='*' || c=='/' || c=='%';
	}
	
	/**
	 * This method returns true if the provided String is a valid equation and returns false otherwise.
	 * @param st- A String value.
	 * @return A boolean value.
	 */
	
	private static boolean validEquation(String st) {
		for(int i=0;i<st.length();i++) {
			if(st.charAt(i)!='0' && st.charAt(i)!='1' && st.charAt(i)!='N' && st.charAt(i)!=' ' && !isOp(st.charAt(i)) && !st.contains("mem")) {
				return false;
			}
		}
		return true;
	}
}

