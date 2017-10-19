package model;

import java.io.Serializable;

import model.interfaces.Player;

public class SimplePlayer implements Player, Serializable
{
	private static final long serialVersionUID = -6361553693653056752L;
	
	private String playerId;
	private String playerName;
	private volatile int result;
	private volatile int points;
	private volatile int bet;
	
	public SimplePlayer() 
	{
		this.playerId = "-1";
		this.playerName = "uninitialized";
		this.result = 0;
		this.points = 0;
		this.bet = 0;
	}
	
	public SimplePlayer(String playerId, String playerName, int points) 
	{
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = points;
		this.result = 0;
		this.bet = 0;
	}
	
	public SimplePlayer(Player player)
	{
		this.playerId = player.getPlayerId();
		this.playerName = player.getPlayerName();
		this.result = player.getResult();
		this.points = player.getPoints();
		this.bet = player.getBet();
	}
	
	@Override
	public String getPlayerName()
	{
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}

	@Override
	public int getPoints() 
	{
		return points;
	}

	@Override
	public void setPoints(int points) 
	{
		this.points = points;
	}

	@Override
	public String getPlayerId() 
	{
		return playerId;
	}

	@Override
	public boolean placeBet(int bet) 
	{
		if(points - bet >= 0) 
		{
			this.bet = bet;
			return true;
		}
		else
			return false;
	}

	@Override
	public int getBet() 
	{
		return bet;
	}

	@Override
	public int getResult() 
	{
		return result;
	}

	@Override
	public void setResult(final int result) 
	{
		this.result = result;
	}
	
	@Override
	public String toString() 
	{	
		String string = getPlayerName() + ":" +
		" Points: " + getPoints() +
		" Last Bet: " + getBet() +
		" Last Result: " + getResult() + "\n";
		
		return string;
	}
}
