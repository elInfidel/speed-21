package testing;

import java.util.ArrayList;

import model.GameEngineClientStub;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class ConcurrencyTestHarness
{
	private static final int CLIENT_COUNT = 10;
	
	ArrayList<GameEngine> engines = new ArrayList<GameEngine>();
	
	ArrayList<Player> players = new ArrayList<Player>();
	
	public static void main(String[] args)
	{
		new ConcurrencyTestHarness();
	}

	public ConcurrencyTestHarness()
	{
		addClients();
		placeBets();
		deal();
		System.out.println("TEST COMPLETE");
	}
	
	private void addClients()
	{
		System.out.println("Adding Clients");
		for(int i = 0; i < CLIENT_COUNT; ++i)
		{
			engines.add(new GameEngineClientStub());
			System.out.println("Created client " + i);
		}
		
		System.out.println("Adding Player to Each Client");
		for(int i = 0; i < CLIENT_COUNT; ++i)
		{
			players.add(new SimplePlayer(Integer.toString(i), "player" + i, 1000));
			engines.get(i).addPlayer(players.get(i));
			System.out.println("Added player for client " + i);
		}
	}
	
	private void placeBets()
	{
		
		System.out.println("Placing Bets");
		for(int i = 0; i < CLIENT_COUNT; ++i)
		{
			@SuppressWarnings("unused")
			boolean betResult = engines.get(i).placeBet(players.get(i), 500);
			System.out.println("Placed bet for client " + i);
		}
	}
	
	private void deal()
	{
		System.out.println("Running game simulation");
		for(int i = 0; i < CLIENT_COUNT; ++i)
		{
			engines.get(i).dealPlayer(players.get(i), 50);
			System.out.println("Called deal player for client " + i);
		}
		
		// Sleeping to give the server time to process all the deal commands
		// We don't want a situation where dealHouse is called before the players have received their cards.
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		engines.get(0).calculateResult();
		System.out.println("Called deal for the house");
	}
}