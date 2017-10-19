package server.command;

import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class DealPlayerCommand extends AbstractCommand 
{
	private static final long serialVersionUID = -3027815744210340027L;
	
	private Player player;
	private int delay;
	
	public DealPlayerCommand(Player player, int delay)
	{
		this.player = player;
		this.delay = delay;
	}
	
	@Override
	public void execute(ObjectOutputStream toClient, GameEngine gameEngine) 
	{
		gameEngine.dealPlayer(player, delay);
	}

}
