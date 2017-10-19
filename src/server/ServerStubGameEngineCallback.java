package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import model.interfaces.*;
import server.callback.*;

public class ServerStubGameEngineCallback implements GameEngineCallback, Serializable
{
	private static final long serialVersionUID = -2787672560545443058L;
	
	private Socket socket;
	private static final int SOCKET_TIMEOUT_THRESHOLD = 5000;
	private static final int COMMAND_QUEUE_MAX = 500;
	
	private ObjectOutputStream toClient;
	private volatile ArrayBlockingQueue<AbstractCallback> commandQueue = new ArrayBlockingQueue<AbstractCallback>(COMMAND_QUEUE_MAX);
	
	public ServerStubGameEngineCallback(String clientIP, int callbackServerPort)
	{
		try 
		{
			socket = new Socket(clientIP, callbackServerPort);
			socket.setSoTimeout(SOCKET_TIMEOUT_THRESHOLD);
			
			toClient = new ObjectOutputStream(socket.getOutputStream());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
		new CallbackHandler().start();
	}
	
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine)  
	{
		queueCallback(new NextCardCallback(player, card, engine));
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine)
	{
		queueCallback(new BustCardCallback(player, card, engine));
	}

	@Override
	public void result(Player player, int result, GameEngine engine)
	{
		queueCallback(new ResultCallback(player, result, engine));
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) 
	{
		queueCallback(new NextHouseCardCallback(card, engine));
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine)
	{
		queueCallback(new HouseBustCardCallback(card, engine));
	}

	@Override
	public void houseResult(int result, GameEngine engine) 
	{
		queueCallback(new HouseResultCallback(result, engine));
	}
	
	private void queueCallback(AbstractCallback callback)
	{
		try
		{
			commandQueue.put(callback);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	private class CallbackHandler extends Thread 
	{
		public CallbackHandler()
		{
			super("CallbackHandlerThread");
		}
		
		@Override
		public void run() 
		{
			try
			{
				
				while(true)
				{
					AbstractCallback cb = commandQueue.take();
					
					try 
					{			
						toClient.writeObject(cb);
						toClient.flush();
					} 
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
