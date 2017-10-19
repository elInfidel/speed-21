package server.command;

import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class AddPlayerCommand extends AbstractCommand 
{
	private static final long serialVersionUID = -8204819411822824498L;
	private Player player;
	
	public AddPlayerCommand(Player player)
	{
		this.player = player;
	}

	@Override
	public void execute(ObjectOutputStream toClient, GameEngine gameEngine) 
	{
		gameEngine.addPlayer(player);
	}
}
