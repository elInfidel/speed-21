package server.callback;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.PlayingCard;

public class NextHouseCardCallback extends AbstractCallback 
{
	private static final long serialVersionUID = -5768848926028908460L;
	
	private PlayingCard card;
	private GameEngine engine;
	
	public NextHouseCardCallback(PlayingCard card, GameEngine engine)
	{
		this.card = card;
		this.engine = engine;
	}
	
	@Override
	public void execute(GameEngineCallback callbackInterface) 
	{
		callbackInterface.nextHouseCard(card, engine);
	}

}
