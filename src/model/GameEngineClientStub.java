package model;

import java.util.Collection;
import java.util.Deque;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import server.GameEngineServerStub;
import server.command.*;

public class GameEngineClientStub implements GameEngine 
{
	private Socket socket;
	private ObjectInputStream fromServer;
	private ObjectOutputStream toServer;
	private ClientGameEngineCallbackServer callbackServer;
	
	private static final int SOCKET_TIMEOUT_THRESHOLD = 5000;
	private static final String IP_ADDRESS = "localhost";
	
	// This is somewhat hacky but we essentially store this clients player data here as a single point of access for
	// the controllers / anything else, instead of passing it halfway through the program.
	// Because we're dealing with only 1 player per client with this implementation I see no harm.
	public static Player playerData = null;
	
	public GameEngineClientStub()
	{
		 try
		 {
			 //TODO: Callback server currently needs to be instantiated prior to connecting to server.
			 //      Dependency on order is probably bad. On the other hand, is it really avoidable?
			 callbackServer = new ClientGameEngineCallbackServer();
			 
			 socket = new Socket(IP_ADDRESS, GameEngineServerStub.PORT);
			 socket.setSoTimeout(SOCKET_TIMEOUT_THRESHOLD);
			 
			 fromServer = new ObjectInputStream(socket.getInputStream());
			 toServer = new ObjectOutputStream(socket.getOutputStream());
			 
			 // We need to pass the callback server port over to the server so it knows what to connect too.
			 // This allows multiple client callback servers on the same system.
			 // The server must receive this int first before it will continue on to the main server loop and begin
			 // passing commands. Maybe there's a better way to do this?
			 toServer.writeInt(callbackServer.getRunningPort());
			 toServer.flush();
		 }
		 catch(IOException e)
		 {
			 if(socket != null)
			 {
				 try 
				 {
					socket.close();
				 } 
				 catch (IOException e1) 
				 {
					e1.printStackTrace();
				 }
			 }
			 
			 handleException(e);
		 }
	}
	
	@Override
	public void dealPlayer(Player player, int delay) 
	{
		SendServerCommand(new DealPlayerCommand(player, delay));
	}

	@Override
	public void dealHouse(int delay) 
	{
		SendServerCommand(new DealHouseCommand(delay));
	}

	@Override
	public void addPlayer(Player player) 
	{
		SendServerCommand(new AddPlayerCommand(player));
		playerData = player;
	}

	@Override
	public Player getPlayer(String id) 
	{
		SendServerCommand(new GetPlayerCommand(id));
		
		try 
		{
			return (Player)fromServer.readObject();
		} 
		catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean removePlayer(Player player) 
	{
		SendServerCommand(new RemovePlayerCommand(player));
		
		try 
		{
			return fromServer.readBoolean();
		} 
		catch (IOException e) 
		{
			handleException(e);
		}
		
		return false;
	}

	@Override
	public void calculateResult() 
	{
		SendServerCommand(new CalculateResultCommand());
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		callbackServer.registerCallback(gameEngineCallback);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Player> getAllPlayers() 
	{
		SendServerCommand(new GetAllPlayersCommand());
		
		try 
		{
			return (Collection<Player>)fromServer.readObject(); // TODO Is there a way to check type safety?
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			handleException(e);
		}
		
		return null;
	}
	
	@Override
	public boolean placeBet(Player player, int bet)
	{
		SendServerCommand(new PlaceBetCommand(player, bet));
		
		try 
		{
			return fromServer.readBoolean();
		} 
		catch (IOException e) 
		{
			handleException(e);
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Deque<PlayingCard> getShuffledDeck()
	{
		SendServerCommand(new GetShuffledDeckCommand());
		
		try 
		{
			return (Deque<PlayingCard>)fromServer.readObject(); // TODO Is there a way to check type safety?
		} 
		catch (IOException | ClassNotFoundException e) 
		{
			handleException(e);
		}
		
		return null;
	}
	
	private void SendServerCommand(AbstractCommand command)
	{
		try 
		{
			toServer.writeObject(command);
			toServer.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void handleException(Exception e)
	{
		e.printStackTrace();
	}

}
