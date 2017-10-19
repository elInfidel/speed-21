package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.GameEngineCallback;
import server.callback.AbstractCallback;

public class ClientGameEngineCallbackServer extends Thread
{
	private Collection<GameEngineCallback> engineCallbacks;
	private ObjectInputStream callbackStream;
	private ServerSocket serverSocket;
	private int runningPort = -1;
	
	public ClientGameEngineCallbackServer()
	{
		super("ClientCallbackServer");
		this.engineCallbacks = new ArrayList<GameEngineCallback>();
		this.start();
	}
	
	@Override
	public void run() 
	{
		
		try
		{
			serverSocket = new ServerSocket(0); // 0 specifies that we want the next available port to be used
			runningPort = serverSocket.getLocalPort();
			Socket socket = serverSocket.accept();
			callbackStream = new ObjectInputStream(socket.getInputStream());
			
			while(true)
			{
				AbstractCallback callback = (AbstractCallback)callbackStream.readObject();
				
				for(GameEngineCallback cb : engineCallbacks)
				{
					callback.execute(cb);
				}
			}
		}
		catch(IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public int getRunningPort()
	{
		return runningPort;
	}
	
	public void registerCallback(GameEngineCallback callback)
	{
		engineCallbacks.add(callback);
	}
}
