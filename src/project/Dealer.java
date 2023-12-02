package project;

public class Dealer extends Player
{
	public Dealer()
	{
		super("Dealer");
	}
	
	@Override
	public String toString()
	{
		String result = " # " + getPlayerName() + " (HandTotal: " + handTotal() + ")\nCards:\n";
		for(Card c: handCards())
		{
			result += " * " + c.toString() + "\n";
		}
		return  result;
	}
}
