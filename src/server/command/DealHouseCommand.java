package server.command;

import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;

public class DealHouseCommand extends AbstractCommand 
{
	private static final long serialVersionUID = -8655184069228859985L;
	
	private int delay;
	
	public DealHouseCommand(int delay)
	{
		this.delay = delay;
	}
	
	@Override
	public void execute(ObjectOutputStream toClient, GameEngine gameEngine) 
	{
		gameEngine.dealHouse(delay);
	}

}
