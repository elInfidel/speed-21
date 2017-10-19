package server.command;

import java.io.IOException;
import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class PlaceBetCommand extends AbstractCommand 
{
	private static final long serialVersionUID = 8431434610982236533L;
	
	private Player player;
	private int bet;
	
	public PlaceBetCommand(Player player, int bet)
	{
		this.player = player;
		this.bet = bet;
	}
	
	@Override
	public void execute(ObjectOutputStream toClient, GameEngine gameEngine) 
	{
		try 
		{
			boolean result = gameEngine.placeBet(player, bet);
			toClient.writeBoolean(result);
			toClient.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
