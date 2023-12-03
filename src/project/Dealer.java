package project;

public class Dealer extends Player
{
	private boolean showBlackJackInformation = false;
	
	public Dealer()
	{
		super("Dealer");
	}
	
	@Override
	public String toString()
	{
		boolean showTotal = true;
		String result = "";
		for(Card c: handCards())
		{
			result += " * " + c.toString() + "\n";
			if (c.isHidden())
			{
				showTotal = false;
			}
		}
		return " # " + getPlayerName() + " (HandTotal: " + ((showTotal || (isBlackJack() && showBlackJackInformation))?handTotal():"?") + (showBlackJackInformation ? " | IS " + (isBlackJack()?"":"NOT ") +  "BLACK JACK":"") + ")\nCards:\n" + result;
	}
	
	public void handAddCard(Card card)
	{
		super.handAddCard(card);
		if (handCards().size() == 2)
		{
			if (handTotal() == 21)
			{
				setBlackJack();
			}
			showBlackJackInformation = handCards().get(1).isAce();
		}
	}
}
