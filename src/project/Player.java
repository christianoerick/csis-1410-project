package project;

import java.util.*;

public class Player 
{
	private String playerName;
	private int playerMoney = 2000;
	private boolean inGame = true; // if they lose they, inGame = false
	private ArrayList<Card> hand = new ArrayList<>();
	private int handTotalSum = 0;
 
	public Player(String name) 
	{
		playerName = name;
	}
	
	public String getPlayerName() 
	{
		return playerName;
	}
	
	public void setPlayerName(String name)
	{
		playerName = name;
	}
	
	public int getPlayerMoney() 
	{
		return playerMoney;
	}
 
	public void setPlayerMoney(int amount) 
	{
		playerMoney = amount; 
	}
	
	public void handAddCard(Card card)
	{
		hand.add(card);
		handCalculate();
	}
	
	public void handCalculate()
	{
		int aces = 0;
		
		handTotalSum = 0;
		
		// Calculate only cards which are not aces
		for (Card c: hand)
		{
			if (!c.isAce())
			{
				handTotalSum += c.getValue();
			}
			else
			{
				aces++;
			}
		}
		
		if (aces > 0)
		{
			for (int i = aces; i > 0; i--)
			{
			    int temp = handTotalSum + aces - 1 + 11;
			    handTotalSum += temp <= 21 ? 11 : 1;
			}
		}
	}
	
	public int handTotal()
	{
		return handTotalSum;
	}
	
	public ArrayList<Card> handCards()
	{
		return hand;
	}
	
	@Override
	public String toString()
	{
		String result = " # " + playerName + " (Money: " + playerMoney + " | HandTotal: " + handTotalSum + ")\nCards:\n";
		for(Card c: hand)
		{
			result += " * " + c.toString() + "\n";
		}
		return  result;
	}
}