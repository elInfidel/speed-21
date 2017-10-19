package server.command;

import java.io.IOException;
import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;

public class GetPlayerCommand extends AbstractCommand 
{
	private static final long serialVersionUID = -854741782556572315L;
	
	private String id;
	
	public GetPlayerCommand(String id)
	{
		this.id = id;
	}
	
	@Override
	public void execute(ObjectOutputStream toClient, GameEngine gameEngine) 
	{
		try 
		{
			toClient.writeObject(gameEngine.getPlayer(id));
			toClient.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
