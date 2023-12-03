package project;

import java.util.*;

public class Player 
{
	private String playerName;
	private int playerMoney = 2000;
	private boolean hasBlackJack = false;
	private ArrayList<Card> hand = new ArrayList<>();
	private int handTotalSum = 0;
	private int gameCurrentBet = 0;
 
	public Player(String name) 
	{
		playerName = name;
	}
	
	public String getPlayerName() 
	{
		return playerName;
	}
	
	public int getPlayerMoney() 
	{
		return playerMoney;
	}
	
	public void addMoney(int amount)
	{
		playerMoney += amount;
	}
	
	public void removeMoney(int amount)
	{
		playerMoney -= amount;
	}
	
	public void setCurrentBet(int amount)
	{
		gameCurrentBet = amount;
	}
	
	public int getCurrentBet()
	{
		return gameCurrentBet;
	}
	
	public void handClean()
	{
		hand.clear();
		handTotalSum = 0;
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
	
	public boolean isBlackJack()
	{
		return hasBlackJack;
	}
	
	public void removeBlackJack()
	{
		hasBlackJack = false;
	}
	
	public void setBlackJack()
	{
		hasBlackJack = true;
	}
	
	@Override
	public String toString()
	{
		String result = "";
		result += " # " + playerName;
		result += " (Money: " + playerMoney;
		if (gameCurrentBet > 0)
		{
			result += " | Current Bet: " + gameCurrentBet; 
		}
		if (hand.size() > 0)
		{
			if (hasBlackJack)
			{
				result += " | BlackJack";
			}
			else
			{
				result += " | Hand Total: " + handTotalSum;
			}
		}
		result += ")";
		if (hand.size() > 0)
		{
			result += "\n  Cards:\n";
			for(Card c: hand)
			{
				result += " * " + c.toString() + "\n";
			}
		}
		return  result;
	}
}