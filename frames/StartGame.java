package frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;

import extras.GameManager;
import extras.InitMap;
import extras.Player;
import extras.ShipSize;
import listeners.MapListener;
import listeners.ShipButtonsListener;
import listeners.StartGameListener;
import listeners.StartGameMapListener;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class StartGame extends JFrame {

	private JPanel mainPanel;
	private JButton startGameButton;
	private JButton fourMastedButton;
	private JButton twoMastedButton;
	private JButton oneMastedButton;
	private JButton threeMastedButton;
	private JLabel[][] mapLabels;
	private MapListener mapListener;
	private ShipButtonsListener shipButtonsListener;
	private Player player;
	
	private GameManager gameManager;

	public StartGame(Player player) {
		
		
		
		
		mapListener = new StartGameMapListener(this);
		shipButtonsListener = new ShipButtonsListener((StartGameMapListener) mapListener);
		mapLabels = new JLabel[mapListener.getMapSize()][mapListener.getMapSize()];
		this.player = player;
		
		initComponents();

	}

	private void initComponents(){
		
		setTitle("Statki");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 420);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Gra");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Poddaj");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Zako\u0144cz");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnOProgramie = new JMenu("Pomoc");
		menuBar.add(mnOProgramie);
		
		JMenuItem mntmPomoc = new JMenuItem("Zasady");
		mnOProgramie.add(mntmPomoc);
		
		JMenuItem mntmPomoc_1 = new JMenuItem("Pomoc");
		mnOProgramie.add(mntmPomoc_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("O programie");
		mnOProgramie.add(mntmNewMenuItem_1);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel leftPanel = new JPanel();
		mainPanel.add(leftPanel);
		leftPanel.setLayout(null);
		
		JLabel gridLabel = new JLabel("");
		gridLabel.setVerticalAlignment(SwingConstants.TOP);
		gridLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gridLabel.setIgnoreRepaint(true);
		gridLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gridLabel.setIcon(new ImageIcon(StartGame.class.getResource("/images/grid.png")));
		gridLabel.setBounds(0, 0, 372, 344);
		leftPanel.add(gridLabel);
	
		InitMap initMap = mapListener.generateMap(30, 1, new Point(1, 1));
		for (JLabel label : initMap.getLabels())
			leftPanel.add(label);
		
		for (JLabel[] label : initMap.getMap())
			for (JLabel lbl : label)
				leftPanel.add(lbl);
		
		this.mapLabels = initMap.getMap();
		
		JPanel rightPanel = new JPanel();
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel topShipsPanel = new JPanel();
		rightPanel.add(topShipsPanel);
		topShipsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		topShipsPanel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblXmastowce = new JLabel("2 x 4-mastowiec");
		lblXmastowce.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblXmastowce, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		panel_5.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		
		fourMastedButton = new JButton("");
		fourMastedButton.putClientProperty("SIZE", ShipSize.FOUR);
		fourMastedButton.addActionListener(shipButtonsListener);
		fourMastedButton.setIcon(new ImageIcon(StartGame.class.getResource("/images/4.png")));
		panel_10.add(fourMastedButton);
		
		JPanel panel_6 = new JPanel();
		topShipsPanel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("3 x 3-mastowiec");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JPanel panel_11 = new JPanel();
		panel_6.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		
		threeMastedButton = new JButton("");
		threeMastedButton.putClientProperty("SIZE", ShipSize.THREE);
		threeMastedButton.addActionListener(shipButtonsListener);
		threeMastedButton.setIcon(new ImageIcon(StartGame.class.getResource("/images/3.png")));
		panel_11.add(threeMastedButton);
		
		JPanel bottomShipsPanel = new JPanel();
		rightPanel.add(bottomShipsPanel);
		bottomShipsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_7 = new JPanel();
		bottomShipsPanel.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("4 x 2-masztowiec");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_13 = new JPanel();
		panel_7.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		
		twoMastedButton = new JButton("");
		twoMastedButton.putClientProperty("SIZE", ShipSize.TWO);
		twoMastedButton.addActionListener(shipButtonsListener);
		twoMastedButton.setIcon(new ImageIcon(StartGame.class.getResource("/images/2.png")));
		panel_13.add(twoMastedButton);
		
		JPanel panel_8 = new JPanel();
		bottomShipsPanel.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("5 x 1-masztowiec");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblNewLabel_4, BorderLayout.NORTH);
		
		JPanel panel_12 = new JPanel();
		panel_8.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
		
		oneMastedButton = new JButton("");
		oneMastedButton.putClientProperty("SIZE", ShipSize.ONE);
		oneMastedButton.addActionListener(shipButtonsListener);
		oneMastedButton.setIcon(new ImageIcon(StartGame.class.getResource("/images/1.png")));
		panel_12.add(oneMastedButton);
		
		JPanel infoPanel = new JPanel();
		rightPanel.add(infoPanel);
		infoPanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel infoLabel = new JLabel("Naci\u015Bnij prawy przycisk myszy by odwr\u00F3ci\u0107 statek.");
		infoLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(infoLabel);
		
		JPanel emptyPanel = new JPanel();
		infoPanel.add(emptyPanel);
		
		startGameButton = new JButton("ROZPOCZNIJ");
		startGameButton.setEnabled(false);
		startGameButton.addActionListener(new StartGameListener(this, player));
		infoPanel.add(startGameButton);
	}

	public JButton getStartGameButton() {
		return startGameButton;
	}

	public JButton getFourMastedButton() {
		return fourMastedButton;
	}

	public JButton getTwoMastedButton() {
		return twoMastedButton;
	}

	public JButton getOneMastedButton() {
		return oneMastedButton;
	}

	public JButton getThreeMastedButton() {
		return threeMastedButton;
	}
	
	public ShipButtonsListener getShipButtonsListener() {
		return shipButtonsListener;
	}

	
	public JLabel[][] getMapLabels() {
		return mapLabels;
	}
	
	public JLabel getLabel(Point point){
		return mapLabels[point.x][point.y];
	}

	public MapListener getMapListener() {
		return mapListener;
	}
	
	
}
