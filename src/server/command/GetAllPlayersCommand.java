package server.command;

import java.io.IOException;
import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;

public class GetAllPlayersCommand extends AbstractCommand 
{
	private static final long serialVersionUID = 8396489484119637709L;

	@Override
	public void execute(ObjectOutputStream toClient, GameEngine gameEngine)
	{
		try 
		{
			toClient.writeObject(gameEngine.getAllPlayers());
			toClient.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
