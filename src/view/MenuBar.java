package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.MenuBarController;

/**
 * 	A menubar currently only with the ability to exit the program.
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar 
{
	private MenuBarController controller;
	private JMenu systemItem = new JMenu("Options");
	private JMenuItem exitItem = new JMenuItem("Exit");
	
	public MenuBar()
	{
		controller = new MenuBarController();
		exitItem.addActionListener(controller);
		systemItem.add(exitItem);
		this.add(systemItem);
	}
}
