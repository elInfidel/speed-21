package server.command;

import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;

public class CalculateResultCommand extends AbstractCommand 
{
	private static final long serialVersionUID = -3470684793540760595L;

	@Override
	public void execute(ObjectOutputStream toClient, GameEngine gameEngine) 
	{
		gameEngine.calculateResult();
	}

}
