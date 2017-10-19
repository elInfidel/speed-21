package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.interfaces.GameEngine;
import server.command.AbstractCommand;

/**
 * 	Contains the main server loop and functionality for handling
 * 	clients on the server side.
 */
public class GameEngineServerStub 
{
	// Because multiple threads access these variables
	// we make them volatile just to be safe.
	private volatile GameEngine gameEngine;
	//private volatile ServerView serverView;
	
	public static final int PORT = 30001;
	
	public GameEngineServerStub(GameEngine gameEngine)
	{
		this.gameEngine = gameEngine;
		//this.serverView = new ServerView();
	}
	
	public void run() // Server game loop
	{
		try (ServerSocket serverSocket = new ServerSocket(PORT))
		{
			while(true)
			{
				// Wait for incoming client connection request
				Socket socket = serverSocket.accept();
				
				// Spawn a handler thread for this client
				new ClientHandler(socket).start();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private class ClientHandler extends Thread 
	{
		private Socket socket;
		private ObjectInputStream incomingRequest;
		private ObjectOutputStream serverResponse;
		
		public ClientHandler(Socket socket)
		{
			this.socket = socket;
		}
		
		@Override
		public void run() 
		{
			try
			{
				serverResponse = new ObjectOutputStream(socket.getOutputStream());
				incomingRequest = new ObjectInputStream(socket.getInputStream());
				
				// The first thing each client will send 
				// over is the port to communicate with its callback server.
				// We assume this in the server side implementation of the client stub
				int callbackServerIP = incomingRequest.readInt();
				
				gameEngine.addGameEngineCallback(new ServerStubGameEngineCallback(socket.getInetAddress().getHostAddress(), callbackServerIP));
				
				if(gameEngine != null)
				{
					while(true)
					{
							AbstractCommand command = (AbstractCommand)incomingRequest.readObject();
							command.execute(serverResponse, gameEngine);
					}
				}
			}
			catch(IOException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}
}
