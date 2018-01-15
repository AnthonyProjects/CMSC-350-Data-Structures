
/**
 * Anthony Borza
 * Project 3: TreeNode class: is used and stored in the Binary Search Tree class
 *
 */

public class TreeNode<E>
{
	 public E element;				// element declared as generic
	 public TreeNode<E> left;		// left declared as public
	 public TreeNode<E> right;		// right declared as public

	/**
	 * TreeNode constructor:
	 *
	 * @param value
	 */

	 public TreeNode(E value)
	 {
		 this(value, null, null);
	 }

	/**
	 * TreeNode method:
	 *
	 * @param value
	 * @param left
	 * @param right
	 */

	 public TreeNode(E value, TreeNode<E> left, TreeNode<E> right)
	 {
		  element = value;			// value is generic
		  this.left = left;			// reference to left
		  this.right = right;		// reference to right
	 }


}


