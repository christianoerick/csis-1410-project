package project;

import java.util.*;

public class TeamProject 
{
	private static Scanner scnr = new Scanner(System.in);
	
    public static void main(String[] args) 
    {
        String temp;

    	ArrayList<Player> players = new ArrayList<>();
    	
    	// Because you like this soooooooooo much...
    	do
    	{
    		temp = userInput("Enter the player name: ");
        	players.add(new Player(temp));	
    	}
    	while(players.size() < 2);
    	
    	Dealer dealer = new Dealer();
    	players.add(dealer);

    	Shoe shoe = new Shoe();
    	
    	// pending - bets
    	shoe.startGame();
    	
    	for (int i = 1; i <= 2; i++)
		{
    		for (Player p: players)
        	{
    			Card c = shoe.buyCard(i == 1 && p instanceof Dealer);
    			p.handAddCard(c);
        	}
		}
    	
    	boolean gameIsOver = false;
    	boolean keepAsking = false;
    	
    	// why not another do while?
		for (Player p: players)
    	{
			if (p instanceof Dealer)
			{
				if (p.handTotal() == 21)
				{
					gameIsOver = true;
				}
			}
			else
			{
				if (p.handTotal() == 21)
				{
					// black jack
				}
				else if (p.handTotal() > 21)
				{
					// player is out
				}
				else
				{
					do
					{
						keepAsking = true;
        				System.out.println(p);
            			System.out.print("Enter S for stand or H for Hit: ");
            			
                    	temp = scnr.nextLine();
                    	if (temp.toUpperCase().equals("S"))
                    	{
                    		keepAsking = false;
                    	}
                    	else if (temp.toUpperCase().equals("H"))
                    	{
                    		Card c = shoe.buyCard(false);
                			p.handAddCard(c);
                			
                			if (p.handTotal() > 21)
            				{
            					// player is out
                				keepAsking = false;
            				}
                    	}
                    	else
                    	{
                    		System.out.println("ENTER A VALID OPTION: H or S");
                    	}
					}
					while(!keepAsking);
				}
			}
    	}
    	
    	for (Player p: players)
    	{
    		System.out.println(p);
    	}
    	
    }
    
    private static String userInput(String question)
    {
    	System.out.print("Enter the player name: ");
    	return scnr.nextLine();
    }
    	
