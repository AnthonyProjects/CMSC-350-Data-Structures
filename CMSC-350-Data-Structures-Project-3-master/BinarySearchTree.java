/**
 * Anthony Borza
 * Project 3: BinarySearchTree class that is generic. This class
 * uses a method to initialize the tree, that allows a new value
 * to be insert into the tree and one that performs an inorder tree
 * traversal that generates and returns a string that contains the
 * elements in sorted order. The insert method allows duplicates to
 * be inserted, and the method uses recursion. This class does not
 * contain a delete method or check method.
 *
 */

public class BinarySearchTree<E>
{
	public TreeNode<E> root;	// root declared as public, and is associate with the TreeNode class

	private StringBuilder output = new StringBuilder();	// Creates an empty string builder with a capacity of 16 empty elements.

	/**
	 * getOuput method
	 * @return the output to a string
	 */

	public String getOutput()
	{
		return output.toString();	// output to a string
	}

	/**
	 *  Default constructor
	 */

	public BinarySearchTree()
	{
		root = null;
	}

	/**
	 * insert method:
	 *
	 * @param data
	 * @return
	 */

	public TreeNode<E> insert(E data)
	{
		root = insertTree(root, data);	// uses recursive approach to insert elements into tree
		return root;	// return root
	}

	/**
	 *   This uses the recursive approach for inserting elements in the tree.
	 * 	 This function adds new elements to the tree, which corresponds to the
	 * 	 root. Each level of the tree will check  if the element that is added
	 * 	 is smaller; thus moving it to the left-subtree, or greater, moving it
	 * 	 to the right subtree.
	 *
	 * @param node
	 * @param value
	 * @return node
	 *
	 */

	public TreeNode<E> insertTree(TreeNode<E> node, E value)
	{
		if (node == null)		// if node is equal to null
		{
			return node = new TreeNode<E>(value);	// return
		}
		if (((Comparable)value).compareTo((Comparable)node.element) < 0) 	// if value is less than node.element
		{
			node.left = insertTree(node.left, value);	// uses recursive approach to insert elements into tree
		}
		else 	// else
		{
			node.right = insertTree(node.right, value);	// uses recursive approach to insert elements into tree
		}
		return node;	// return node
	}

	/**
	 * Inorder Approach (Left -> Root -> Right) of the tree
	 * Gets elements in ascending order. This is also known
	 * as the recursive helper.
	 *
	 * @param node
	 **/

	public void inOrder(TreeNode<E> node)
	{
		if (node != null) 	// if node is not equal to null
		{
			inOrder(node.left);		// calls inorder on the leftmost node
			output.append(node.element).append(" ");	// appends a space between each node
			System.out.print(node.element + " ");	// print statement for nodes
			inOrder(node.right);	// calls inorder on the rightmost node
		}
	}

	/**
	 * Descending Order Approach ( Right -> Root -> Left)
	 * Gets elements in descending order. This is also known
	 * as the recursive helper.
	 *
	 * @param node
	 **/

	public void descOrder(TreeNode<E> node)
	{
		if (node != null)  	// if node is not equal to null
		{
			descOrder(node.right);	// calls descOrder on the leftmost node
			output.append(node.element).append(" ");	// appends a space between each node
			System.out.print(node.element + " ");	// print statements for nodes
			descOrder(node.left);	// calls descOrder on the rightmost node
		}
	}
}