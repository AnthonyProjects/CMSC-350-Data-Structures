
/** 
 *  Anthony Borza
 *  This is the OperatorNode class that implements TreeNode. 
 *  It evaluates the node, and prints the tree in the inorder format.
 */

public class OperatorNode implements TreeNode
{
	private TreeNode left;		// left declared as private and inherits from the TreeNode class
	private TreeNode right;		// right declared as private and inherits from the TreeNode class
    private Operator operator;	// operator declared as private and inherits from the Operator class
    private String leftValue;	// leftValue declared as a string and is private
    private String rightValue;	// rightValue declared as a string and is private
    
    /** public operatorNode method
     * 
     * @param operator
     * @param left
     * @param right
     */
    
    public OperatorNode(Operator operator, TreeNode left, TreeNode right)
    {
        this.operator = operator;	// reference to operator
        this.left = left;			// reference to left
        this.right = right;			// reference to right
    }
    
    public String inOrderWalk() 
    {
        leftValue = left.inOrderWalk();			// leftValue declared as a 
        rightValue = right.inOrderWalk();
        return "(" + rightValue + " " + operator + " " + leftValue + ")";	// returns the follwoing defined up above
    }
    
}
