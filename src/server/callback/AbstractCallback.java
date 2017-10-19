package server.callback;

import java.io.Serializable;

import model.interfaces.GameEngineCallback;

public abstract class AbstractCallback  implements Serializable
{
	private static final long serialVersionUID = -5189039878583013792L;

	public abstract void execute(GameEngineCallback callbackInterface);
}
