package project;

public class Card 
{
	private int[] values = new int[2];
	private int currentValue;
	String name;
	Suit suit;
	boolean hidden = false; // for dealer purpose
	
	public Card(String name, Suit suit, int[] values)
	{
		this.name = name;
		this.suit = suit;
		this.values = values;
		if (values[0] == values[1])
		{
			currentValue = values[0];
		}
	}
	
	public void setValue(int value)
	{
		currentValue = value;
	}
	
	public int getValue()
	{
		return currentValue;
	}
	
	public int[] getValues()
	{
		return values;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Suit getSuit()
	{
		return suit;
	}
	
	public void setHidden()
	{
		hidden = true;
	}
	
	public boolean isAce()
	{
		return values[0] != values[1];
	}
	
	@Override
	public String toString()
	{
		return name + " of " + suit + " | Values: " + (hidden ? "HIDDEN" : values[0] + ", " + values[1]);
	}
}
