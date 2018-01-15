
/**
 * Anthony Borza
 * Operator class,which is abstract and contains internal classes that extend the Operator class
 * This implements the operators for the expression tree
 * 
 * AddOperator: toString method to return a string representation of the operator
 * SubOperator: toString method to return a string representation of the operator
 * MulOperator: toString method to return a string representation of the operator
 * DivOperator: toString method to return a string representation of the operator
 */

public abstract class Operator 
{
	// abstract class 
}

class AddOperator extends Operator 		// extends Operator
{
    public String toString() 	// to string method
    {
        return "+";		// return "+"
    }
}
   
class SubOperator extends Operator 		// extends Operator
{
    public String toString()	// to string method
    {
        return "-";		// return "-"
    }
}

class MulOperator extends Operator 		// extends Operator
{
    
    public String toString()	// to string method
    {
        return "*";		// return "*"
    }
}
   
class DivOperator extends Operator		// extends Operator
{
    public String toString() 	// to string method
    {
        return "/";		// return "/"
    }
}