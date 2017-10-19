package view;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;

/**
 * 	CardPanel encapsulates the data required to display a playing card
 * 	on the screen.
 */
@SuppressWarnings("serial")
public class CardPanel extends JPanel 
{
	private JLabel imageContainer = new JLabel();
	
	private BufferedImage imageData; // Load image data into imageData for use
	private ImageIcon cardImage;
	
	public CardPanel()
	{
		cardImage = new ImageIcon(CardArt.getCardBack());
		imageContainer.setIcon(cardImage);
		this.add(imageContainer);
	}
	
	public void setCardArt(Suit suit, Value value)
	{
		imageData = CardArt.getCardSuitValue(suit, value); // Grab new card.
		System.out.println("Updated Card");
		imageContainer.setIcon(new ImageIcon(imageData));
	}
}
