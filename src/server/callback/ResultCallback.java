package server.callback;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class ResultCallback extends AbstractCallback 
{
	private static final long serialVersionUID = 1625052883624804510L;
	
	private Player player;
	private int result;
	private GameEngine engine;
	
	public ResultCallback(Player player, int result, GameEngine engine)
	{
		this.player = player;
		this.result = result;
		this.engine = engine;
	}
	
	@Override
	public void execute(GameEngineCallback callbackInterface) 
	{
		callbackInterface.result(player, result, engine);
	}

}
