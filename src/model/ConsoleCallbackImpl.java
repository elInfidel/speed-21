package model;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class ConsoleCallbackImpl implements GameEngineCallback
{
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) 
	{
		System.out.println("PLAYER CARD = Player - " + player.getPlayerName() + " Card Info - " + card.toString() + "\n");
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
	{
		System.out.println("PLAYER BUST = Player - " + player.getPlayerName() + " Card Info - " + card.toString() + "\n");
	}

	@Override
	public void result(Player player, int result, GameEngine engine) 
	{
		System.out.println("PLAYER RESULT = Player - " + player.getPlayerName() + " Result - " + result + "\n");
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) 
	{
		System.out.println("HOUSE CARD =" + " Card Info - " + card.toString() + "\n");
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) 
	{
		System.out.println("HOUSE BUST = " + "Card Info - " + card.toString() + "\n");
	}

	@Override
	public void houseResult(int result, GameEngine engine) 
	{
		System.out.println("HOUSE RESULT = " + result);
	}

}
