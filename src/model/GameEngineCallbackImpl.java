package model;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.MainView;

public class GameEngineCallbackImpl implements GameEngineCallback
{
	private MainView view;
	
	@Override
	public void nextCard(final Player player, final PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
		    public void run() 
		    {
		    	if(player.getPlayerId().equals(GameEngineClientStub.playerData.getPlayerId()))
		    	{
		    		view.getGameDataPanel().PrintToConsole("PLAYER DEALT: " + " CARD: " + card.toString() + "\n");
			    	view.getGamePanel().getResultCardPanel().setCardArt(card.getSuit(), card.getValue());
		    	}
		    }
		});
	}

	@Override
	public void bustCard(final Player player, final PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
		    public void run() 
		    {
		    	if(player.getPlayerId().equals(GameEngineClientStub.playerData.getPlayerId()))
		    	{
		    		view.getGameDataPanel().PrintToConsole("PLAYER BUST: " + " CARD: " + card.toString() + "\n");
			    	view.getGamePanel().getResultCardPanel().setCardArt(card.getSuit(), card.getValue());
		    	}
		    }
		});
	}

	@Override
	public void result(final Player player, final int result, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
		    public void run() 
		    {
		    	if(player.getPlayerId().equals(GameEngineClientStub.playerData.getPlayerId()))
		    	{
		    		view.getGameDataPanel().PrintToConsole("PLAYER RESULT: " + result + "\n\n");
		    	}
		    }
		});
	}

	@Override
	public void nextHouseCard(final PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
		    public void run() 
		    {
		    	view.getGameDataPanel().PrintToConsole("HOUSE DEALT: " + "CARD: " + card.toString() + "\n");
		    	view.getGamePanel().getResultCardPanel().setCardArt(card.getSuit(), card.getValue());
		    }
		});
	}

	@Override
	public void houseBustCard(final PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
		    public void run() 
		    {
		    	view.getGameDataPanel().PrintToConsole("HOUSE BUST: " + "CARD: " + card.toString() + "\n");
				view.getGamePanel().getResultCardPanel().setCardArt(card.getSuit(), card.getValue());
				view.getOptionsPanel().getPlayRoundButton().setEnabled(true);
		    }
		});
	}

	@Override
	public void houseResult(final int result, GameEngine engine) 
	{
		// Calculating the winner and printing their name.
		@SuppressWarnings("unused")
		final Player player = engine.getPlayer(GameEngineClientStub.playerData.getPlayerId());
		
		SwingUtilities.invokeLater(new Runnable() 
		{
		    public void run() 
		    {
		    	view.getGameDataPanel().PrintToConsole("HOUSE RESULT: " + result + "\n");
		    	
				//if(player.getResult() > result)
				//	view.getGameDataPanel().PrintToConsole("PLAYER WINS: " + "House Score - " + result + " Player Score - "
				//											+ player.getResult() + " Player Points - " + player.getPoints() + "\n\n");
				//else
				//	view.getGameDataPanel().PrintToConsole("HOUSE WINS: " + "House Score - " + result + " Player Score - " 
				//											+ player.getResult() + " Player Points - " + player.getPoints() + "\n\n");
		    }
		});
	}
	
	public void setMainView(MainView view) 
	{
		this.view = view;
	}
}
