package project;

import java.util.*;

public class TeamProject 
{
	private final static int MAX_PLAYERS = 2;
	
	private static Scanner scnr = new Scanner(System.in);
	private static Shoe shoe = new Shoe();
	private static boolean keepAsking = false;
	private static ArrayList<Player> players = new ArrayList<>();
	private static String temp, option;
	private static Dealer dealer = new Dealer();
	private static boolean gameIsOver = false;
	
    public static void main(String[] args) 
    {
    	// DO-WHILE: Because you like this soooooooooo much...
    	do
    	{
    		// DO-WHILE: Why not one more??? 
	    	do
	    	{
	    		temp = userInput("Enter the player name #"+(players.size() + 1)+": ");
	        	players.add(new Player(temp));	
	    	}
	    	while(players.size() < MAX_PLAYERS);
	
	    	// DO-WHILE: We needed, really!!!
	    	do
	    	{
	    		option = userInput("\nSTART A NEW GAME? Y FOR YES AND ANY KEY FOR NO: ");
	    		if (option.toUpperCase().equals("Y"))
	    		{	
	    			gameBets();
	    			
	    			shoe.startGame();
	    			
	    			gamePlayCards();
	    			
	    			gameCheckCards();
	    			
	    			gameCheckPlayerMoney();
	    			
	    			gameReset();
	    		}
	    	}
	    	while(option.toUpperCase().equals("Y") && !gameIsOver);
	    	
	    	players.clear();
	    	
	    	option = userInput("\nSTOP PLAYING? Y/N: ");
    	} 
    	while(!option.toUpperCase().equals("Y"));
    	
    	System.out.println("\nGAME IS OVER!!!");
    }
    
    private static void gameReset()
    {
    	System.out.println("\nPLAYER INFORMATION:");
    	for (Player p: players)
    	{
			if (p.isBlackJack())
			{
				p.removeBlackJack();
			}
			p.setCurrentBet(0);
			p.handClean();
    		System.out.println(p);
    	}
    	
    	dealer.removeBlackJack();
    	dealer.handClean();
    }
    
    private static void gameCheckPlayerMoney()
    {
    	for (Player p: players)
    	{
    		if (p.getPlayerMoney() < 5)
    		{
    			gameIsOver = true;
    		}
    	}
    }
    
    private static void gameCheckCards()
    {
    	System.out.println("\n"+dealer);
    	System.out.println("\nGAME RESULT:");
    	if (dealer.isBlackJack())
    	{
    		System.out.println("- Dealer has BlackJack!");
    		for (Player p: players)
        	{
    			if (p.isBlackJack())
    			{
    				System.out.println("- " + p.getPlayerName() + " has BlackJack! (bet@returned: " + p.getCurrentBet() + ")");
    			}
    			else
    			{
    				System.out.println("- " + p.getPlayerName() + " lost ("+p.handTotal()+")! (bet@removed: " + p.getCurrentBet() + ")");
    				p.removeMoney(p.getCurrentBet());
    			}
        	}
    	}
    	else if (dealer.handTotal() == 21)
    	{
    		System.out.println("- Dealer has " + dealer.handTotal() + "!");
    		for (Player p: players)
        	{
    			if (p.isBlackJack())
    			{
    				System.out.println("- " + p.getPlayerName() + " has BlackJack! (bet@doubled: " + p.getCurrentBet() + " -> "+((int)(p.getCurrentBet() * 1.5))+")");
    				p.addMoney((int)(p.getCurrentBet() * 1.5));
    			}
    			else if (p.handTotal() != 21)
    			{
    				System.out.println("- " + p.getPlayerName() + " lost ("+p.handTotal()+")! (bet@removed: " + p.getCurrentBet() + ")");
    				p.removeMoney(p.getCurrentBet());
    			}
    			else
    			{
    				System.out.println("- " + p.getPlayerName() + " tied ("+p.handTotal()+")! (bet@returned: " + p.getCurrentBet() + ")");
    			}
        	}
    	}
    	else if (dealer.handTotal() > 21)
    	{
    		System.out.println("- Dealer has " + dealer.handTotal() + "!");
    		for (Player p: players)
        	{
    			if (p.isBlackJack())
    			{
    				System.out.println("- " + p.getPlayerName() + " has BlackJack! (bet@doubled: " + p.getCurrentBet() + " -> "+((int)(p.getCurrentBet() * 1.5))+")");
    				p.addMoney((int)(p.getCurrentBet() * 1.5));
    			}
    			else if (p.handTotal() > 21)
    			{
    				System.out.println("- " + p.getPlayerName() + " lost ("+p.handTotal()+")! (bet@removed: " + p.getCurrentBet() + ")");
    				p.removeMoney(p.getCurrentBet());
    			}
    			else
    			{
    				System.out.println("- " + p.getPlayerName() + " won ("+p.handTotal()+")! (bet@added: " + p.getCurrentBet() + ")");
    				p.addMoney(p.getCurrentBet());
    			}
        	}
    	}
    	else
    	{
    		System.out.println("- Dealer has " + dealer.handTotal() + "!");
    		for (Player p: players)
        	{
    			if (p.isBlackJack())
    			{
    				System.out.println("- " + p.getPlayerName() + " has BlackJack! (bet@doubled: " + p.getCurrentBet() + " -> "+((int)(p.getCurrentBet() * 1.5))+")");
    				p.addMoney((int)(p.getCurrentBet() * 1.5));
    			}
    			else if (p.handTotal() > 21 || p.handTotal() < dealer.handTotal())
    			{
    				System.out.println("- " + p.getPlayerName() + " lost ("+p.handTotal()+")! (bet@removed: " + p.getCurrentBet() + ")");
    				p.removeMoney(p.getCurrentBet());
    			}
    			else if (p.handTotal() != dealer.handTotal())
    			{
    				System.out.println("- " + p.getPlayerName() + " won ("+p.handTotal()+")! (bet@added: " + p.getCurrentBet() + ")");
    				p.addMoney(p.getCurrentBet());
    			}
    			else
    			{
    				System.out.println("- " + p.getPlayerName() + " tied ("+p.handTotal()+")! (bet@returned: " + p.getCurrentBet() + ")");
    			}
        	}
    	}
    }
    
