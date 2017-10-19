package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Random;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.OptionsPanel;

// TODO: Improved input validation
public class OptionsPanelController implements ActionListener
{
	private static final int DEFAULT_DELAY = 500;
	
	private OptionsPanel optionsPanel;
	private volatile GameEngine engineInterface;
	private Player playerHandle;
	
	public OptionsPanelController(OptionsPanel optionsPanel, GameEngine engineInterface)
	{
		this.optionsPanel = optionsPanel;
		this.engineInterface = engineInterface;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent)
	{
		switch(actionEvent.getActionCommand())
		{
		case "Add Player":	
			// Instantiating a new player based on dialog input
			String nameInput = JOptionPane.showInputDialog("New Game - Create Player", "Default Player");
			if(nameInput == null) break;
			
			String initialPointsInput = JOptionPane.showInputDialog("New Game - Initial Points", "100");
			if(initialPointsInput == null) break;
			
			int initialPoints = Integer.parseInt(initialPointsInput);
			
			if(nameInput != null && initialPointsInput != null)
			{
				// Generate a random ID for player by avoiding existing IDs obtained from engine
				Collection<Player> players = engineInterface.getAllPlayers();
				boolean notUnique = true;
				Random idGen = new Random(1000);
				idGen.setSeed(System.nanoTime());
				int playerID = idGen.nextInt();
				
				do
				{
					boolean idMatched = false;
					for(Player player : players)
					{
						if(Integer.parseInt(player.getPlayerId()) == playerID)
							idMatched = true;
							
					}
					
					if(!idMatched)
						notUnique = false;
				}
				while(notUnique);
					
				// Add player to system
				playerHandle = new SimplePlayer(String.valueOf(playerID), nameInput, initialPoints);
				engineInterface.addPlayer(playerHandle);
				optionsPanel.getAddPlayerButton().setEnabled(false);
				optionsPanel.getPlaceBetButton().setEnabled(true);
			}
			
			break;
		case "Place Bet":
			String input = JOptionPane.showInputDialog("Place bet", "50");
			final int bet;
			
			if(input != null)
				bet = Integer.parseInt(input);
			else
				break; // Input was invalid we break out here
			
			// We contact the model in a seperate thread to prevent the
			// GUI updates accumulating until the very end of all our work before updating.
			new Thread()
			{
				@Override
				public void run()
				{
					if(!engineInterface.placeBet(playerHandle, bet)) 
					{
						// Player can't afford his bet
						JOptionPane.showMessageDialog(optionsPanel, "You haven't got the required points to bet!", "ERROR", JOptionPane.OK_OPTION);
					} 
					else
					{
						optionsPanel.getPlaceBetButton().setEnabled(false);
						optionsPanel.getPlayRoundButton().setEnabled(true);
					}
				}
			}.start();
			break;
		case "Player Info":
			Collection<Player> players = engineInterface.getAllPlayers();
			System.out.println(" ---------- Server Player Data ----------");
			for(Player player : players)
			{
				System.out.println("Player ID: " + player.getPlayerId() + " Player Name: " + player.getPlayerName() + " Player Points: " + player.getPoints());
			}
			System.out.println(" ----------------------------------------");
			break;
		case "Play Round":
			optionsPanel.getPlayRoundButton().setEnabled(false);
			optionsPanel.getPlaceBetButton().setEnabled(true);
			
			Collection<Player> playingPlayers = engineInterface.getAllPlayers();
			for(Player player : playingPlayers)
			{
				engineInterface.dealPlayer(player, DEFAULT_DELAY);
			}
			engineInterface.calculateResult();
			break;
		default:
			break;
		}
	}

}
