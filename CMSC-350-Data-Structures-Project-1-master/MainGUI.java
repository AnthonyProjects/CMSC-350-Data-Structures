/** 
 * Anthony Borza
 * Project1: MainGUI class generates a GUI that allows the user to enter
 * a infix expression, and then have the results displayed.
 * 
 */

// imports used throughout class

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainGUI extends JFrame
{
	private final static int FRAME_WIDTH = 380;   			// frame width
	private final static int FRAME_HEIGHT = 140; 	 		// frame height
	private JLabel enterInfix ;								// JLabel declared as private and assigned n
	private JLabel results;									// JLabel declared as private and assigned results
	private JTextField resultsText;							// JTextField declared as private and assigned resultsText
	private JTextField infixText;							// JTextField declared as private and assigned infixText
	private JButton evaluateButton;							// JButton declared as private and assigned the name evaluate
	private InfixExpression infix = new InfixExpression();
	String currentValue;									// currentValue declared as a String
	
	/** Main GUI Constructor: creates the layout of the GUI
	 * 
	 */
	
	public MainGUI()
	{
		JPanel panel1 = new JPanel();						// new JPanel with the name panel1
		enterInfix = new JLabel("Enter Infix Expression");	// creates a JLabel instance with the specified text "Enter Infix Expression" 
		infixText = new JTextField(20);						// Constructs a new empty TextField for infixText
		panel1.add(enterInfix);								// adds enterInfix to panel1
		panel1.add(infixText);								// adds infixText to panel1
		
		JPanel panel2 = new JPanel();						// new JPanel with the name panel2
		evaluateButton = new JButton("Evaluate");			// creates a JButton with the specified text "Evaluate" 
		panel2.add(new JLabel());							// creates a JLabel with an empty string for the title
		panel2.add(evaluateButton);							// adds evaluate to panel2
		
		JPanel panel3 = new JPanel();						// new JPanel with the name panel3
		results = new JLabel("Result");						// creates a JLabel instance with the specified text "Result"
		resultsText = new JTextField(20);					// Constructs a new empty TextField for resultsText
		resultsText.setEditable(false);						// sets textArea so it is unable to be edited 
		panel3.add(results);								// adds results to panel3
		panel3.add(resultsText);							// adds resultsText to panel3
		
		add(panel1,BorderLayout.NORTH);						// adds panel1 and positions it NORTH using BorderLayout
		add(panel2,BorderLayout.CENTER);					// adds panel2 and positions it in the CENTER using BorderLayout
		add(panel3,BorderLayout.SOUTH);						// adds panel3 and positions it SOUTH using BorderLayout
		
		evaluateButton.addActionListener(new Press());		// registers action listener for evaluate button
		resultsText.addActionListener(new Press());			// registers action listener for evaluate button
		infixText.addActionListener(new Press());			// registers action listener for evaluate button

	}
	
	/** The Press class implements an Action Listener for the following:
 		* evaluateButton
 		* infixText
 		* resultsText
 	*/

	public class Press implements ActionListener
	{ 
		public void actionPerformed(ActionEvent event)
		{
			try		//try/catch block that catches various exceptions for invalid input
			{
				if(event.getSource()== evaluateButton)						// if the object on which the event initially occurred is equal to evaluateButton
				{
					int result;												// result declared as a integer
					currentValue = infixText.getText();						// gets the test entered into the "Enter Infix Expression" JtextField
					result = InfixExpression.expression(currentValue);		// gets the expression method from the InfixExpression class, and takes the currentValue
					resultsText.setText(Integer.toString(result));			// changes resultsText to a data type Integer, and takes the parameter result
				}
			}
			catch(ArithmeticException e)	// thrown when an exceptional arithmetic condition has occurred when trying to divide by zero
			{
				JOptionPane.showMessageDialog(null, "Cannot divide by zero");  // displays an information-message dialog says, Cannot divide by zero
			}
			catch(NumberFormatException e)	// thrown when an NumberFormatException condition has occurred when trying to enter in a non integer value
			{
				JOptionPane.showMessageDialog(null, "Enter only integer values");  // displays an information-message dialog says, Enter only integer values
			}
			catch(EmptyStackException e)	// thrown when an EmptyStackException condition has occurred when pressing the button with nothing entered in
			{
				JOptionPane.showMessageDialog(null, "The stack is empty");  // displays an information-message dialog says, The stack is empty
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
		frame.setTitle("Project 1: Infix Expression Evaluator");	// assigns Project 1: Infix Expression Evaluator as title to frame
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);					// sets GUI frame size
		frame.setLocationRelativeTo(null);							// sets location relative to null				
		frame.setVisible(true);										// sets frame as visible
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// closes on exit
	}

}
