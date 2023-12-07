package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Card 
{
	private int[] values = new int[2];
	private int currentValue;
	String name;
	Suit suit;
	boolean hidden = false; // for dealer purpose
	private Color color;
	private String symbol;
	
	public Card(String name, Suit suit, int[] values)
	{
		this.name = name;
		this.suit = suit;
		this.values = values;
		if (values[0] == values[1])
		{
			currentValue = values[0];
		}
		this.color = (suit.toString().equals("SPADES") || suit.toString().equals("CLUBS"))?Color.BLACK:Color.RED;
		if (suit.toString().equals("SPADES"))
		{
			symbol = "♠";
		}
		else if (suit.toString().equals("HEARTS"))
		{
			symbol = "♥";
		}
		else if (suit.toString().equals("CLUBS"))
		{
			symbol = "♣";
		}
		else if (suit.toString().equals("DIAMONDS"))
		{
			symbol = "♦";
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
	
	public Color getColor()
	{
		return color;
	}
	
	public String getSymbol()
	{
		return symbol;
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
	
	public void setPublic()
	{
		hidden = false;
	}
	
	public boolean isHidden()
	{
		return hidden;
	}
	
	public boolean isAce()
	{
		return values[0] != values[1];
	}
	
	public JPanel panel()
	{
		@SuppressWarnings("serial")
		JPanel card = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		        g.drawString(hidden ? "?" : name.substring(0,  1), 10, 20);
		        g.drawString(symbol, getWidth() - 20, getHeight() - 5);
			}
		};
        card.setBackground(new Color(255, 255, 255));
        card.setOpaque(true);
        card.setForeground(color);
        return card;
	}
	
	@Override
	public String toString()
	{
		return (hidden ? "HIDDEN" : name + " of " + suit);
	}
}