package server.callback;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class NextCardCallback extends AbstractCallback
{
	private static final long serialVersionUID = 4093393130022190540L;
	
	private Player player;
	private PlayingCard card;
	private GameEngine engine;
	
	public NextCardCallback(Player player, PlayingCard card, GameEngine engine)
	{
		this.player = player;
		this.card = card;
		this.engine = engine;
	}

	@Override
	public void execute(GameEngineCallback callbackInterface) 
	{
		callbackInterface.nextCard(player, card, engine);
	}
}
