
/**
 * Anthony Borza
 * Vertex Class: used to check and add edges to the linked list Neighbor.
 * It's goal is to check if an edge is already present in the linked list.
 * It also has the job of adding an edge in the linked list.
 * 
 */

public class Vertex
{
    String name;		 // name declared as a string
    Neighbor adjList;	// This is linked list that contain all the vertices that has an edge with this vertex

	/** This is Constructor for Vertex class
	 * @param name
	 */

	Vertex(String name)
    {
        this.name = name;		 // reference to name
        this.adjList = null;	 // reference to null
    }

	/** isPresent boolean method: checks if edge is already present in linked list
	 * @param vertex
	 */

	public boolean isPresent(int vertex)
	{
    	if(adjList == null)		 // if adjList is equal to null (null check)
    	{
			return false;		// return false
		}
    	return adjList.isPresent(vertex);	// else will return that the element is present
    }

	/** addNeighbor method is for adding an edge in linked list
	 * @param vertex
	 * return
	 */

    public void addNeighbor(int vertex)
	{
    	if(adjList == null)		// if adjList is equal to null (null check)
    	{
    		adjList = new Neighbor(vertex, adjList);
    		return;		// return
    	}
    	
    	Neighbor trav = adjList, prev = null;
    	
    	while(trav != null)		// while trav is not equal to null
		{
    		prev = trav;
    		trav = trav.next;	// points to next element in linked list
    	}
    	
    	prev.next = new Neighbor(vertex, null);	// adds element in linked list
    }

}
