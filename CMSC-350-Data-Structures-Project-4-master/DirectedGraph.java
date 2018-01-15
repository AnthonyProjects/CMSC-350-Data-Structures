
/**
 * Anthony Borza
 * DirectedGraph Class: This class allows for vertex names. In this application
 * those vertex names will represent strings. The graph is represented in an array
 * list of vertices that conatin a linked list of their associated adjacenct lists. 
 */

// imports used

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DirectedGraph
{
	Map<Integer, Vertex> vertices = null;       // maps keys to values
	public static int currentIndex;             // currentIndex declared as static
	Vertex[] topoOrderVertices;                 // inherits from the Vertex class

	/**
	 * Graph method to initialize graph each time a new file is read in
	 * @param file
	 * @throws FileNotFoundException
	 */

	public DirectedGraph(String file) throws FileNotFoundException
	{
		// scanner used to read in file, and tests that the Depth First Search and topologySort
		// method work and is displayed in console

		try(Scanner sc = new Scanner(new FileInputStream(file)))
		{
			String graph = null;                   	 	// graph declared as a string (null check)
			vertices = new HashMap<>();             	// constructs an empty HashMap

			while(sc.hasNextLine())                		// scans next line of file
			{
				graph = sc.nextLine();              	// scans next line
				String[] names = graph.split(" "); 		// string array of names, and uses a space as delimiter

				if(names != null)      					// if names is not equal to null (null check)
				{
					Vertex vertex = null;               // null check for vertex
					String verName = names[0].trim();   // removes any white space
					int vertexId = -1;                  // vertexId is declared as an int

					for (int key: vertices.keySet())     // for each element of vertices of type key
					{
						if (vertices.get(key).name.equals(verName))  // returns value of specific key that is mapped
						{
							vertex = vertices.get(key);     // returns value of specific key that is mapped to vertex
							vertexId = key;
							break;                          // break
						}
					}
					if(vertex == null)  		// if names is equal to null (null check)
					{
						vertexId = vertices.size();         // number of key-value mappings in the vertexID map
						vertex = new Vertex(verName);       // new vertex
						vertices.put(vertexId, vertex);     // associates the specified value with the specified key in the map
					}
					for(int i = 1; i < names.length; ++i)  	// continues as long as i is less then the length of the names
					{
						String neighborName = names[i].trim();  // removes any white space
						int neighborId = -1;                    // neighborId is an int and equal to -1
						Vertex curNeighbor = null;              // null check for curNeighbor

						for(int key: vertices.keySet())         // for each element of vertices of type key
						{
							if(vertices.get(key).name.equals(neighborName)) // returns value of specific key that is mapped
							{
								curNeighbor = vertices.get(key); // returns value of specific key that is mapped to vertex
								neighborId = key;
								break;  // break
							}
						}
						if(curNeighbor == null)    // if curNeighbor is equal to null (null check)
						{
							neighborId = vertices.size();   		// number of key-value mappings in the neighborID map
							curNeighbor = new Vertex(neighborName); // new curNeighbor
							vertices.put(neighborId, curNeighbor);  // associates the specified value with the specified key in the map
						}
						if(!vertex.isPresent(neighborId))       	// if vertex is not present
						{
							vertex.addNeighbor(neighborId);     	// adds edge in linked list
						}
					}
				}
			}
			sc.close(); // closes scanner
		}
	}

	/**
	 * Vertex isVertexPresent method: checks to see if a vertex is present
	 * @param in
	 * @return null
	 */

    private Vertex isVertexPresent(String in)
	{
    	for(int key: vertices.keySet())		// for each element of vertices of type key
    	{
    		if(vertices.get(key).name.equals(in))	 // returns value of specific key that is mapped
    		{
    			return vertices.get(key);	// returns value of specific key that is mapped to vertex
    		}
    	}
    	return null;	// return null (null check)
    }

	/**
	 *  printHashMap Method
	 */

	private void printHashMap()
	{
    	for(int key: vertices.keySet())	// for each element of vertices of type key
    	{
    		System.out.print(key + " " + vertices.get(key).name + " ");	// returns value of specific key that is mapped
    		
    		Neighbor trav = vertices.get(key).adjList;	// returns value of specific key that is mapped
    		
    		while(trav != null)		// while trav is not equal to null
			{
    			System.out.print(trav.vertexNumber + " ");	// prints vertexNumber
    			trav = trav.next;	// gets next element in linked list from neighbors class
    		}
    		System.out.println();	// prints new line
    	}
    }

	/**
	 * depthFirstSearch method that uses recursion. The dept-first search of a
	 * graph starts from a vertex in the graph and visits all vertices in the 
	 * graph as far as possible before backtracking.
	 * Time complexity is O(V+E)
	 * 
	 * @param vertex
	 * @param isVisited
	 * @throws CycleDetectedException
	 */

    private void depthFirstSearch(int vertex, boolean[] isVisited) throws CycleDetectedException
    {
        isVisited[vertex] = true;	// true if visited
        System.out.print(vertices.get(vertex).name + " ");	// prints the value of specific key that is mapped
        
        // gets next vertex in Neighbor class, null check for Neighbor class, points to next
        // vertex in linked list
        
        for(Neighbor neighbor = vertices.get(vertex).adjList; neighbor != null; neighbor = neighbor.next)
        {
        	// if vertex is equal to the number of verticies in the Neighbor class
        	if(vertex == neighbor.vertexNumber) throw new CycleDetectedException();	
        	
            if(!isVisited[neighbor.vertexNumber])	// if not visited show number of verticies
            {
            	// prints the value to which the specific key is mapped
            	System.out.println(vertices.get(vertex).name + "--" + vertices.get(neighbor.vertexNumber).name);

                depthFirstSearch(neighbor.vertexNumber, isVisited);	// calls the recursive helper
            }
        }
        System.out.println("Adding " + vertices.get(vertex).name);	// prints the added value of specific key that is mapped
        topoOrderVertices[currentIndex++] = vertices.get(vertex);	// returns value of specific key that is mapped
    }

	/**
	 * topologySort method: this prints a vertex before its adjacent vertices.
	 * This is a directed graph that is a linear ordering of all of its verticies,
	 * such that for every directed graph uv from vertex u to vertex v, u comes 
	 * before v in that ordering.
	 * Time complexity is O(|E| +|V|)
	 * Running Time: O(V + E)
	 * 
	 * @param source
	 * @return ret
	 * @throws CycleDetectedException
	 */

    public String topologySort(String source) throws CycleDetectedException
    {
        boolean[] visited = new boolean[vertices.size()];	// see if a vertex has been visited
        topoOrderVertices = new Vertex[vertices.size()];	// gets the size of the vertices

        currentIndex = 0;	// currentIndex is zero
        int start = -1;		// start is declared as an integer and is -1
        
        for(int key: vertices.keySet())	// for each element of vertices of type key
        {
        	if(vertices.get(key).name.equals(source))	// returns value of specific key that is mapped
        	{
        		start = key;	// at -1
        		break;			// break
        	}
        }
        if(start == -1)		// if start is equal to -1
        {
			return null;	// return null (null check)
		}

        depthFirstSearch(start, visited);
        System.out.println(currentIndex);	// prints currentIndex
        
        if(currentIndex == 0)				// if currentIndex is equal to zero
        {
        	topoOrderVertices = null;		// null check for topoOrderVertices
        	return "";						// return a string of ""
        }
        
        Vertex[] temp = new Vertex[currentIndex];	// new vertex
        String ret = "";	// ret declared as a string
        
        for(int i = 0; i < currentIndex; ++i)	// continues as long as i is less than currentIndex
        {
        	temp[i] = topoOrderVertices[i];
        	ret += topoOrderVertices[currentIndex - i - 1].name + " ";
        }
        
        topoOrderVertices = temp;
        return ret;					// returns ret, which was declared as a string
    }

    /** Main Method used to prove that it compiles properly */

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);					// scanner is open
        String file = "graph.txt"; 								// file delared as a string and given a file
        DirectedGraph graph = new DirectedGraph(file);			// uses the methods in the Graph class
        graph.printHashMap();									// prints hashmap method that is in the Graph class

		try	// exception handling
		{
			System.out.println(graph.topologySort("ClassA"));	// pints topology sort that starts at ClassA
		}
		catch (CycleDetectedException e)	// CycleDetectedException
		{
			System.out.println(e.toString());	 // shows a string format of why exception was thrown
		}
        sc.close();	// closes the scanner
    }
}