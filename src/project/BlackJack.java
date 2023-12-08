package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import java.awt.CardLayout;

public class BlackJack extends JFrame {

	private static final long serialVersionUID = 1L;
		
	private Shoe shoe = new Shoe();
	private ArrayList<Player> players = new ArrayList<>();
	private Dealer dealer = new Dealer();
	private boolean keepAsking = false;
	private String temp;
	private int playTurn;
	
	private EmptyBorder border2 = new EmptyBorder(2, 2, 2, 2);
	private EmptyBorder border10 = new EmptyBorder(10, 10, 10, 10);
	private EmptyBorder border010 = new EmptyBorder(0, 10, 0, 10);
	private EmptyBorder border15 = new EmptyBorder(15, 15, 15, 15);
	
	private ImageIcon image = new ImageIcon(BlackJack.class.getResource("/project/assets/bg.png"));
	private ImageIcon warning = new ImageIcon(BlackJack.class.getResource("/project/assets/warning.png"));
	private ImageIcon question = new ImageIcon(BlackJack.class.getResource("/project/assets/question.png"));
	
	private JPanel gamePanel;
	private JPanel selectionPanel;
	
	private JPanel contentPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel actionsPanel = new JPanel();
	private JPanel infoPanel = new JPanel();
	private JPanel dealerPanel = new JPanel();
	private JPanel playersPanel = new JPanel();
	
	private Color goldColor = new Color(169, 127, 32);
	private Color redColor = new Color(194, 0, 17);
	private Color blackColor = new Color(0, 0, 0);
	
	private Color whiteColor50 = new Color(255, 255, 255, 50);
	private Color whiteColor = new Color(255, 255, 255);
	
	private JPanel topPanel = new JPanel();
	private JPanel topWelcomePanel = new JPanel();
	private JPanel topPanelButtons1 = new JPanel();
	private JPanel topPanelButtons2 = new JPanel();
	private JLabel gameLabel = new JLabel("Black Jack");
	private JLabel gameWelcomeLabel = new JLabel("Black Jack");
	private JPanel welcomeFormPanel = new JPanel();
	private JPanel formPanel = new JPanel();
	private JPanel welcomePanel = new JPanel();
	private JPanel actionsPanelContent = new JPanel();
	
	private JButton hitBtn = new JButton("HIT");
	private JButton standBtn = new JButton("STAND");
	private JButton quitGameButton = new JButton("QUIT");
	private JButton saveBtn = new JButton("SAVE");
	private JButton loadBtn = new JButton("LOAD");
	private JButton gameStartBtn = new JButton("START GAME");
	private JButton loadWelcomeBtn = new JButton("LOAD GAME");
	private JButton newGameBtn = new JButton("NEW");
	
	private JPanel player1Panel = new JPanel();
	private JPanel player1TopPanel = new JPanel();
	private JLabel player1Money = new JLabel(currency(2000));
	private JLabel player1Bet = new JLabel("#1 ?");
	private JLabel player1Name = new JLabel("PlayerName");
	private JPanel player1BottomPanel = new JPanel();
	private JPanel formPlayer1Panel = new JPanel();
	private JLabel formPlayer1Label = new JLabel("Player Name #1: ");
	private JTextField formPlayer1Input = new JTextField();
	
	private JPanel player2Panel = new JPanel();
	private JPanel player2TopPanel = new JPanel();
	private JLabel player2Money = new JLabel(currency(2000));
	private JLabel player2Bet = new JLabel("#2 ?");
	private JLabel player2Name = new JLabel("PlayerName");
	private JPanel player2BottomPanel = new JPanel();
	private JPanel formPlayer2Panel = new JPanel();
	private JLabel formPlayer2Label = new JLabel("Player Name #2: ");
	private JTextField formPlayer2Input = new JTextField();
	
