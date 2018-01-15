
/**
 * Anthony Borza
 * CycleDetectedException Class: detects any clycles that take place during execution
 * of a program
 * 
 */

public class CycleDetectedException extends Exception
{
	// universe identifer for a serializable class
	// the number 1L is uesd as a deserializer to ensure that 
	// the loaded class corresponds exactly to a serialized object
	
	private static final long serialVersionUID = 1L;	

	/** CycleDetectedException method */

	public CycleDetectedException()
	{
		super("Cycle Detected");	// constructs a new exception with a message of "Cycle Detected"
	}
}
