package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.interfaces.GameEngine;

/**
 * 	Captures information regarding the window itself and also instantiates and stores reference
 * 	to each of the panels which it contains.
 */
@SuppressWarnings("serial")
public class MainView extends JFrame
{
	private static final int WINDOW_WIDTH = 1024;
	private static final int WINDOW_HEIGHT = 550;
	
	private MenuBar menuBar;
	private GamePanel gamePanel;
	private OptionsPanel optionsPanel;
	private GameConsole gameConsole;
	
	public MainView(GameEngine engineInterface) 
	{
		super("Speed 21");
		
		menuBar = new MenuBar();
		optionsPanel = new OptionsPanel(engineInterface);
		gamePanel = new GamePanel();
		gameConsole = new GameConsole();
		
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		// Setting up sub containers
		this.setLayout(new BorderLayout());
		this.setJMenuBar(menuBar);
		this.getContentPane().add(gamePanel, BorderLayout.CENTER);
		this.getContentPane().add(optionsPanel, BorderLayout.SOUTH);
		this.getContentPane().add(gameConsole, BorderLayout.WEST);
		
		this.setVisible(true);
	}
	
	public MenuBar getWindowMenuBar() 
	{
		return menuBar;
	}
	
	public GamePanel getGamePanel() 
	{
		return gamePanel;
	}
	
	public OptionsPanel getOptionsPanel() 
	{
		return optionsPanel;
	}
	
	public GameConsole getGameDataPanel()
	{
		return gameConsole;
	}
}
