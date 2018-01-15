/** 
 * Anthony Borza
 * Project2: MainGUI class generates a GUI that allows the user to enter
 * in a postfix expression and have the infix expression display. 
 * 
 */

// imports used throughout class

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainGUI extends JFrame
{
	private final static int FRAME_WIDTH = 400;   				// frame width
	private final static int FRAME_HEIGHT = 150; 	 			// frame height
	private JLabel enterPostfix;								// JLabel declared as private and assigned enterPostfix
	private JLabel results;										// JLabel declared as private and assigned results
	private JTextField infixExpressionText;						// JTextField declared as private and assigned infixExpression
	private JTextField postfixText;								// JTextField declared as private and assigned postfixText
	private JButton constuctTreeButton;							// JButton declared as private and assigned the name constuctTreeButton
	private ExpressionTree exp = new ExpressionTree();		
	private static char val;
	
	/** Main GUI Constructor: creates the layout of the GUI
	 * 
	 */
	
	public MainGUI()
	{
		JPanel panel1 = new JPanel();							// new JPanel with the name panel1
		enterPostfix = new JLabel("Enter Postfix Expression");	// creates a JLabel instance with the specified text "Enter Postfix Expression" 
		postfixText = new JTextField(20);						// Constructs a new empty TextField for postfixText
		panel1.add(enterPostfix);								// adds enterPostfix to panel1
		panel1.add(postfixText);								// adds postfixText to panel1
		
		JPanel panel2 = new JPanel();							// new JPanel with the name panel2
		constuctTreeButton = new JButton("Construct Tree");		// creates a JButton with the specified text "Construct Tree"
		panel2.add(new JLabel());								// creates a JLabel with an empty string for the title
		panel2.add(constuctTreeButton);							// adds evaluate to panel2
		
		JPanel panel3 = new JPanel();							// new JPanel with the name panel3
		results = new JLabel("Infix Expression");				// creates a JLabel instance with the specified text "Infix Expression"
		infixExpressionText = new JTextField(20);				// Constructs a new empty TextField for infixExpressionText
		infixExpressionText.setEditable(false);					// sets textArea so it is unable to be edited 
		panel3.add(results);									// adds results to panel3
		panel3.add(infixExpressionText);						// adds resultsText to panel3
		
		add(panel1,BorderLayout.NORTH);							// adds panel1 and positions it NORTH using BorderLayout
		add(panel2,BorderLayout.CENTER);						// adds panel2 and positions it in the CENTER using BorderLayout
		add(panel3,BorderLayout.SOUTH);							// adds panel3 and positions it SOUTH using BorderLayout
		
	    constuctTreeButton.addActionListener(new Press());		// registers action listener for constuctTreeButton button
	    infixExpressionText.addActionListener(new Press());		// registers action listener for infixExpressionText
	    postfixText.addActionListener(new Press());				// registers action listener for postfixText
	    
	 
	}

	/** The Press class implements an Action Listener for the following:
		* constuctTreeButton
		* infixExpressionText
		* postfixText
	*/

	public class Press implements ActionListener
	{ 
		public void actionPerformed(ActionEvent event) throws NumberFormatException
		{   
			try
			{ 	
				if(event.getSource()== constuctTreeButton)			// if the object on which the event initially occurred is equal to constuctTreeButton
				{
					String post = postfixText.getText();		    // gets the text entered into the "Enter Postfix Expression" JtextField	
					
					for (int i = 0; i < post.length(); i++)			// for loop continues as long as i is less than the length of post
					{
						val = post.charAt(i);						// returns the char value at the specified index. An index ranges from 0 to length() - 1
						
						if(!Character.isDigit(val)&& !"+-/*".contains(Character.toString(val)))	// character not a digit or operator
						{
							throw new RuntimeException();			// throws a runtime exception 
						}
					}
					String result;									// reuslt declared as a string
					result = ExpressionTree.expression(post);		// gets the expression method from the ExpressionTree class, and takes the string post
					infixExpressionText.setText(result);			// sets text to results which is declared as a string
				}
			}
			catch(RuntimeException e)	// thrown when an RuntimeException condition has occurred when trying to enter in a invalid token
			{
				JOptionPane.showMessageDialog(null, "Invalid Token "  + val);  // displays an information-message dialog says, Invalid token

			}
		}
	}
	
	/**	Main class used to execute the program
	 * 
	 * @param args
	 */
		
	public static void main(String[] args)
	{
		MainGUI frame = new MainGUI();								// new frame object
		frame.setTitle("Project 2: Three Adddress Generator");		// assigns "Project 2: Three Adddress Generator" as title to frame
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);					// sets GUI frame size
		frame.setLocationRelativeTo(null);							// sets location relative to null				
		frame.setVisible(true);										// sets frame as visible
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// closes on exit
	}

}