    private static void gamePlayCards()
    {
    	System.out.println("");
    	
    	// give cards
    	for (int i = 1; i <= 2; i++)
		{
    		for (Player p: players)
        	{
    			Card c = shoe.buyCard(false);
    			p.handAddCard(c);
        	}
    		
    		Card c = shoe.buyCard(dealer.handTotal() == 0);
			dealer.handAddCard(c);
		}
    	
    	// show everyone's cards
    	for (Player p: players)
    	{
    		System.out.println(p);
    	}
    	System.out.println(dealer);
    	
    	// why not another do while?
		for (Player p: players)
    	{
			if (p.handTotal() == 21)
			{
				p.setBlackJack();
			}
			else if (!dealer.isBlackJack() && p.handTotal() < 21)
			{
				// DO-WHILE: Ooooooooops one more here!!! =]
				do
				{
					keepAsking = true;
    				System.out.println("\n"+p);
        			System.out.print("\n("+p.getPlayerName()+") Enter S for stand or H for Hit: ");
        			
                	temp = scnr.nextLine();
                	if (temp.toUpperCase().equals("S"))
                	{
                		keepAsking = false;
                	}
                	else if (temp.toUpperCase().equals("H"))
                	{
                		Card c = shoe.buyCard(false);
            			p.handAddCard(c);
            			
            			if (p.handTotal() >= 21)
        				{
            				keepAsking = false;
        				}
                	}
                	else
                	{
                		System.out.println("\n("+p.getPlayerName()+") ENTER A VALID OPTION: H or S");
                	}
				}
				while(keepAsking);
			}
    	}
		
		// Dealer's turn
		for (Card c: dealer.handCards())
		{
			c.setPublic();
		}
		
		while(dealer.handTotal() < 17)
		{
			Card c = shoe.buyCard(false);
			dealer.handAddCard(c);
		}
    }
    
    private static void gameBets()
    {
    	System.out.println("");
    	
		// ask for bets
		for (Player p: players)
    	{
			// DO-WHILE: this is the last one, we promise!!!
			do
			{
				keepAsking = true;
				System.out.println(p);
				temp = userInput(p.getPlayerName() + "'s bet: ");
				int bet = Integer.parseInt(temp);
				if (bet >= 5)
				{
					if (bet <= p.getPlayerMoney())
					{
						p.setCurrentBet(bet);
						keepAsking = false;
					}
					else
					{
						System.out.println("YOU HAVE ONLY '"+p.getPlayerMoney()+"', SO YOU CAN ONLY BET UP TO '"+p.getPlayerMoney()+"'.");
					}
				}
				else
				{
					System.out.println("ENTER AT LEAST 5");
				}
			}
			while(keepAsking);
    	}
    }
    
    private static String userInput(String question)
    {
    	System.out.print(question);
    	return scnr.nextLine();
    }
}
