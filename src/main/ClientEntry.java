package main;

import model.ConsoleCallbackImpl;
import model.GameEngineCallbackImpl;
import model.GameEngineClientStub;
import model.interfaces.GameEngine;
import view.MainView;

public class ClientEntry 
{

	// Program entry point - gets everything initialized.
	public static void main(String[] args) 
	{	
		GameEngine gameEngine = new GameEngineClientStub();
		
		MainView mainView = new MainView(gameEngine);
		
		GameEngineCallbackImpl guiCb = new GameEngineCallbackImpl();
		ConsoleCallbackImpl consoleCb = new ConsoleCallbackImpl();
		guiCb.setMainView(mainView);
		gameEngine.addGameEngineCallback(guiCb);
		gameEngine.addGameEngineCallback(consoleCb);
	}
}
