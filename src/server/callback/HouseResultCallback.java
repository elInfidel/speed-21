package server.callback;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;

public class HouseResultCallback extends AbstractCallback 
{
	private static final long serialVersionUID = -6906029402508783121L;
	
	private int result;
	private GameEngine engine;
	
	public HouseResultCallback(int result, GameEngine engine)
	{
		this.result = result;
		this.engine = engine;
	}
	
	@Override
	public void execute(GameEngineCallback callbackInterface) 
	{
		callbackInterface.houseResult(result, engine);
	}

}
