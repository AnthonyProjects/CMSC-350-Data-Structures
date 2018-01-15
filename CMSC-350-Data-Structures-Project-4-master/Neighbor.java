
/**
 * Anthony Borza
 * Neighbor Class: This class acts like a linked list. Its goal is to add 
 * elements to a linked list, and also check to see if an element is already 
 * present to avoid any duplicity. 
 * 
 */

public class Neighbor
{
	public int vertexNumber;	// This is destination of edge that Neighbor represent.
	public Neighbor next;		// This is to point to next element so a linked list can be made.

	/** Constructor, this adds element in linked list
	 * @param vertexNum
	 * @param neighbor
	 */

	public Neighbor(int vertexNum, Neighbor neighbor)
	{
		this.vertexNumber = vertexNum;	// reference to vertexNum
		next = neighbor;				// next vertex in adjacent list in the neighbors class
	}

	/**
	 * isPresent method is a function to check if element is already present
	 * in linked list so to avoid duplicity
	 * @param vertex
	 * @return true, and flase
	 */

	public boolean isPresent(int vertex)
	{
		Neighbor trav = this;	// inherits from the Neighbor class

		while(trav != null)		// while trav is not equal to null (null check)
		{
			if(trav.vertexNumber == vertex)	// if destination of edge equals vertex, which is an integer
			{
				return true;	// returns true
			}

			trav = trav.next;	// points to next element in linked list
		}
		return false;	// returns false
	}
}