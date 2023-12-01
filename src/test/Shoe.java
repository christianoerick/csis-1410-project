/*Blackjack By Riley Ogier and Ciaran Bogedahl
  Shoe class creates the shoe with 6 decks of cards.
  The main game draws cards from the created shoe object.
*/

package test;

import java.util.ArrayList;

public class Shoe {
   
   //fields
   public static ArrayList<String> shoe;
   private static ArrayList<String> deck;
   private static ArrayList<String> shuffledShoe;
   private static boolean firstDraw = true;
 
   //constuctors
   public Shoe() {
   
      createShoe();
      
   }
   
   //methods
   
   public static void createShoe() {
      
      //Fills the deck array with 52 playing cards
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
      
      //loops and fills the shoe array with the contents of the deck array 6 times
      for (int i = 0; i < 6; i++) {
         for (int j = 0; j < 52; j++) {
            shoe.add(deck.get(j));
         }
      }
      
      firstDraw = true;
   }
   
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
   
   //aceCount checks if an ace is drawn
   public static boolean aceCount(String card) {
      if (card.contains("Ace")) {
         return true;
      } else {
         return false;
      }
   }
   
   //Takes 10 off a bust handTotal if aces are present in the hand
   public static int handTotalMod(int handTotal, int aceNum) {
      if (handTotal > 21) {
         if (aceNum > 0) {
            handTotal -= 10;
         }
      }
      return handTotal;
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

}
