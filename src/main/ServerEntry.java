package main;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import server.GameEngineServerStub;

public class ServerEntry 
{

	public static void main(String[] args) 
	{
		GameEngine gameEngine = new GameEngineImpl();
		GameEngineServerStub server = new GameEngineServerStub(gameEngine);
		server.run();
	}

}
