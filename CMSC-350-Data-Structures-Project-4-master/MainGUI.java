
/**
 * Anthony Borza
 * MainGUI Class: This class behaves like the java command line compiler.
 * Whenever, a user request that the java compiler recomile a particular 
 * class, it not only recompiles that class but every other class that 
 * depends upon it, directly or indirectly, and in a particular order.
 * Inorder to make the determination about which classes need recomplication,
 * the java compiler maintains a directed graph of class dependencies. 
 * 
 */

// imports used

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainGUI extends JFrame
{
    private final static int FRAME_WIDTH = 575;   		// frame width
    private final static int FRAME_HEIGHT = 300; 	 	// frame height
    private JLabel inputFileName;                       // JLabel declared as inputFileName
    private JLabel classToRecompile;                    // JLabel declared as classToRecomplile
    private JTextArea textArea;                         // JTextArea declared as textArea
    private JTextField inputFile;                       // JTextField declared as inputFile
    private JTextField classRecompile;                  // JTextField declared as classRecompile
    private JButton buildGraphButton;                   // JButton declared as buildGraphButton
    private JButton topologicalOrderButton;             // JButton declared as topologicalOrderButton
    private JPanel panel1, panel2, panel3;              // JPanels: panel1, panel2, panel3
    private DirectedGraph graph = null;                 // Graph declared as private
    private ButtonActionListener button = null;         // button declared as private

    /**  Main Constructor for GUI components **/

    public MainGUI()
    {
        panel1 = new JPanel();              							// new JPanel name panel1
        inputFileName = new JLabel("            Input File Name:");  	// "JLabel Input File Name:"
        inputFile = new JTextField(15);     							// new JTextField
        buildGraphButton = new JButton("Build Directed Graph");    		// JButton named "Build Directed Graph"
        panel1.add(inputFileName);          							// adds inputFileName to panel1
        panel1.add(inputFile);             								// adds inputFile to panel1
        panel1.add(buildGraphButton);       							// adds buildGraphButton to panel1

        panel2 = new JPanel();                  				 		// new JPanel name panel2
        classToRecompile = new JLabel("Class to recompile:");    		// JLabel "Class to recompile:"
        classRecompile = new JTextField(15);   							// new JTextField
        topologicalOrderButton = new JButton("Topological Order");
        panel2.add(classToRecompile);           						// adds classToRecompile to panel2
        panel2.add(classRecompile);             						// adds classRecompile to panel2
        panel2.add(topologicalOrderButton);     						// adds topologicalOrderButton to panel2

        panel3 = new JPanel();               							// new JPanel name panel3
        textArea = new JTextArea(9,50);      							// new JTextArea named textArea
        textArea.setEditable(false);        							// textArea cannot be edited
        TitledBorder title = new TitledBorder("Recompilation Order");   // TitledBorder "Recompilation Order"
        panel3.setBorder(title);            							// adds title to panel3
        panel3.setBackground(Color.white);  							// sets panel3 background to white
        panel3.add(textArea);               							// adds textArea to panel3

        add(panel1,BorderLayout.NORTH);     							// panel1 uses BorderLayout NORTH
        add(panel2,BorderLayout.CENTER);   								// panel2 uses BorderLayout CENTER
        add(panel3,BorderLayout.SOUTH);    					 			// panel3 uses BorderLayout SOUTH

        button = new ButtonActionListener();               				// button register as a ButtonActionListener
        buildGraphButton.addActionListener(button);        				// buildGraphButton registers as a ActionListener
        topologicalOrderButton.addActionListener(button);  				// topologicalOrderButton registers as a ActionListener
    }

    /**
     * A ButtonActionListener method that implements ActionListener
     */

    private class ButtonActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == buildGraphButton)  // if buildGraphButton is selected
            {
                String s = null;	// 's' declared as a string (null check)

                try		// exception handling 
                {
                    Path currentRelativePath = Paths.get("");							// gets the path of the file
                    s = currentRelativePath.toAbsolutePath().toString();				// returns absolute path as a string
                    System.out.println("Current relative path is: " + s);				// prints path to file
                    graph = new DirectedGraph(inputFile.getText().trim());				// gets the input file and removes any whitespace
                    JOptionPane.showMessageDialog(null, "Graph Built Successfully\n");	// prints message
                }
                catch (FileNotFoundException e1)	// FileNotFoundException
                {
                    JOptionPane.showMessageDialog(null, "File Not Found! " + "File Did Not Open!" );	// displays error message
                }

            }
            else if(e.getSource() == topologicalOrderButton)    // else if topologicalOrderButton is selected
            {
            	if(graph == null)   // if graph is null (null check)
            	{
            		JOptionPane.showMessageDialog(null, "Build Graph First");   // displays message
            		return;     // return
            	}
				try     // exception handling
                {
					String topoOrder;   // topOrder declared as a string
					topoOrder = graph.topologySort(classRecompile.getText().trim()); // recompile's class entered

					if(topoOrder == null)   // if topoOrder is null (null check)
					{
	            		JOptionPane.showMessageDialog(null, "Invalid Class Name");  // displays error message
	            	}
	            	else   // else
	            	{
	            		textArea.setText("                            " + topoOrder);    // appends file in sorted order to the textArea
	            	}
				}
				catch(CycleDetectedException e1)       // CycleDetectedException
                {
					JOptionPane.showMessageDialog(null, e1.toString());   // shows a string format of why exception was thrown
				}
            }
        }
    }

    /**
     *  Main class used to execute program
     * @param args
     */
    
    public static void main(String[] args)
    {
        MainGUI frame = new MainGUI();								// new frame object
        frame.setTitle("Project 4: Class Dependency Graph");		// assigns "Project 4: Class Dependency Graph" as title to frame
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);					// sets GUI frame size
        frame.setLocationRelativeTo(null);							// sets location relative to null
        frame.setVisible(true);										// sets frame as visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// closes on exit
    }
}