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
      
   public void createShoe() 
   {
      /*
      deck = new ArrayList<String>();
      shoe = new ArrayList<String>();
      deck.add("Ace of Clubs");
      deck.add("2 of Clubs");
      deck.add("3 of Clubs");
      deck.add("4 of Clubs");
      deck.add("5 of Clubs");
      deck.add("6 of Clubs");
      deck.add("7 of Clubs");
      deck.add("8 of Clubs");
      deck.add("9 of Clubs");
      deck.add("10 of Clubs");
      deck.add("Jack of Clubs");
      deck.add("Queen of Clubs");
      deck.add("King of Clubs");
      deck.add("Ace of Diamonds");
      deck.add("2 of Diamonds");
      deck.add("3 of Diamonds");
      deck.add("4 of Diamonds");
      deck.add("5 of Diamonds");
      deck.add("6 of Diamonds");
      deck.add("7 of Diamonds");
      deck.add("8 of Diamonds");
      deck.add("9 of Diamonds");
      deck.add("10 of Diamonds");
      deck.add("Jack of Diamonds");
      deck.add("Queen of Diamonds");
      deck.add("King of Diamonds");
      deck.add("Ace of Hearts");
      deck.add("2 of Hearts");
      deck.add("3 of Hearts");
      deck.add("4 of Hearts");
      deck.add("5 of Hearts");
      deck.add("6 of Hearts");
      deck.add("7 of Hearts");
      deck.add("8 of Hearts");
      deck.add("9 of Hearts");
      deck.add("10 of Hearts");
      deck.add("Jack of Hearts");
      deck.add("Queen of Hearts");
      deck.add("King of Hearts");
      deck.add("Ace of Spades");
      deck.add("2 of Spades");
      deck.add("3 of Spades");
      deck.add("4 of Spades");
      deck.add("5 of Spades");
      deck.add("6 of Spades");
      deck.add("7 of Spades");
      deck.add("8 of Spades");
      deck.add("9 of Spades");
      deck.add("10 of Spades");
      deck.add("Jack of Spades");
      deck.add("Queen of Spades");
      deck.add("King of Spades");
   
      for (int i = 0; i < 6; i++) {
         for (int j = 0; j < 52; j++) {
            shoe.add(deck.get(j));
         }
      }
      
      firstDraw = true;
      /* */
   }
   
   /*
   public static void shuffleShoe() {
      shuffledShoe = new ArrayList<String>();
      //iterate through the size of the shoe, so each card can be pulled
      while(shoe.size()>0){
         //Select a random index to pull
         int cardPulled = (int)(Math.random()*(shoe.size()-1));
         //Add this random card to the new shuffled shoe
         shuffledShoe.add(shoe.get(cardPulled));
         //Remove the pulled card from the original shoe
         shoe.remove(cardPulled);
      }
      shoe = shuffledShoe;
   }
   
   //Method to draw a card from the shoe
   public static String drawCard() {
      if (firstDraw == true) {
         firstDraw = false;
         return shoe.get(0);
      } else {
         shoe.remove(0);
         return shoe.get(0);
      }
   }
   
   //getCardValue assigns a card with a integer value when called to be used in the game
   public static int getCardValue(String card) {
      if (card.contains("Ace")) {
         return 11;
      }
      
      else if (card.contains("2")) {
         return 2;
      }
      
      else if (card.contains("3")) {
         return 3;
      }
      
      else if (card.contains("4")) {
         return 4;
      }
      
      else if (card.contains("5")) {
         return 5;
      }
      
      else if (card.contains("6")) {
         return 6;
      }
      
      else if (card.contains("7")) {
         return 7;
      }
      
      else if (card.contains("8")) {
         return 8;
      }
      
      else if (card.contains("9")) {
         return 9;
      }
      
      else if (card.contains("10")) {
         return 10;
      }
      
      else if (card.contains("Jack")) {
         return 10;
      }
      
      else if (card.contains("Queen")) {
         return 10;
      }
      
      else if (card.contains("King")) {
         return 10;
      }
      
      else {
         return 0;
      }
   }
   
   @Override
   public String toString() {  //for checking what contents of the shoe is
      String print = "";
      for (String el : shoe) {
         print += el;
         print += "  ";
      }
      return print;
   }
/* */
}
