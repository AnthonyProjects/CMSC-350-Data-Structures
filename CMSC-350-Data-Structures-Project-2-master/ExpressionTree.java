
/** 
 *  Anthony Borza
 *  ExpressionTree Class: implements two expression trees, which are
 *  the OperandNode class, and the OperatorNode class. These classes
 *  illustrates how to use the OperatorNode and OprandNode classes to
 *  build an expression tree from postfix to infix.
 *  
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Stack;

public class ExpressionTree 
{
	private static Operator root;					// This implements the operators for the expression tree
	private static String opAddress;				// opAddress declared as a string and private
	private static String results;					// results declared as a string and static
	
	/** expression class that takes postfix as a string and its parameter
	 * 
	 * @param postfix
	 * @return
	 */
	
  public static String expression(String postfix) 
  {
          Stack<String> s = new Stack<String>();					// stack declared as a string
          Stack<TreeNode> operandStack = new Stack<TreeNode>();		// operand stack
        
      try  // try statement used for exception handling 
      { 
               File file;	// used as the name of file

               int j = 0;	// j declared as an int and assigned 0
               
			do{	// executes code at least once in the loop
				
                     j++;	// increments j
                     
                     file = new File("F:\\DataStructures\\Project 2\\srcThreeAddress" + j + ".txt");	// writes file to this location
               } 
               while(file.exists());	// file exists
				
               PrintWriter w = new PrintWriter("ThreeAddress" + j + ".txt");	// prints formatted representations of objects to a text-output stream.
               
               int count = 0;								// count declared as an integer and set to zero
               
               for(int i = 0; i < postfix.length(); i++)	// for loop continues as long as i is less than the size of postfix
               {
                     char token = postfix.charAt(i);		// returns the char value at the specified index.
                     
                     System.out.println(token);				// prints out token
                     
                     if (isValue(token)) 				// if token is a value or inotherwords a number. Calls the isValue Method
                     {
                           operandStack.push(new OperandNode(token));	// pushes token onto the top of this stack. 
                         
                     } 
                     else if(isOperator(token))			// else if token is an operator. Calls the isOperator method
                     {
                    	 TreeNode left  = operandStack.pop();						// pops onto the operandStack
                    	 TreeNode right = operandStack.pop();						// pops onto the operandStack
                    	 TreeNode element = new OperatorNode(root, left, right); 	// element is set to the OperatorNode method in the OperatorNode class        
                    	 
                         w.println(opAddress + "R" + count + " " + right.inOrderWalk() + " " + left.inOrderWalk());				// writes the three address instructions to a file
                         System.out.println(opAddress + "R" + count + " " + right.inOrderWalk() + " " + left.inOrderWalk());	// prints threeAddress (for testing purposes only)
                         
                         s.push(element.inOrderWalk());	// pushes
                         operandStack.push(element);	// pushs element on operandStack
                         count++; 						// increments counter 
                     }
                     else if (token == ' ') 		// else if token is equal to ''
                     {
                           // Do nothing 
                     }        
               } 
               
               w.close();		// close writer
           } 
      	catch (FileNotFoundException e) 	// throws FileNotFound Exception 
      	{
             System.out.println("No file");	// prints No file	
              
             System.exit(0); // terminates
      	}
      
      	results = s.pop();	// pops onto the s stack, which is declared as a string. 
    	return results;		// returns results
    } 
        
	 
	 /** isValue is a boolean method that takes token as its parameter and is declared as a character
	  * 
	  * @param token
	  * @return
	  */
	 
    public static boolean isValue(char token) 
    {
          if (Character.isDigit(token))	// if the character is a digit
          {
            	return true; // returns true 
          }
          else	// else 
          {
				return false; // returns false
          }
    }
    
      /** isOperator is a boolean method that takes token as its parameter and is declared as a character
       *  provides 4 cases for the following operators: +,-,*,/
       *  
       * @param token
       * @return
       */
    
	public static boolean isOperator(char token)
	{
		switch(token)	// has a number of posible exection paths
		{
	          case '+':  // possible case for execution
	          {
	        	  opAddress = "Add ";			// Add is displayed as a string
	              root = new AddOperator();		// inherits from the AddOperator class
	              return true;					// returns true
	          }
	          case '-': 						// possible case for execution
	          {
	        	  opAddress = "Sub ";			// Sub is displayed as a string
	              root = new SubOperator();		// inherits from the SubOperator class
	              return true;					// returns true
	          }
	          case '*': 						// possible case for execution
	          {
	        	  opAddress = "Mul ";			// Mul is displayed as a string
	              root = new MulOperator();		// inherits from the MulOperator class
	              return true;					// returns true
	          }
	          case '/': 						// possible case for execution
	          {
	        	  opAddress = "Div ";			// Div is displayed as a string
	              root = new DivOperator();		// inherits from the DivOperator class
	              return true;					// returns true
	          }
	          case ' ': 						// possible case for execution
	          default:							// default
	              return false; 				// returns false
	      }
	  }
	
}
