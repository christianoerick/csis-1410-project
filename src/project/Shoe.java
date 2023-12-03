package project;

import java.util.*;

public class Shoe 
{
	public ArrayList<Card> cards = new ArrayList<>();
	public ArrayList<Card> shoe = new ArrayList<>();
	private ArrayList<String> shuffledShoe;
	private boolean firstDraw = true;
 
	public Shoe() 
	{
		createCards();
	}
	
	public Card buyCard(boolean hidden)
	{
		Card card = shoe.get(0);
		shoe.remove(0);
		if (hidden)
		{
			card.setHidden();
		}
		else
		{
			card.setPublic();
		}
		return card;
	}
	
	public void startGame()
	{
		if (shoe.size() < 50)
		{
			ArrayList<Card> currentShoe = new ArrayList<>();
			for (int i = 1; i <= 6; i++)
				for(Card c: cards)
					currentShoe.add(c);
			
			shoe = currentShoe;
			shuffleCards();
		}
		
		// dealer will start giving cards starting with the first user
	}
	
	public void shuffleCards()
	{
		for (int i = 1; i <= 10; i++)
		{
			Collections.shuffle(shoe);
		}
	}
	
	public void createCards()
   {
	   String[] cardList = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	   for (Suit suit : Suit.values())
	   {
		   for (String c: cardList)
		   {
			   int[] values = new int[2];
			   if (c.equals("Ace"))
			   {
				   values[0] = 1;
				   values[1] = 11;
				}
				else if (c.equals("J") || c.equals("Q") || c.equals("K"))
				{
					values[0] = 10;
					values[1] = 10;
				}
				else
				{
					values[0] = Integer.parseInt(c);
					values[1] = Integer.parseInt(c);
				}
				cards.add(new Card(c, suit, values));
			}
		}
   }
}
