
/**
 * Anthony Borza
 * Project 3: MainGUI class: that performs a sort using a binary search
 * tree. The program is able to sort lists of integers or fractions in both
 * ascending and descending order. There are two sets of radio buttons for
 * both fractions and integers, and ascending and descending to determine
 * what type of data type to sort.
 *
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class MainGUI extends JFrame
{
	private final static int FRAME_WIDTH =500;   				// frame width
	private final static int FRAME_HEIGHT = 400; 	 			// frame height
	private JLabel originalList;								// JLabel declared as private and assigned originalList
	private JLabel sortedList;									// JLabel declared as private and assigned sortedList
	private JLabel numericTypeText;								// JLabel declared as private and assigned numericTypeText
	private JLabel sortOrderText;								// JLabel declared as private and assigned sortOrderText
	private JTextField sortedText;								// JTextField declared as private and assigned sortedText
	private JTextField originalText;							// JTextField declared as private and assigned originalText
	private JButton performSort;								// JButton declared as private and assigned performSort
	private JRadioButton ascendingRadioButton;					// JRadioButton declared as private and assigned ascendingRadioButton
	private JRadioButton descendingRadioButton;					// JRadioButton declared as private and assigned descendingRadioButton
	private JRadioButton integerRadioButton;					// JRadioButton declared as private and assigned integerRadioButton
	private JRadioButton fractionRadioButton;					// JRadioButton declared as private and assigned fractionRadioButton
	private BinarySearchTree ascending = new BinarySearchTree();	// accesses BinarySearchTree class
	private BinarySearchTree descending = new BinarySearchTree();	// accesses BinarySearchTree class
	private Fractions fractions = new Fractions();					// accesses Fractions class

	/**
	 * Main GUI Constructor: creates the layout of the GUI
	 */

	public MainGUI()
	{
		JPanel panel1 = new JPanel(new GridLayout(3,6,50,50));			// creates a first panel called panel1
		originalList = new JLabel("              Original List" );		// adds a new JLabel with Original List
		originalText = new JTextField(30);								// originalText declared as a JTextField
		sortedList = new JLabel("                Sorted List");			// adds a new JLabel with Sorted List
		sortedText = new JTextField(30);								// sortedText declared as a JTextField
		sortedText.setEditable(false);									// sortedText is not editable
		panel1.add(originalList);										// adds originalList to panel1
		panel1.add(originalText);										// adds orginalText to panel1
		panel1.add(sortedList);											// adds sortedList to panel1
		panel1.add(sortedText);											// adds sortedText to panel1

		JPanel panel2 = new JPanel();									// creates a second panel called panel2
		performSort = new JButton("Perform Sort");						// creates a new JButton with using the variable performSort, and will display Perform Sort on the button
		panel2.add(new JLabel());										// new JLabel with no parameter
		panel2.add(performSort);										// adds performSort Button to panel2

		JPanel panel3 = new JPanel(new GridLayout(3,3,3,3));			// Creates a thrid panel called panel3
		sortOrderText = new JLabel("  Sort Order");						// new JLabel with Sort Order as parameter
		numericTypeText = new JLabel(" Numeric Type ");					// new JLabel with Numeric Type as parameter
		ascendingRadioButton = new JRadioButton("Ascending", true);		// Radio Button Ascending is selected default when program is run
		integerRadioButton = new JRadioButton("Integer", true);			// Radio Button Integer is selected default when program is run
		descendingRadioButton = new JRadioButton("Descending");			// Radio Button Descending
		fractionRadioButton = new JRadioButton("Fraction");				// Radio Button Fraction

		ButtonGroup group = new ButtonGroup();							// ButtonGroup with the name group
		group.add(ascendingRadioButton);								// adds ascendingRadioButton to button group
		group.add(descendingRadioButton);								// adds descendingRadioButton to button group

		ButtonGroup group2 = new ButtonGroup();							// ButtonGroup with the name group2
		group2.add(integerRadioButton);									// adds integerRadioButton to button group2
		group2.add(fractionRadioButton);								// adds fractionRadioButton to button group2

		panel3.add(sortOrderText);										// adds sortOrderText to panel3
		panel3.add(numericTypeText);									// adds numericTypeText to panel3
		panel3.add(ascendingRadioButton);								// adds ascendingRadioButton to panel3
		panel3.add(integerRadioButton);									// adds integerRadioButton to panel3
		panel3.add(descendingRadioButton);								// adds descendingRadioButton to panel3
		panel3.add(fractionRadioButton);								// adds fractionRadioButton to panel3

		add(panel1, BorderLayout.NORTH);								// adds panel1 using BorderLayout NORTH
		add(panel2, BorderLayout.CENTER);								// adds panel2 using BorderLayout CENTER
		add(panel3, BorderLayout.SOUTH);								// adds panel3 using BorderLayout SOUTH

		performSort.addActionListener(new PerformSortListener());	// registers action listener for performSort button
	}

	/**
	 * The PerformSortListener class implements an Action Listener for the following:
	 	* 	integerRadioButton
	 	* 	fractionRadioButton
	 * 	The class sets the sequence type based on the button selected, either integer or fraction
	 */

	private class PerformSortListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)	// actionPerformed takes e as ActionEvent
		{
			if(integerRadioButton.isSelected())		// if statement for if the integerRadioButton is selected
			{
				performIntegerSort();				// calls performIntegerSort method
			}

			if(fractionRadioButton.isSelected())	// if statement for if the fractionRadioButton is selected
			{
				performFractionSort();				// calls performFractionSort method
			}
		}
	}

	/**
	 *  performFractionSort method is void declared as a private. This method contains
	 *  the necessary calling methods to sort the Fractions in ascending and descending
	 *  order.
	 */

	private void performFractionSort()
	{
		// contains various ways for manipulating an array by using the spit method
		// to all a space between numbers entered in the GUI

		List<String> parts = Arrays.asList(originalText.getText().split(" "));
		List<Fractions> fractionsList = new ArrayList<>();	// constructs and array list

		for(String part: parts)	// for each element part of type string in array parts
		{
			// splits the string, which is the two numbers for the fraction using the "/" as a delimiter

			String[] fraction = part.split("/");	// uses "/" as a delimiter for the fractions

			try		// exception handling
			{
				int val1 = Integer.parseInt(fraction[0]); // parses the string argument as a integer for fraction array
				int val2 = Integer.parseInt(fraction[1]); // parses the string argument as a integer for fraction array

				Fractions fractions = new Fractions(val1, val2);	// calls the Fractions class and takes to integers
				fractionsList.add(fractions);						// adds them to the list with the name fractionList
			}
			catch (NumberFormatException re)		// catches any errors from user input
			{
				JOptionPane.showMessageDialog(MainGUI.this, " Error: " + part + " Invalid format");	// prints error message, "Invalid format"

				return;	// return
			}
		}

		BinarySearchTree<Fractions> bst = new BinarySearchTree<>();	// calling BinarySearchTree class

		for(Fractions num : fractionsList)		// for each element num of type integer in array numbers
		{
			bst.insert(num);		// insert num into BinarySearchTree
		}

		if(ascendingRadioButton.isSelected())	// if statement for if the ascendingRadioButton is selected
		{
			bst.inOrder(bst.root);	// uses inOrder method from BinarySearchTree class to sort in ascending order
		}

		if(descendingRadioButton.isSelected())	// if statement for if the descendingRadioButton is selected
		{
			bst.descOrder(bst.root); // uses descOrder method from BinarySearchTree class to sort in descending order
		}

		sortedText.setText(bst.getOutput());	// sets the output to a string
	}

	/**
	 *  performIntegerSort method is void declared as a private. This method contains
	 *  the necessary calling methods to sort the integers in ascending and descending
	 *  order.
	 */

	private void performIntegerSort()
	{
		// contains various ways for manipulating an array by using the spit method
		// to all a space between numbers entered in the GUI

		List<String> parts = Arrays.asList(originalText.getText().split(" "));
		List<Integer> numbers = new ArrayList<>();	// constructs and array list

		for(String part: parts)		// for each element part of type String in array parts
		{
			try		// exception handling
			{
				int num = Integer.parseInt(part);	// parses the string argument as a signed decimal integer
				numbers.add(num);					// adds number to the list
			}

			catch (NumberFormatException e)		// catches input error from user
			{
				// displays a message dialog box for Non Numeric Input

				JOptionPane.showMessageDialog(MainGUI.this, "Error: " + part + "  Is Non Numeric Input");

				return;	// return
			}
		}

		BinarySearchTree<Integer> bst = new BinarySearchTree<>();	// calling BinarySearchTree class

		for(int num : numbers)		// for each element num of type integer in array numbers
		{
			bst.insert(num);		// insert num into BinarySearchTree
		}

		if(ascendingRadioButton.isSelected())	// if statement for if the ascendingRadioButton is selected
		{
			bst.inOrder(bst.root);	// uses inOrder method from BinarySearchTree class to sort in ascending order
		}

		if(descendingRadioButton.isSelected())	// if statement for if the descendingRadioButton is selected
		{
			bst.descOrder(bst.root); // uses descOrder method from BinarySearchTree class to sort in ascending order
		}

		sortedText.setText(bst.getOutput());	// sets the output to a string
	}

	/**	Main class used to execute the program
	 *
	 * @param args
	 */

	public static void main(String[] args)
	{
		MainGUI frame = new MainGUI();								// new frame object
		frame.setTitle("Project 3: Binary Search Tree Sort");		// assigns "Project 3: Binary Search Tree Sort" as title to frame
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);					// sets GUI frame size
		frame.setLocationRelativeTo(null);							// sets location relative to null
		frame.setVisible(true);										// sets frame as visible
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// closes on exit
	}

}
