package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;

/**
 * 	CardArt is a class containing only static data.
 * 	This data relates to the card art required by the game and acts as a single point of access
 * 	to all required card suit and value combinations.
 */
public class CardArt 
{
	private static BufferedImage cardBack;
	private static BufferedImage[] suitCards = new BufferedImage[52];
	
	// Use the enum to reach into suitCards
	private enum SuitOffset
	{
		Clubs(0),
		Diamonds(13),
		Hearts(26),
		Spades(39);
		
		private int offset;
		
		SuitOffset(int offset) 
		{
			this.offset = offset;
		}
		
		public int getOffset() 
		{
			return offset;
		}
		
	}
	
	// Here we initialize all the card art
	// the art will be initialized the first time this class is called.
	//
	//	REFERENCE: http://kenney.nl/assets/boardgame-pack
	//  All art obtained for this assignment is held under the CC0 in public domain
	//  the above link will take you to the pack.
	//
	static
	{
		try 
		{
			cardBack = ImageIO.read(new File("assets/cardBack_blue.png"));
			suitCards[0] = ImageIO.read(new File("assets/cardClubs2.png"));
			suitCards[1] = ImageIO.read(new File("assets/cardClubs3.png"));
			suitCards[2] = ImageIO.read(new File("assets/cardClubs4.png"));
			suitCards[3] = ImageIO.read(new File("assets/cardClubs5.png"));
			suitCards[4] = ImageIO.read(new File("assets/cardClubs6.png"));
			suitCards[5] = ImageIO.read(new File("assets/cardClubs7.png"));
			suitCards[6] = ImageIO.read(new File("assets/cardClubs8.png"));
			suitCards[7] = ImageIO.read(new File("assets/cardClubs9.png"));
			suitCards[8] = ImageIO.read(new File("assets/cardClubs10.png"));
			suitCards[9] = ImageIO.read(new File("assets/cardClubsA.png"));
			suitCards[10] = ImageIO.read(new File("assets/cardClubsJ.png"));
			suitCards[11] = ImageIO.read(new File("assets/cardClubsK.png"));
			suitCards[12] = ImageIO.read(new File("assets/cardClubsQ.png"));
			suitCards[13] = ImageIO.read(new File("assets/cardDiamonds2.png"));
			suitCards[14] = ImageIO.read(new File("assets/cardDiamonds3.png"));
			suitCards[15] = ImageIO.read(new File("assets/cardDiamonds4.png"));
			suitCards[16] = ImageIO.read(new File("assets/cardDiamonds5.png"));
			suitCards[17] = ImageIO.read(new File("assets/cardDiamonds6.png"));
			suitCards[18] = ImageIO.read(new File("assets/cardDiamonds7.png"));
			suitCards[19] = ImageIO.read(new File("assets/cardDiamonds8.png"));
			suitCards[20] = ImageIO.read(new File("assets/cardDiamonds9.png"));
			suitCards[21] = ImageIO.read(new File("assets/cardDiamonds10.png"));
			suitCards[22] = ImageIO.read(new File("assets/cardDiamondsA.png"));
			suitCards[23] = ImageIO.read(new File("assets/cardDiamondsJ.png"));
			suitCards[24] = ImageIO.read(new File("assets/cardDiamondsK.png"));
			suitCards[25] = ImageIO.read(new File("assets/cardDiamondsQ.png"));
			suitCards[26] = ImageIO.read(new File("assets/cardHearts2.png"));
			suitCards[27] = ImageIO.read(new File("assets/cardHearts3.png"));
			suitCards[28] = ImageIO.read(new File("assets/cardHearts4.png"));
			suitCards[29] = ImageIO.read(new File("assets/cardHearts5.png"));
			suitCards[30] = ImageIO.read(new File("assets/cardHearts6.png"));
			suitCards[31] = ImageIO.read(new File("assets/cardHearts7.png"));
			suitCards[32] = ImageIO.read(new File("assets/cardHearts8.png"));
			suitCards[33] = ImageIO.read(new File("assets/cardHearts9.png"));
			suitCards[34] = ImageIO.read(new File("assets/cardHearts10.png"));
			suitCards[35] = ImageIO.read(new File("assets/cardHeartsA.png"));
			suitCards[36] = ImageIO.read(new File("assets/cardHeartsJ.png"));
			suitCards[37] = ImageIO.read(new File("assets/cardHeartsK.png"));
			suitCards[38] = ImageIO.read(new File("assets/cardHeartsQ.png"));
			suitCards[39] = ImageIO.read(new File("assets/cardSpades2.png"));
			suitCards[40] = ImageIO.read(new File("assets/cardSpades3.png"));
			suitCards[41] = ImageIO.read(new File("assets/cardSpades4.png"));
			suitCards[42] = ImageIO.read(new File("assets/cardSpades5.png"));
			suitCards[43] = ImageIO.read(new File("assets/cardSpades6.png"));
			suitCards[44] = ImageIO.read(new File("assets/cardSpades7.png"));
			suitCards[45] = ImageIO.read(new File("assets/cardSpades8.png"));
			suitCards[46] = ImageIO.read(new File("assets/cardSpades9.png"));
			suitCards[47] = ImageIO.read(new File("assets/cardSpades10.png"));
			suitCards[48] = ImageIO.read(new File("assets/cardSpadesA.png"));
			suitCards[49] = ImageIO.read(new File("assets/cardSpadesJ.png"));
			suitCards[50] = ImageIO.read(new File("assets/cardSpadesK.png"));
			suitCards[51] = ImageIO.read(new File("assets/cardSpadesQ.png"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to read in all card art!");
		}
	}
	
	/**
	 * @return A bufferedImage containing image data for the card.
	 */
	public static BufferedImage getCardBack()
	{
		return cardBack;
	}
	
	/**
	 * @param suit used to figure out the suit of the card art.
	 * 
	 * @param value used to figure out the value of the card art.
	 * 
	 * @return A bufferedImage containing image data for the card.
	 */
	public static BufferedImage getCardSuitValue(Suit suit, Value value)
	{
		// Setting our offset for the suit type chosen
		SuitOffset offsetEnum;
		
		if(suit == Suit.clubs)
			offsetEnum = SuitOffset.Clubs;
		else if(suit == Suit.diamonds)
			offsetEnum = SuitOffset.Diamonds;
		else if(suit == Suit.hearts)
			offsetEnum = SuitOffset.Hearts;
		else if(suit == Suit.spades)
			offsetEnum = SuitOffset.Spades;
		else
			offsetEnum = SuitOffset.Clubs;

		switch(value) // somewhat dirty but this can be improved for assignment 2
		{
		case Ace:
			return suitCards[offsetEnum.getOffset() + 9];
		case Two:
			return suitCards[offsetEnum.getOffset()];
		case Three:
			return suitCards[offsetEnum.getOffset() + 1];
		case Four:
			return suitCards[offsetEnum.getOffset() + 2];
		case Five:
			return suitCards[offsetEnum.getOffset() + 3];
		case Six:
			return suitCards[offsetEnum.getOffset() + 4];
		case Seven:
			return suitCards[offsetEnum.getOffset() + 5];
		case Eight:
			return suitCards[offsetEnum.getOffset() + 6];
		case Nine:
			return suitCards[offsetEnum.getOffset() + 7];
		case Ten:
			return suitCards[offsetEnum.getOffset() + 8];
		case Jack:
			return suitCards[offsetEnum.getOffset() + 10];
		case Queen:
			return suitCards[offsetEnum.getOffset() + 12];
		case King:
			return suitCards[offsetEnum.getOffset() + 11];
		}
		
		return cardBack;
	}
	
}
