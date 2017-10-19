package server.callback;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.PlayingCard;

public class HouseBustCardCallback extends AbstractCallback 
{
	private static final long serialVersionUID = -3584052730175244155L;
	
	private PlayingCard card;
	private GameEngine engine;
	
	public HouseBustCardCallback(PlayingCard card, GameEngine engine)
	{
		this.card = card;
		this.engine = engine;
	}
	
	@Override
	public void execute(GameEngineCallback callbackInterface) 
	{
		callbackInterface.houseBustCard(card, engine);
	}

}
