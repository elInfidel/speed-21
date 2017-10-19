package model;

import java.io.Serializable;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard, Serializable
{
	private static final long serialVersionUID = -3231459599601969112L;
	
	private Suit suit;
	private Value value;
	
	public PlayingCardImpl(Suit suit, Value value)
	{
		this.suit = suit;
		this.value = value;
	}
	
	@Override
	public Suit getSuit() 
	{
		return this.suit;
	}

	@Override
	public Value getValue() 
	{
		return this.value;
	}

	@Override
	public int getScore() 
	{
		int score = 0;
		
		switch(value)
		{
		case Ace:
		case Two:
		case Three:
		case Four:
		case Five:
		case Six:
		case Seven:
		case Eight:
		case Nine:
		case Ten:
			score = value.ordinal() + 1; // TODO:  probably a more clear way to do this??? conversion to int based on
			break;                       // enum index + 1. Easy to break by modifying enum
		case Jack:
		case Queen:
		case King:
			score = 10;
			break;
		}
		
		return score;
	}
	
	@Override
	public String toString()
	{
		return "Suit: " + this.suit + " Value: " + this.value;
	}

}
