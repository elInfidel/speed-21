package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarController implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent actionEvent) 
	{
		System.out.println(actionEvent.getActionCommand());
		switch(actionEvent.getActionCommand())
		{
		case "Exit":
			System.exit(0);
		}
		
	}

}
