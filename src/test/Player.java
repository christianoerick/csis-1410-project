package test;

/*Blackjack By Riley Ogier and Ciaran Bogedahl
  Player class - This class is for creating a player profile with the player's name and money amount.
*/


public class Player {
   
   //fields
   public static String playerName;
   public static int playerMoney;
   
   //constructors
   public Player(String playerName) {
      this.playerName = playerName;
      playerMoney = 1000;
   }
   
   //methods
   public static String getPlayerName() {
      return playerName;
   }
   
   public static int getPlayerMoney() {
      return playerMoney;
   }
   
   public void setPlayerMoney(int playerMoney) {
      this.playerMoney = playerMoney; 
   }
   
  // @Override
  // public String toString() {
  //    return playerName;
  // }
}