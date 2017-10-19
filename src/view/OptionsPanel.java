package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.OptionsPanelController;
import model.interfaces.GameEngine;

/**
 * 	Contains all information regarding the options panel. This class holds
 * 	all view information and creates the controller which facilitates most of
 * 	the players interaction with the game itself.
 * 
 * @see controller.OptionsPanelController
 */
public class OptionsPanel extends JToolBar 
{
	private static final long serialVersionUID = -575830027967673381L;
	
	private OptionsPanelController controller;
	private JButton addPlayerButton;
	private JButton placeBetButton;
	private JButton playerInfoButton;
	private JButton playRoundButton;
	
	public OptionsPanel(GameEngine engineInterface)
	{
		this.setLayout(new  BorderLayout());
		
		controller = new OptionsPanelController(this, engineInterface);
		addPlayerButton = new JButton("Add Player");
		placeBetButton = new JButton("Place Bet");
		playerInfoButton = new JButton("Player Info");
		playRoundButton = new JButton("Play Round");
		placeBetButton.setEnabled(false);
		playRoundButton.setEnabled(false);
		
		addPlayerButton.addActionListener(controller);
		placeBetButton.addActionListener(controller);
		playerInfoButton.addActionListener(controller);
		playRoundButton.addActionListener(controller);
		
		JPanel leftPanel = new JPanel();
		leftPanel.add(addPlayerButton);
		leftPanel.add(placeBetButton);
		
		JPanel rightPanel = new JPanel();
		rightPanel.add(playerInfoButton);
		rightPanel.add(playRoundButton);
		
		this.add(rightPanel, BorderLayout.EAST);
		this.add(leftPanel, BorderLayout.WEST);
	}
	
	public JButton getAddPlayerButton()
	{
		return addPlayerButton;
	}
	
	public JButton getPlaceBetButton()
	{
		return placeBetButton;
	}
	
	public JButton getPlayerInfoButton()
	{
		return playerInfoButton;
	}
	
	public JButton getPlayRoundButton()
	{
		return playRoundButton;
	}
}
