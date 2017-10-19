package server.callback;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class BustCardCallback extends AbstractCallback
{
	private static final long serialVersionUID = -4102185441815427464L;
	
	private Player player;
	private PlayingCard card;
	private GameEngine engine;
	
	public BustCardCallback(Player player, PlayingCard card, GameEngine engine)
	{
		this.player = player;
		this.card = card;
		this.engine = engine;
	}
	
	@Override
	public void execute(GameEngineCallback callbackInterface) 
	{
		callbackInterface.bustCard(player, card, engine);
	}

}
