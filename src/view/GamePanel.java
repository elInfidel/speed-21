package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Stores CardPanels representing cards in the deck.
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel 
{
	private CardPanel deckPanel = new CardPanel();
	private CardPanel resultCardPanel = new CardPanel();
	
	public GamePanel() 
	{
		this.add(deckPanel);
		this.add(resultCardPanel);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public CardPanel getResultCardPanel()
	{
		return resultCardPanel;
	}
}
