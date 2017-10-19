package server;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ServerView extends JFrame 
{
	JTextArea textDisplay = new JTextArea();
	
	public ServerView()
	{
		super("Server Console");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textDisplay.setEditable(false);
		this.add(textDisplay);
		this.add(new JScrollPane(textDisplay));
		this.setSize(800,600);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void addMessage(String message)
	{
		textDisplay.append(message + "\n");
	}
}
