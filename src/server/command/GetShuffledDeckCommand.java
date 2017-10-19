package server.command;

import java.io.IOException;
import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;

public class GetShuffledDeckCommand extends AbstractCommand {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2012411605987042102L;

	@Override
	public void execute(ObjectOutputStream toClient, GameEngine gameEngine)
	{
		try 
		{
			toClient.writeObject(gameEngine.getShuffledDeck());
			toClient.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