    	/*

        String hit = "null", playAgain = "y", playerName;//, playerMoney;
        String nextCard, card1, card2, dealerCard1, dealerCard2;
        int cardTotal = 0, dealerTotal = 0, bet = 0;
        
        Scanner keyboard = new Scanner(System.in);
        //Create Shoe to draw cards from
        Shoe gameShoe = new Shoe();
        //Shuffle the Shoe 8 times for most randomness
        Shoe.shuffleShoe();
        Shoe.shuffleShoe();
        Shoe.shuffleShoe();
        Shoe.shuffleShoe();
        Shoe.shuffleShoe();
        Shoe.shuffleShoe();
        Shoe.shuffleShoe();
        Shoe.shuffleShoe();
        
        System.out.println("Enter name: ");
        playerName = keyboard.nextLine();
        
        Player player1 = new Player(playerName);
        
        System.out.println("Welcome to Blackjack, " + Player.getPlayerName() + "!");
        System.out.println("You'll start out with $" + Player.getPlayerMoney() + ". Good Luck!");
        System.out.println();
        
        // Player gets two starting cards

        while (playAgain.equals("y"))
        {   
            //Check to see if enough cards are left in the Shoe
            if (gameShoe.shoe.size() > 52) {
               
               while (bet < 1 || bet > Player.getPlayerMoney()) {
                  System.out.println("Enter bet amount ($1 or higher): ");
                  bet = keyboard.nextInt();
                  if (bet < 1 || bet > Player.getPlayerMoney()) {
                     System.out.println("Invalid Amount");
                  } else {
                     break;
                  }
               }
               
               card1 = Shoe.drawCard();               
               dealerCard1 = Shoe.drawCard();
               card2 = Shoe.drawCard();
               dealerCard2 = Shoe.drawCard();
               cardTotal = Shoe.getCardValue(card1) + Shoe.getCardValue(card2);
   
               //Dealers two card total and display only one dealer card
               dealerTotal = Shoe.getCardValue(dealerCard1) + Shoe.getCardValue(dealerCard2);
               System.out.println("Dealer shows: " + dealerCard2);
               
               //Display players first two cards & card total
               System.out.println("Your First Cards: " + card1 + ", " +card2);
               System.out.println("Total: "+ cardTotal);
               System.out.println();
               
               while (!hit.equalsIgnoreCase("y")) {
                  System.out.print("Do you want to hit (y/n)?: ");
                  hit = keyboard.nextLine();
                  if ((hit.equalsIgnoreCase("y")) || (hit.equalsIgnoreCase("n"))) {
                     break;
                  }
                  else {
                     System.out.println("Invalid Input");
                  }
               }
               while (hit.equals("y"))
               {
                   nextCard = Shoe.drawCard();
                   cardTotal += Shoe.getCardValue(nextCard);
                   System.out.println("You Draw: " + nextCard);
                   System.out.println("Total: " + cardTotal);
                   System.out.println();
   
                   if (cardTotal > 21)
                   {
                   System.out.println("You busted, Dealer Wins");
                   hit = "null";
                   bet = 0;
                   }   
                   if (cardTotal < 21)
                   {
                   System.out.print("Do you want to hit? (y/n)?: ");
                   hit = keyboard.nextLine();
                   }
                 }
                   
                 while (hit.equals("n"))
                 {
                   System.out.println("Dealer shows: " + dealerCard1 + ", " + dealerCard2);
                   System.out.println("Dealer total: " + dealerTotal);
                   System.out.println();
                   while (dealerTotal < 17)
                   {
                   nextCard = Shoe.drawCard();
                   dealerTotal += Shoe.getCardValue(nextCard);
                   System.out.println("Since dealer total is under 17, dealer draws.");
                   System.out.println("Dealer draws: " + nextCard);
                   System.out.println("New total: " + dealerTotal);
                   System.out.println();
                   }
                   if (dealerTotal < cardTotal)
                   {
                   System.out.println("You win!");
                   hit = "null";
                   bet = 0;
                   }
                   
                   if ((dealerTotal < 22) && (dealerTotal > cardTotal))
                   {
                   System.out.println("Dealer wins, you lose.");
                   hit = "null";
                   bet = 0;
                   }
                   if (dealerTotal > 21)
                   {
                   System.out.println("Dealer Busts, You Win!");
                   hit = "null";
                   bet = 0;
                   }
                   if (dealerTotal == cardTotal)
                   {
                   System.out.println("Push.");
                   hit = "null";
                   bet = 0;
                   }
                  }
                   
                   while (playAgain != "y" || playAgain != "n")
                   {
                   System.out.println("Play Again? (y/n): ");
                   playAgain = keyboard.nextLine();
                   if (playAgain.equalsIgnoreCase("y"))
                   {
                    playAgain = "y";
                    System.out.println();
                    break;
                   } else if (playAgain.equalsIgnoreCase("n"))
                   {
                    System.out.println();
                    break;
                   } else {
                    System.out.println("Invalid choice");
                   }
                   System.out.println();
                   }
              } else {
                  System.out.println("Shoe running low. Replenishing and reshuffling Shoe.");
                  System.out.println();
                  Shoe.createShoe();
                  Shoe.shuffleShoe();
                  Shoe.shuffleShoe();
                  Shoe.shuffleShoe();
                  Shoe.shuffleShoe();
                  Shoe.shuffleShoe();
                  Shoe.shuffleShoe();
                  Shoe.shuffleShoe();
                  Shoe.shuffleShoe();
                }
              
            }
/**/
}
