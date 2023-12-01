/*Blackjack By Riley Ogier and Ciaran Bogedahl
  This is the Main Class that will run the game
  This version of Blackjack follows some typical Blackjack rules: 6 deck shoe, $5 minimum bet, dealer stands on all 17s, blackjack pays 3/2.
  Some rules this Blackjack doesn't include: insurance, splitting, double down, surrender.
*/

package test;

import java.util.Scanner;
import java.util.*;

public class BlackjackMain {

    public static void main(String[] args) {

        String hit = "null", playAgain = "y", playerName;
        String nextCard, card1, card2, dealerCard1, dealerCard2;
        int cardTotal = 0, dealerTotal = 0, bet = 0;
        int aceCountDealer = 0, aceCountPlayer = 0, temp = 0;
        boolean playerBlackjack = false;
        
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

        while (playAgain.equals("y")) {   
            //Check to see if a good amount of cards are left in the Shoe. If not the shoe will be reset and shuffled.
            if (gameShoe.shoe.size() > 40) {
               
               //while loop for player to enter valid bet - minimum bet $5
                  while (bet < 5 || bet > Player.getPlayerMoney()) {
                     if (Player.getPlayerMoney() > 4) {
                        System.out.println("Your money: $" + Player.getPlayerMoney());
                        System.out.println("Enter bet amount ($5 or higher): ");
                        bet = keyboard.nextInt();
                        if (bet < 5 || bet > Player.getPlayerMoney()) {
                           System.out.println("Invalid Amount");
                        } else {
                           break;
                        }
                     } else {
                        System.out.println("Looks like your broke. Here's another $2000!");
                        Player.winPlayerMoney(2000);
                     }
                  }
               
               cardTotal = 0;
               dealerTotal = 0;
               aceCountDealer = 0;
               aceCountPlayer = 0;
               playerBlackjack = false;
               card1 = Shoe.drawCard();
               if (Shoe.aceCount(card1) == true) {  //Counts aces
                  aceCountPlayer++;
               }         
               dealerCard1 = Shoe.drawCard();
               if (Shoe.aceCount(dealerCard1) == true) {
                  aceCountDealer++;
               }
               card2 = Shoe.drawCard();
               if (Shoe.aceCount(card2) == true) {
                  aceCountPlayer++;
               } 
               dealerCard2 = Shoe.drawCard();
               if (Shoe.aceCount(dealerCard2) == true) {
                  aceCountDealer++;
               }
               cardTotal = Shoe.getCardValue(card1) + Shoe.getCardValue(card2);
               temp = Shoe.handTotalMod(cardTotal, aceCountPlayer);
               if (temp != cardTotal) {
                  aceCountPlayer--;
               }
               cardTotal = temp;
               //Dealers two card total and display only one dealer card
               dealerTotal = Shoe.getCardValue(dealerCard1) + Shoe.getCardValue(dealerCard2); 
               temp = Shoe.handTotalMod(dealerTotal, aceCountDealer);
               if (temp != dealerTotal) {
                  aceCountDealer--;
               }
               dealerTotal = temp;
               
               System.out.println("Dealer shows: " + dealerCard2);
               
               //Display players first two cards & card total
               System.out.println("Your First Cards: " + card1 + ", " +card2);
               System.out.println("Total: "+ cardTotal);
               System.out.println();
               keyboard.nextLine();
               if (cardTotal == 21) {
                  hit = "n";
                  playerBlackjack = true;
               }
               
               while (!hit.equalsIgnoreCase("y") && cardTotal != 21) {
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
                   if (Shoe.aceCount(nextCard) == true) {
                     aceCountPlayer++;
                   }
                   cardTotal += Shoe.getCardValue(nextCard);
                   temp = Shoe.handTotalMod(cardTotal, aceCountPlayer);
                   if (temp != cardTotal) {
                     aceCountPlayer--;
                   }
                   cardTotal = temp;
                   System.out.println("You Draw: " + nextCard);
                   System.out.println("Total: " + cardTotal);
                   System.out.println();
   
                   if (cardTotal > 21)
                   {
                   System.out.println("You busted, Dealer Wins. You lose $" + bet + ".");
                   hit = "null";
                   Player.losePlayerMoney(bet);
                   bet = 0;
                   }   
                   if (cardTotal < 21)
                   {
                   System.out.print("Do you want to hit? (y/n)?: ");
                   hit = keyboard.nextLine();
                   }
                   if (cardTotal == 21)
                   {
                   hit = "n";
                   break;
                   }
                 }
                   
                 while (hit.equals("n"))
                 {
                   System.out.println("Dealer shows: " + dealerCard1 + ", " + dealerCard2);
                   System.out.println("Dealer total: " + dealerTotal);
                   System.out.println();
                   if (dealerTotal == 21) {
                     if ((cardTotal == 21) && (playerBlackjack == true)) {
                        System.out.println("Push.");
                        hit = "null";
                        bet = 0;
                        break;
                     } else {
                        System.out.println("Dealer blackjack, you lose $" + bet + ".");
                        hit = "null";
                        Player.losePlayerMoney(bet);
                        bet = 0;
                        break;
                     }
                     
                   } else if ((cardTotal == 21) && (playerBlackjack == true)) {
                     System.out.println("Blackjack! You win $" + ((int)(bet * 1.5)) + "!");
                     hit = "null";
                     Player.winPlayerMoney((int)(bet * 1.5));
                     bet = 0;
                     break;
                     }

                   while (dealerTotal < 17)
                   {
                   nextCard = Shoe.drawCard();
                   if (Shoe.aceCount(nextCard) == true) {
                     aceCountDealer++;
                   }
                   dealerTotal += Shoe.getCardValue(nextCard);
                   temp = Shoe.handTotalMod(dealerTotal, aceCountDealer);
                   if (temp != dealerTotal) {
                     aceCountDealer--;
                   }
                   dealerTotal = temp;
                   System.out.println("Since dealer total is under 17, dealer draws.");
                   System.out.println("Dealer draws: " + nextCard);
                   System.out.println("New total: " + dealerTotal);
                   System.out.println();
                   }
                   if (dealerTotal < cardTotal)
                   {
                   System.out.println("You win $" + bet + "!");
                   hit = "null";
                   Player.winPlayerMoney(bet);
                   bet = 0;
                   }
                   
                   if ((dealerTotal < 22) && (dealerTotal > cardTotal))
                   {
                   System.out.println("Dealer wins, you lose $" + bet + ".");
                   hit = "null";
                   Player.losePlayerMoney(bet);
                   bet = 0;
                   }
                   if (dealerTotal > 21)
                   {
                   System.out.println("Dealer Busts, You Win $" + bet + "!");
                   hit = "null";
                   Player.winPlayerMoney(bet);
                   bet = 0;
                   }
                   if (dealerTotal == cardTotal)
                   {
                   System.out.println("Push.");
                   hit = "null";
                   bet = 0;
                   }
                  } //end while(hit.equals("n"))
                   
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
            System.out.println("You ended with $" + Player.getPlayerMoney() + ". Goodbye " + Player.getPlayerName() + "!");
}
}