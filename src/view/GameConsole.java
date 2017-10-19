package view;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Essential a console embedded into the gui. get the textField and modify it
 * as you see fit.
 */
@SuppressWarnings("serial")
public class GameConsole extends JScrollPane 
{
	private static final int WIDTH = 50;
	private static final int HEIGHT = 10;
	private static JTextArea console = new JTextArea(HEIGHT, WIDTH);
	
	private static final String CLIENT_INFO = "NOTE: This particular console only supplies information regarding your personal"
											+ " game against the house. For information about all clients on the server"
											+ " check the eclipse console output.";
	
	public GameConsole()
	{
		super(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		console.setWrapStyleWord(true);
		console.setLineWrap(true);
		console.setEditable(false);
		console.append(CLIENT_INFO + "\n\n");
	}
	
	public void PrintToConsole(String text)
	{
		console.append(text);
	}
	
	public void Clear()
	{
		console.setText("");
	}
}
