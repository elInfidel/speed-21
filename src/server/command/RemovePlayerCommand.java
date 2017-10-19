package server.command;

import java.io.IOException;
import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class RemovePlayerCommand extends AbstractCommand
{
	private static final long serialVersionUID = 2002453685550834054L;
	private Player player;
	
	public RemovePlayerCommand(Player player)
	{
		this.player = player;
	}
	
	@Override
	public void execute(ObjectOutputStream toClient, GameEngine gameEngine) 
	{
		try 
		{
			toClient.writeBoolean(gameEngine.removePlayer(player));
			toClient.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