	private JPanel player3Panel = new JPanel();
	private JPanel player3TopPanel = new JPanel();
	private JLabel player3Money = new JLabel(currency(2000));
	private JLabel player3Bet = new JLabel("#3 ?");
	private JLabel player3Name = new JLabel("PlayerName");
	private JPanel player3BottomPanel = new JPanel();
	private JPanel formPlayer3Panel = new JPanel();
	private JLabel formPlayer3Label = new JLabel("Player Name #3: ");
	private JTextField formPlayer3Input = new JTextField();

	private JPanel player4Panel = new JPanel();
	private JPanel player4TopPanel = new JPanel();
	private JLabel player4Money = new JLabel(currency(2000));
	private JLabel player4Bet = new JLabel("#4 ?");
	private JLabel player4Name = new JLabel("PlayerName");
	private JPanel player4BottomPanel = new JPanel();
	private JPanel formPlayer4Panel = new JPanel();
	private JLabel formPlayer4Label = new JLabel("Player Name #4: ");
	private JTextField formPlayer4Input = new JTextField();
	private JPanel dealerBottomPanel = new JPanel();
	private JPanel infoPanelContent = new JPanel();
	private JTextPane mainLabel = new JTextPane();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					BlackJack frame = new BlackJack();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public BlackJack() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 620);
		

		selectionPanel = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(image.getImage(), 0, 0, null);
			}
		};
		selectionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(selectionPanel);
		
		createGamePanel();
		createWelcomePanel();
		
		gamePanel.setVisible(false);
    	welcomePanel.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	private void createGamePanel()
	{
		selectionPanel.setLayout(new CardLayout(0, 0));
		gamePanel = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(image.getImage(), 0, 0, null);
			}
		};
		gamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		gamePanel.setLayout(new BorderLayout(0, 0));
		selectionPanel.add(gamePanel, "name_63275556255541");
		
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setHgap(20);
		topPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
		topPanel.setOpaque(false);
		gamePanel.add(topPanel, BorderLayout.NORTH);

		topPanelButtons1.setPreferredSize(new Dimension(310, 40));
		topPanelButtons1.setOpaque(false);
		topPanelButtons1.setLayout(new GridLayout(1, 0, 10, 0));
		
		topPanel.add(topPanelButtons1);
		hitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePlayTurnAction("hit");
			}
		});
		topPanelButtons1.add(hitBtn);
		hitBtn.setEnabled(false);
		standBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePlayTurnAction("stand");
			}
		});
		
		topPanelButtons1.add(standBtn);
		standBtn.setEnabled(false);
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGameBtn.setEnabled(false);
				hitBtn.setEnabled(true);
				standBtn.setEnabled(true);
				
    			gameBets();
    			
    			shoe.startGame();
    			
    			gamePlayCards();
    			/*
    			
    			gameCheckCards();
    			
    			gameCheckPlayerMoney();
    			
    			gameReset();
    			/* */
			}
		});
		
		topPanelButtons1.add(newGameBtn);
		
		gameLabel.setForeground(goldColor);
		gameLabel.setFont(new Font("Krungthep", Font.BOLD, 30));
		gameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameLabel.setOpaque(false);
		gameLabel.setPreferredSize(new Dimension(200, 40));
		topPanel.add(gameLabel);
		
		topPanelButtons2.setPreferredSize(new Dimension(310, 40));
		topPanelButtons2.setOpaque(false);
		topPanelButtons2.setLayout(new GridLayout(1, 0, 10, 0));
		topPanel.add(topPanelButtons2);
		quitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int input = JOptionPane.showConfirmDialog(null, "Are you sure?", "QUIT GAME", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, question);
		        if (input == 0) {
		        	formPlayer1Input.setText("");
		        	formPlayer2Input.setText("");
		        	formPlayer3Input.setText("");
		        	formPlayer4Input.setText("");
		        	dealer.handClean();
		        	players.clear();
		        	gamePanel.setVisible(false);
		        	welcomePanel.setVisible(true);
		        }
			}
		});
		
		topPanelButtons2.add(quitGameButton);
		
		saveBtn.setEnabled(false);
		topPanelButtons2.add(saveBtn);
		
		loadBtn.setEnabled(false);
		topPanelButtons2.add(loadBtn);
		
		contentPanel.setOpaque(false);
		//contentPanel.setBackground(new Color(255, 255, 255, 50));

		contentPanel.setLayout(new BorderLayout(0, 0));
		gamePanel.add(contentPanel, BorderLayout.CENTER);
		
		mainPanel.setPreferredSize(new Dimension(300, 250));
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 18, 0));
		contentPanel.add(mainPanel, BorderLayout.NORTH);
		
		actionsPanel.setLayout(new BorderLayout(0, 0));
		actionsPanel.setPreferredSize(new Dimension(198, 240));
		actionsPanel.setBackground(whiteColor50);
		mainPanel.add(actionsPanel);
		
		JLabel betLabel = new JLabel("BETS");
		betLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		betLabel.setBackground(redColor);
		betLabel.setForeground(whiteColor);
		betLabel.setOpaque(true);
		betLabel.setBorder(border10);
		betLabel.setHorizontalAlignment(SwingConstants.CENTER);
		actionsPanel.add(betLabel, BorderLayout.NORTH);
		
		actionsPanelContent.setOpaque(false);
		actionsPanelContent.setBorder(border10);
		actionsPanel.add(actionsPanelContent, BorderLayout.CENTER);
		actionsPanelContent.setLayout(new GridLayout(0, 1, 10, 10));
		
		infoPanel.setLayout(new BorderLayout(0, 0));
		infoPanel.setPreferredSize(new Dimension(416, 240));
		infoPanel.setBackground(whiteColor50);
		mainPanel.add(infoPanel);
		
		JLabel infoLabel = new JLabel("INFORMATION");
		infoLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		infoLabel.setBackground(redColor);
		infoLabel.setForeground(whiteColor);
		infoLabel.setOpaque(true);
		infoLabel.setBorder(border10);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(infoLabel, BorderLayout.NORTH);
		
		infoPanel.add(infoPanelContent, BorderLayout.CENTER);
		infoPanelContent.setLayout(new BorderLayout(0, 0));
		infoPanelContent.setBorder(border15);
		infoPanelContent.setOpaque(false);
		
		mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		mainLabel.setBackground(whiteColor);
		mainLabel.setText("Click NEW to start a new game");
		
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        mainLabel.setParagraphAttributes(center, false);
		mainLabel.setOpaque(true);
		mainLabel.setBorder(border15);
		
		infoPanelContent.add(mainLabel, BorderLayout.CENTER);
		
		dealerPanel.setLayout(new BorderLayout(0, 0));
		dealerPanel.setPreferredSize(new Dimension(198, 240));
		dealerPanel.setBackground(whiteColor50);
		mainPanel.add(dealerPanel);
		
		JLabel dealerLabel = new JLabel("DEALER");
		dealerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		dealerLabel.setBackground(redColor);
		dealerLabel.setForeground(whiteColor);
		dealerLabel.setOpaque(true);
		dealerLabel.setBorder(border10);
		dealerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dealerPanel.add(dealerLabel, BorderLayout.NORTH);
		
		dealerPanel.add(dealerBottomPanel, BorderLayout.CENTER);
		dealerBottomPanel.setBorder(border10);
		dealerBottomPanel.setOpaque(false);
		dealerBottomPanel.setLayout(new GridLayout(3, 3, 3, 3));
		
		playersPanel.setOpaque(false);
		playersPanel.setBorder(border15);
		playersPanel.setLayout(new GridLayout(1, 0, 20, 0));
		playersPanel.setPreferredSize(new Dimension(300, 240));
		contentPanel.add(playersPanel, BorderLayout.SOUTH);
		
		// player 1
		player1Bet.setBackground(blackColor);
		player1Bet.setForeground(whiteColor);
		player1Bet.setOpaque(true);
		player1Bet.setBorder(border010);
		actionsPanelContent.add(player1Bet);
		
		player1Panel.setBackground(whiteColor50);
		player1Panel.setBorder(border10);
		player1Panel.setLayout(new BorderLayout(0, 0));
		playersPanel.add(player1Panel);
		
		player1TopPanel.setLayout(new BorderLayout(0, 0));
		player1TopPanel.add(player1Name, BorderLayout.WEST);
		player1TopPanel.setBackground(whiteColor);
		player1TopPanel.setOpaque(true);
		player1TopPanel.setBorder(border10);
		player1TopPanel.setPreferredSize(new Dimension(0, 34));
		
		player1Panel.add(player1TopPanel, BorderLayout.NORTH);
		
		player1Name.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		player1Name.setBackground(whiteColor);
		player1Name.setForeground(blackColor);
		player1Name.setOpaque(false);
		player1Name.setHorizontalAlignment(SwingConstants.CENTER);
		
		player1TopPanel.add(player1Money, BorderLayout.EAST);
		
		player1BottomPanel.setBorder(border10);
		player1BottomPanel.setOpaque(false);
		player1Panel.add(player1BottomPanel, BorderLayout.CENTER);
		player1BottomPanel.setLayout(new GridLayout(3, 3, 3, 3));
		
		// player 2
		player2Bet.setBackground(blackColor);
		player2Bet.setForeground(whiteColor);
		player2Bet.setOpaque(true);
		player2Bet.setBorder(border010);
		actionsPanelContent.add(player2Bet);
		
		player2Panel.setBackground(whiteColor50);
		player2Panel.setBorder(border10);
		player2Panel.setLayout(new BorderLayout(0, 0));
		playersPanel.add(player2Panel);
		
		player2TopPanel.setLayout(new BorderLayout(0, 0));
		player2TopPanel.add(player2Name, BorderLayout.WEST);
		player2TopPanel.setBackground(whiteColor);
		player2TopPanel.setOpaque(true);
		player2TopPanel.setBorder(border10);
		player2TopPanel.setPreferredSize(new Dimension(0, 34));
		
		player2Panel.add(player2TopPanel, BorderLayout.NORTH);
		
		player2Name.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		player2Name.setBackground(whiteColor);
		player2Name.setForeground(blackColor);
		player2Name.setOpaque(false);
		player2Name.setHorizontalAlignment(SwingConstants.CENTER);
		
		player2TopPanel.add(player2Money, BorderLayout.EAST);
		FlowLayout player2TopPanelLayout = (FlowLayout) player2BottomPanel.getLayout();
		player2TopPanelLayout.setVgap(10);
		player2TopPanelLayout.setHgap(10);
		
		player2BottomPanel.setBorder(border10);
		player2BottomPanel.setOpaque(false);
		player2Panel.add(player2BottomPanel, BorderLayout.CENTER);
		player2BottomPanel.setLayout(new GridLayout(3, 3, 3, 3));
		
		// player 3
		player3Bet.setBackground(blackColor);
		player3Bet.setForeground(whiteColor);
		player3Bet.setOpaque(true);
		player3Bet.setBorder(border010);
		actionsPanelContent.add(player3Bet);
		
		player3Panel.setBackground(whiteColor50);
		player3Panel.setBorder(border10);
		player3Panel.setLayout(new BorderLayout(0, 0));
		playersPanel.add(player3Panel);
		
		player3TopPanel.setLayout(new BorderLayout(0, 0));
		player3TopPanel.add(player3Name, BorderLayout.WEST);
		player3TopPanel.setBackground(whiteColor);
		player3TopPanel.setOpaque(true);
		player3TopPanel.setBorder(border10);
		player3TopPanel.setPreferredSize(new Dimension(0, 34));
		
		player3Panel.add(player3TopPanel, BorderLayout.NORTH);
		
		player3Name.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		player3Name.setBackground(whiteColor);
		player3Name.setForeground(blackColor);
		player3Name.setOpaque(false);
		player3Name.setHorizontalAlignment(SwingConstants.CENTER);
		
		player3TopPanel.add(player3Money, BorderLayout.EAST);
		FlowLayout player3TopPanelLayout = (FlowLayout) player3BottomPanel.getLayout();
		player3TopPanelLayout.setVgap(10);
		player3TopPanelLayout.setHgap(10);
		
		player3BottomPanel.setBorder(border10);
		player3BottomPanel.setOpaque(false);
		player3Panel.add(player3BottomPanel, BorderLayout.CENTER);
		player3BottomPanel.setLayout(new GridLayout(3, 3, 3, 3));
		
		// player 4
		player4Bet.setBackground(blackColor);
		player4Bet.setForeground(whiteColor);
		player4Bet.setOpaque(true);
		player4Bet.setBorder(border010);
		actionsPanelContent.add(player4Bet);
		
		player4Panel.setBackground(whiteColor50);
		player4Panel.setBorder(border10);
		player4Panel.setLayout(new BorderLayout(0, 0));
		playersPanel.add(player4Panel);
		
		player4TopPanel.setLayout(new BorderLayout(0, 0));
		player4TopPanel.add(player4Name, BorderLayout.WEST);
		player4TopPanel.setBackground(whiteColor);
		player4TopPanel.setOpaque(true);
		player4TopPanel.setBorder(border10);
		player4TopPanel.setPreferredSize(new Dimension(0, 34));
		
		player4Panel.add(player4TopPanel, BorderLayout.NORTH);
		
		player4Name.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		player4Name.setBackground(whiteColor);
		player4Name.setForeground(blackColor);
		player4Name.setOpaque(false);
		player4Name.setHorizontalAlignment(SwingConstants.CENTER);
		
		player4TopPanel.add(player4Money, BorderLayout.EAST);
		FlowLayout player4TopPanelLayout = (FlowLayout) player4BottomPanel.getLayout();
		player4TopPanelLayout.setVgap(10);
		player4TopPanelLayout.setHgap(10);
		
		player4BottomPanel.setBorder(border10);
		player4BottomPanel.setOpaque(false);
		player4Panel.add(player4BottomPanel, BorderLayout.CENTER);
		player4BottomPanel.setLayout(new GridLayout(3, 3, 3, 3));
	}
	
	private void createWelcomePanel()
	{	
		welcomePanel.setOpaque(false);
		selectionPanel.add(welcomePanel, "name_63275562329375");
		welcomePanel.setLayout(new BorderLayout(0, 0));
		
		FlowLayout flowLayout = (FlowLayout) topWelcomePanel.getLayout();
		flowLayout.setHgap(20);
		welcomePanel.add(topWelcomePanel, BorderLayout.NORTH);
		topWelcomePanel.setBorder(new EmptyBorder(20, 0, 20, 0));
		topWelcomePanel.setOpaque(false);
		
		gameWelcomeLabel.setForeground(goldColor);
		gameWelcomeLabel.setFont(new Font("Krungthep", Font.BOLD, 30));
		gameWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameWelcomeLabel.setOpaque(false);
		gameWelcomeLabel.setPreferredSize(new Dimension(200, 40));
		topWelcomePanel.add(gameWelcomeLabel);
		welcomePanel.add(welcomeFormPanel, BorderLayout.SOUTH);
		
		welcomeFormPanel.setPreferredSize(new Dimension(200, 280));
		welcomeFormPanel.setOpaque(false);
		
		//formPanel.setBackground(whiteColor50);
		formPanel.setOpaque(false);
		formPanel.setBorder(border15);
		formPanel.setLayout(new GridLayout(0, 1, 0, 0));
		welcomeFormPanel.add(formPanel);

		formPlayer1Panel.setOpaque(false);
		formPanel.add(formPlayer1Panel);
		
		formPlayer1Label.setForeground(whiteColor);
		formPlayer1Panel.add(formPlayer1Label);
		
		formPlayer1Input.setBorder(border2);
		formPlayer1Input.setPreferredSize(new Dimension(200, 30));
		formPlayer1Panel.add(formPlayer1Input);
		
		formPlayer2Panel.setOpaque(false);
		formPanel.add(formPlayer2Panel);
		
		formPlayer2Label.setForeground(whiteColor);
		formPlayer2Panel.add(formPlayer2Label);
		
		formPlayer2Input.setBorder(border2);
		formPlayer2Input.setPreferredSize(new Dimension(200, 30));
		formPlayer2Panel.add(formPlayer2Input);
		
		formPlayer3Panel.setOpaque(false);
		formPanel.add(formPlayer3Panel);
		
		formPlayer3Label.setForeground(whiteColor);
		formPlayer3Panel.add(formPlayer3Label);
		
		formPlayer3Input.setBorder(border2);
		formPlayer3Input.setPreferredSize(new Dimension(200, 30));
		formPlayer3Panel.add(formPlayer3Input);
		
		formPlayer4Panel.setOpaque(false);
		formPanel.add(formPlayer4Panel);
		
		formPlayer4Label.setForeground(whiteColor);
		formPlayer4Panel.add(formPlayer4Label);
		
		formPlayer4Input.setBorder(border2);
		formPlayer4Input.setPreferredSize(new Dimension(200, 30));
		formPlayer4Panel.add(formPlayer4Input);
		formPanel.add(gameStartBtn);
		formPanel.add(loadWelcomeBtn);
		gameStartBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (formPlayer1Input.getText().trim().isEmpty())
				{
					showWarning("Enter player 1's name");
				}
				else if (formPlayer2Input.getText().trim().isEmpty())
				{
					showWarning("Enter player 2's name");
				}
				else if (formPlayer3Input.getText().trim().isEmpty())
				{
					showWarning("Enter player 3's name");
				}
				else if (formPlayer4Input.getText().trim().isEmpty())
				{
					showWarning("Enter player 4's name");
				}
				else
				{					
					players.add(new Player(formPlayer1Input.getText().trim()));
					players.add(new Player(formPlayer2Input.getText().trim()));
					players.add(new Player(formPlayer3Input.getText().trim()));
					players.add(new Player(formPlayer4Input.getText().trim()));
					
					updateScreenInformation();
					
					welcomePanel.setVisible(false);
					gamePanel.setVisible(true);
				}
			}
		});
	}
	
	private void updateScreenInformation()
	{
		Player p1 = players.get(0);
		Player p2 = players.get(1);
		Player p3 = players.get(2);
		Player p4 = players.get(3);
		
		player1Name.setText("#1 " + p1.getPlayerName());
		player2Name.setText("#2 " + p2.getPlayerName());
		player3Name.setText("#3 " + p3.getPlayerName());
		player4Name.setText("#4 " + p4.getPlayerName());
		
		player1Bet.setText("#1 " + (p1.getCurrentBet() > 0 ? currency(p1.getCurrentBet()) : "?"));
		player2Bet.setText("#2 " + (p2.getCurrentBet() > 0 ? currency(p2.getCurrentBet()) : "?"));
		player3Bet.setText("#3 " + (p3.getCurrentBet() > 0 ? currency(p3.getCurrentBet()) : "?"));
		player4Bet.setText("#4 " + (p4.getCurrentBet() > 0 ? currency(p4.getCurrentBet()) : "?"));
		
		player1Money.setText(currency(p1.getPlayerMoney()));
		player2Money.setText(currency(p2.getPlayerMoney()));
		player3Money.setText(currency(p3.getPlayerMoney()));
		player4Money.setText(currency(p4.getPlayerMoney()));
		
		// cards?
		
		player1BottomPanel.removeAll();
		player1BottomPanel.repaint();
		if (p1.handCards().size() > 0)
		{
			for(Card c: p1.handCards())
			{
	            player1BottomPanel.add(c.panel());
	        }
		}
		
		player2BottomPanel.removeAll();
		player2BottomPanel.repaint();
		if (p2.handCards().size() > 0)
		{
			for(Card c: p2.handCards())
			{
				player2BottomPanel.add(c.panel());
	        }
		}
		
		player3BottomPanel.removeAll();
		player3BottomPanel.repaint();
		if (p3.handCards().size() > 0)
		{
			for(Card c: p3.handCards())
			{
				player3BottomPanel.add(c.panel());
	        }
		}
		
		player4BottomPanel.removeAll();
		player4BottomPanel.repaint();
		if (p4.handCards().size() > 0)
		{
			for(Card c: p4.handCards())
			{
				player4BottomPanel.add(c.panel());
	        }
		}
		
		dealerBottomPanel.removeAll();
		dealerBottomPanel.repaint();
		if (dealer.handCards().size() > 0)
		{
			for(Card c: dealer.handCards())
			{
				dealerBottomPanel.add(c.panel());
	        }
		}
		
		System.out.println(players);
		System.out.println(dealer);
	}
	
	private String userInput(String message, String title)
	{
		return (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, question, null, "");
	}
	
	private void showWarning(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Oops", JOptionPane.INFORMATION_MESSAGE, warning);
	}
	
	private String currency(int amount)
	{
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMaximumFractionDigits(0);
        return currencyFormat.format(amount);
	}
	
	private void gamePlayTurnAction(String action)
	{
		if (action.equals("hit"))
		{
			Player p = players.get(playTurn - 1);
			Card c = shoe.buyCard(false);
			p.handAddCard(c);
			if (p.handTotal() >= 21)
			{
				playTurn++;
			}
			updateScreenInformation();
		}
		else if (action.equals("stand"))
		{
			playTurn++;
		}
		
		gamePlayTurn(false);
	}
	
	private void gamePlayTurn(boolean first)
	{
		player1TopPanel.setBackground(whiteColor);
		player2TopPanel.setBackground(whiteColor);
		player3TopPanel.setBackground(whiteColor);
		player4TopPanel.setBackground(whiteColor);
		
		int counter = 0;
		for (Player p: players)
    	{
			counter++;
			if (counter == playTurn)
			{
				if (p.handTotal() == 21 && first)
				{
					playTurn++;
					p.setBlackJack();
				}
				else if (dealer.isBlackJack() || p.handTotal() >= 21)
				{
					playTurn++;
				}
				else
				{
					if (counter == 1)
					{
						player1TopPanel.setBackground(goldColor);
					}
					else if (counter == 2)
					{
						player2TopPanel.setBackground(goldColor);
					}
					else if (counter == 3)
					{
						player3TopPanel.setBackground(goldColor);
					}
					else if (counter == 4)
					{
						player4TopPanel.setBackground(goldColor);
					}
					mainLabel.setText(p.getPlayerName() + "'s turn\nClick HIT or STAND to play!");
				}
				/*
				if (!dealer.isBlackJack() && p.handTotal() < 21)
				{
					do
					{
						keepAsking = true;
	    				System.out.println("\n"+p);
	        			System.out.print("\n("+p.getPlayerName()+") Enter S for stand or H for Hit: ");
	        			
	                	//temp = scnr.nextLine();
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
				/* */
			}
    	}
		
		if (playTurn == 0)
		{
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
	}
	
	private void gamePlayCards()
	{    	
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
    	
    	updateScreenInformation();
    	
    	playTurn = 1;
    	
    	gamePlayTurn(true);
	}
	
	private void gameBets()
	{
		// ask for bets
		for (Player p: players)
    	{
			do
			{
				keepAsking = true;
				try 
				{
					mainLabel.setText("Waiting for " + p.getPlayerName() + "'s bet");
					temp = userInput("Current Money: " + p.getPlayerMoney(), p.getPlayerName() + "'s bet");
		            int bet = Integer.parseInt(temp);
		            if (bet >= 5)
					{
						if (bet <= p.getPlayerMoney())
						{
							p.setCurrentBet(bet);
							p.removeMoney(bet);
							keepAsking = false;
							
							updateScreenInformation();
						}
						else
						{
							showWarning("YOU HAVE ONLY '" + p.getPlayerMoney() + "', SO YOU CAN ONLY BET UP TO '" + p.getPlayerMoney() + "'.");
						}
					}
					else
					{
						showWarning("Your bet must be at least 5");
					}
		        } 
				catch (NumberFormatException e) 
				{
		        	showWarning("Please enter a valid integer");
		        }
			}
			while(keepAsking);
    	}
	}
}
