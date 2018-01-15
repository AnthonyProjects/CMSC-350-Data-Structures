
/**
 * Anthony Borza
 * OperandNode class that implements TreeNode
 * 
 */

public class OperandNode implements TreeNode 
{
    private int value;		// value declared as an integer and set as private
    
    public OperandNode(int value) 	// OperandNode declared as public and takes the parameter value declared as an integer
    {
      this.value = Character.getNumericValue(value);	// returns the int value that the specified character
    }
    
    public String inOrderWalk()
    {
        return String.valueOf(value);	// returns the string representation of the int argument value 
    }
    
 
}