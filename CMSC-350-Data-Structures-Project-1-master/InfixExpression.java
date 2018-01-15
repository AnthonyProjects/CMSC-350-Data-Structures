/** 
 * Anthony Borza
 * Project1: InfixExpression class performs the infix expression evaluation.
 * The class is only expected to perform correctly syntactically correct
 * infix expressions that contain integer operands and the four arthmetic 
 * operators "+ - *  /" 
*/

//imports used

import java.util.Stack;
import java.util.StringTokenizer;

public class InfixExpression
{
	static int result = 0;		// result used multiple times throughout the whole class
	static int value = 0;		// value used multiple times throughout the whole class
	static int order = 0;

	/** Expression method evaluates infix expressions
	 * @param infix
	*/
	
	public static int expression(String infix)
	{
		String  token;			// token declared as a String
		String  operator;		// operator declared as a String
		Integer operand1;		// operand1 declared as a Integer
		Integer operand2;		// operand2 declared as a Integer
		int value = 0;			// value declared as an integer and set to zero
		
		infix = "(" + infix + ")"; 			// adds parenthesis around infix
		infix = infix.replace(" ", ""); 	// replaces each substring of this string that matches the literal target
		
		StringTokenizer tokenizer = new StringTokenizer(infix, "()*/+-^", true); 		// able to break a string into tokens
		Stack<String> operatorStack = new Stack<String>();								// stack created for operator
		Stack<Integer> operandStack = new Stack<Integer>();								// stack created for operand
		
		while(tokenizer.hasMoreTokens())	// sees if there are more tokens available from this tokenizer's string.
		{
			token = tokenizer.nextToken();	// returns the next token from this string tokenizer.
			
			if(!operator(token) && !token.equals("(") && !token.equals(")"))	// if token is not an operator(in other words, it is a number)
			{
				value = Integer.parseInt(token);	// parses the string argument token as a integer with the name value
				operandStack.push(value); 			// pushes value onto the operandStack
			}
			
			else if(token.equals("(")) 				// if token is equal to left parenthesis
			{
				operatorStack.push(token);			// push onto the operatorStack
			}
	
			else if(token.equals(")"))				// if token is equal to right parenthesis
			{
				// while top of the operator stack not a left parenthesis
				
				while(operatorStack.peek().equals("(") == false)	// peeks to make sure it is not a left parenthesis
				{
					operator = (String)operatorStack.peek();operatorStack.pop();  // peeks at the operator and then pops it to operatorStack
					operand1 = (int) operandStack.peek(); operandStack.pop();	  // peeks at the operand and then pops it to operandStack
					operand2 = (int) operandStack.peek();operandStack.pop();	  // peeks at the operand and then pops it to operandStack
					evaluate(operator, operand1, operand2);					      // use the evaluate method to perform calculations
					operandStack.push(result);									  // pushes the results onto the operandStack
				}
				operatorStack.pop();	// pops from the operatorStack
			}
			
			else if(operator(token))	// if it is a operator
			{
				// while the operator stack is not empty and the operator at the top of the stack has higher
				// or the same precedence that the current operator
				
				while(operatorStack.isEmpty() == false && precedence(operatorStack.peek()) >= precedence(token))
				{
					operator = operatorStack.peek(); operatorStack.pop();	 // peeks at the operator and then pops it to operatorStack
					operand1 = operandStack.pop(); 						 	 // peeks at the operand and then pops it to operandStack
					operand2 = operandStack.pop(); 						 	 // peeks at the operand and then pops it to operandStack
					evaluate(operator, operand1, operand2);				 	 // use the evaluate method to perform calculations
					operandStack.push(result);								 // pushes the results onto the operandStack
				}
				operatorStack.push(token); 									 // pushes the token onto the operatorStack
			}
		}
		result = (int)operandStack.peek(); 	// the final result is at the top of the operandStack
		return result;						// returns result
	}
	
	/** The operator method is of type boolean, and returns true or false
	 * 
	 * @param op
	 * @return both true and false 
	 */
	
	public static boolean operator(String op)
	{
		if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) // if op equals "+" or "-" or "*" or "/"
		{
			return true;	// returns true
		}
		else 
		{
			return false;	// returns false
		}
	}

	/**Precedence method follows the rules of the order of operation:
	 * PEMDAS
	 * Parenthases, Exponents, Multiplicaiton, Division, Addition, Subtraction
	 * @param token
	 * @return
	 */
	public static int precedence(String token)
	{
		if (token.equals("(") || token.equals(")"))		// if token equals "(" or ")"
		{
			order = 0; // order is equal to 0
		}
		else if (token.equals("*") || token.equals("/")) // if token equals "*" or "/"
		{
			order = 3; // order is equal to 3
		}
		else if (token.equals("+") || token.equals("-")) // if token equals "+" or "-"
		{
			order = 2; // order is equal to 3
		}
		else
		{
			order = 1; // order is equal to 1
		}
		return order; 	// return order
	}

	/** This evaluate method is used to calculate and return the results
	 *  of the infix expression
	 * @param operator1
	 * @param operator2
	 * @param operator3
	 * @return
	 */
	public static int evaluate(String operator, int operand1, int operand2)
	{
		if (operator.equals("+"))			// if operator is equal to "+"
		{
			result = operand1 + operand2;	// operator is the value from operand1 + operand2;
		}
		else if (operator.equals("-"))		// if operator is equal to "-"
		{
			result = operand2 - operand1;	// result is the value from operand2 - operand1;
		}
		else if (operator.equals("*"))		// if operator is equal to "*"
		{
			result = operand1 * operand2;	// result is the value from operand1 * operand2;
		}
		else if (operator.equals("/"))		// if operator is equal to "/"
		{
			result = operand2 / operand1;	// result is the value from operand2 / operand1;
		}
		return result; 						// returns result
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------//	
	/** Main method for this class is used for testing purposes only to 
	 *  verify that the infix expression works
	 * @param args
	 */
	
//	public static void main(String[] args)
//	{
//	    System.out.println(InfixExpression.expression("(2 + 3 * 5)-8/5*(5-2)"));	// proof of concept that it works
//	    System.out.println(InfixExpression.expression("(2 + 10 * 7)-8/5*(4-2)"));	// proof of concept that it works
//	    System.out.println(InfixExpression.expression("50 * (30 + 30) / 6"));		// proof of concept that it works
//	}
}
