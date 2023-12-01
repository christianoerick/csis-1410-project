/*Blackjack By Riley Ogier and Ciaran Bogedahl
  Player class - This class is for creating a player profile with the player's name and money amount.
*/

package test;

public class Player {
   
   //fields
   public static String playerName;
   public static int playerMoney;
   
   //constructors
   public Player(String playerName) {
      this.playerName = playerName;
      playerMoney = 2000;
   }
   
   //methods
   public static String getPlayerName() {
      return playerName;
   }
   
   public static int getPlayerMoney() {
      return playerMoney;
   }
   
   public static void winPlayerMoney(int playerBet) {
      playerMoney = playerMoney + playerBet; 
   }
   
   public static void losePlayerMoney(int playerBet) {
      playerMoney = playerMoney - playerBet; 
   }
}