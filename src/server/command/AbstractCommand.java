package server.command;

import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.interfaces.GameEngine;

public abstract class AbstractCommand implements Serializable
{
	private static final long serialVersionUID = -6496779177489416648L;

	public abstract void execute(ObjectOutputStream toClient, GameEngine gameEngine);
}
