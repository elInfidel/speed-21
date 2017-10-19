package model;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class GameEngineImpl implements GameEngine, Serializable
{
	private static final long serialVersionUID = 635229564015093244L;
	
	private static final int DECK_SIZE = 52;
	private static final int DEFAULT_HOUSE_POINTS = 1000;
	private static long deckGenSeed = System.nanoTime();
	
	private volatile transient Collection<GameEngineCallback> engineCallbacks;
	private volatile Map<String, Player> players;
	private volatile Deque<PlayingCard> deck;
	
	Player house = new SimplePlayer("0", "The House", DEFAULT_HOUSE_POINTS);
	
	public GameEngineImpl() 
	{
		this.engineCallbacks = new CopyOnWriteArrayList<GameEngineCallback>();
		this.players = new ConcurrentHashMap<String, Player>();	
		this.deck = new ConcurrentLinkedDeque<PlayingCard>();
		this.deck = getShuffledDeck();
	}
	
	@Override
	public void dealPlayer(Player player, int delay) 
	{
		assert player != null;
		assert deck.size() > 0;
		
		// Draw cards until bust
		int result = 0;
		
		while(result < GameEngine.BUST_LEVEL) 
		{
			PlayingCard drawnCard = deck.pollFirst();
			
			if(result + drawnCard.getScore() >= GameEngine.BUST_LEVEL)
			{
				// Notifying subscribers with bust card
				for(GameEngineCallback cb : engineCallbacks) 
				{
					cb.bustCard(player, drawnCard, this);
				}
				break;
			}
			else
				result += drawnCard.getScore();
			
			// Notifying subscribers of drawn card
			for(GameEngineCallback cb : engineCallbacks)
			{
				cb.nextCard(player, drawnCard, this);
			}
			
			try 
			{
				Thread.sleep(delay);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		players.get(player.getPlayerId()).setResult(result);
		
		// Notifying subscribers with result
		for(GameEngineCallback cb : engineCallbacks) 
		{
			cb.result(players.get(player.getPlayerId()), result, this);
		}
		
		// Refresh the deck if it's getting low
		if(deck.size() < DECK_SIZE/3)
		{
			deck = getShuffledDeck();
		}
	}
	
	@Override
	public void dealHouse(int delay) 
	{
		assert house != null;
		assert deck.size() > 0;
		
		// Draw cards until bust
		int result = 0;
		while(result < GameEngine.BUST_LEVEL)
		{
			PlayingCard drawnCard = deck.pollFirst();
					
			if(result + drawnCard.getScore() >= GameEngine.BUST_LEVEL)
			{
				// Notifying subscribers with bust card
				for(GameEngineCallback cb : engineCallbacks) 
				{
					cb.houseBustCard(drawnCard, this);
				}
				break;
			}
			else
				result += drawnCard.getScore();
					
			// Notifying subscribers of drawn card
			for(GameEngineCallback cb : engineCallbacks) 
			{
				cb.nextHouseCard(drawnCard, this);
			}
			
			// Sleeping the thread for 'delay' milliseconds	
			try {
				Thread.sleep(delay);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
				
		house.setResult(result);
		
		for(GameEngineCallback cb : engineCallbacks) 
		{
			cb.houseResult(result, this);
		}
		
		// Refresh the deck if it's getting low
		if(deck.size() < DECK_SIZE/3)
		{
			deck = getShuffledDeck();
		}
	}

	@Override
	public void addPlayer(Player player) 
	{
		players.put(player.getPlayerId(), player);
		System.out.println();
	}
	
	@Override
	public Player getPlayer(String id) 
	{
		Player temp = new SimplePlayer(players.get(id));
		return temp;
	}
	
	@Override
	public boolean removePlayer(Player player) 
	{
		String playerID = player.getPlayerId();
		
		if(players.containsKey(playerID))
		{
			players.remove(playerID);
			return true;
		}
		else return false;
	}

	@Override
	public void calculateResult() 
	{
		dealHouse(500);
		
		// Check players for winners and update points where required
		for(Player player : players.values())
		{
			player.setPoints(player.getPoints() - player.getBet());
			
			if(player.getResult() > house.getResult())
			{
				player.setPoints(player.getPoints() + (player.getBet() * 2));
			}
			else if(player.getResult() == house.getResult())
			{
				player.setPoints(player.getPoints() + player.getBet());
			}
		}
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		engineCallbacks.add(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() 
	{
		// Here we throw the player data in a fresh array
		// otherwise if we pass the values directly back
		// we'll lose the data across the network.
		return new ArrayList<Player>(players.values());
	}

	@Override
	public boolean placeBet(Player player, int bet) 
	{
		return players.get(player.getPlayerId()).placeBet(bet);
	}
	
	@Override
	public Deque<PlayingCard> getShuffledDeck() 
	{
		Deque<PlayingCard> temp = new ConcurrentLinkedDeque<PlayingCard>();
		
		deckGenSeed = System.nanoTime();
		Random cardGen = new Random(deckGenSeed);
		
		// Generate new deck based on seed
		for(int i = 0; i < DECK_SIZE; ++i)
		{
			temp.addFirst(new PlayingCardImpl(PlayingCard.Suit.values()[cardGen.nextInt(PlayingCard.Suit.values().length)],
					                               PlayingCard.Value.values()[cardGen.nextInt(PlayingCard.Value.values().length)]));
		}
		
		return temp;
	}

}
