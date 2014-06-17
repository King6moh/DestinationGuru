package customerBoundary;
/**
 * Class Client
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import dataManagement.Attraction;
import recommender.RecommendationControl;

import ErrorLog.ErrorLog;

public class CustomerBoundary extends ErrorLog {

	private GUI gui;
	private RecommendationControl recomControl;

	public CustomerBoundary(){
		super();
		recomControl = new RecommendationControl(this);
		gui = new GUI(recomControl);
		gui.gui();
		
		logger.info("adding to error log");
	}

	public boolean askHotOrNot(String tag) {
		boolean answer = gui.HotOrNot(tag);
		 return answer;

	} //end askHotOrNot()

	public int askHeadToHead(Attraction attraction_1, Attraction attraction_2) {

		return 0;

	} //end askHeadToHead()

	/**
	 * Main method used to run the client.
	 */
	public static void main(String args[]) throws Exception {
		CustomerBoundary customerBoundary = new CustomerBoundary();
	}
}

/**
 * Gui class used for spectator view.
 */
class GUI extends JFrame implements KeyListener, ActionListener { // KeyListener to allow for keyboard shortcuts... not yet working

	private static final long serialVersionUID = 7237211201250882835L;
	private TitlePanel titlePanel;
	private HotOrNotPanel hotOrNotPanel;
	DestinationGuruPanel dgPanel = new DestinationGuruPanel();
	JPanel mainPanel = new JPanel();
	
	Font font = new Font("Times New Roman", Font.BOLD, 16);
	private boolean ready;
	private int state;
	private boolean hotAnswer;

	private String attractionName;

	private RecommendationControl recomControl;

	/**
	 * Constructor for GUI objects.
	 */
	public GUI(RecommendationControl recomControl) {

		this.recomControl = recomControl;
		attractionName = new String("attractionName");
		ready = false;
		state = 0;
		hotAnswer = false;
		dgPanel = new DestinationGuruPanel();
		mainPanel = new JPanel();
		
	}
	
	public void gui() {
		
		// Initial
				setTitle("TRAVELABULOUS");
				setSize(562,700);

				titlePanel = new TitlePanel();
				titlePanel.setPreferredSize(new Dimension(563, 275));

				//dgPanel = new DestinationGuruPanel();
				dgPanel.setPreferredSize(new Dimension(563, 150));

				//mainPanel = new JPanel();
				JButton continueButton = new JButton("Get Recommendations!");
				continueButton.addActionListener(this);
				mainPanel.add(continueButton);

				this.getContentPane().add(titlePanel, BorderLayout.NORTH);
				this.getContentPane().add(mainPanel, BorderLayout.CENTER);
				this.getContentPane().add(dgPanel, BorderLayout.SOUTH);

				setLocationRelativeTo(null);

				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setResizable(false);
				this.setVisible(true);
				
				while(state == 0){ // wait for button click
					this.repaint();
				} 
				
				while(true);

				//this.getContentPane().remove(mainPanel);
				//this.getContentPane().remove(dgPanel);

				/*
				// Hot or Not
				setTitle("TRAVELABULOUS Hot-Or-Not");
				HotOrNotPanel hotOrNotPanel = new HotOrNotPanel(recomControl, attractionName);
				this.getContentPane().add(titlePanel, BorderLayout.NORTH);
				this.getContentPane().add(hotOrNotPanel, BorderLayout.CENTER);
				revalidate();
				*/

				/*
				while(!hotOrNotPanel.getAttractionName().equals("DONE")){ 
					repaint();
				}

				this.getContentPane().remove(hotOrNotPanel);

				// Head to Head
				setTitle("TRAVELABULOUS Head-To-Head");

				HeadToHeadPanel headToHeadPanel = new HeadToHeadPanel(recomControl);
				this.getContentPane().add(titlePanel, BorderLayout.NORTH);
				this.getContentPane().add(headToHeadPanel, BorderLayout.CENTER);	
				revalidate();

				while(!headToHeadPanel.getAttractionNames()[0].equals("DONE")){ 
					repaint();
				}

				this.getContentPane().remove(headToHeadPanel);

				// Head to Head
				setTitle("TRAVELABULOUS Recommendations");

				JPanel recommendationsPanel = new JPanel();
				JLabel recommendationsLabel = new JLabel("Recommendations:");
				recommendationsPanel.add(recommendationsLabel);

				this.getContentPane().add(recommendationsPanel, BorderLayout.CENTER);
				this.getContentPane().add(dgPanel, BorderLayout.SOUTH);

				revalidate();
				*/
		
	}
	
	public boolean HotOrNot(String tag) {
		
		if (state == 0) {
			this.getContentPane().remove(mainPanel);
			this.getContentPane().remove(dgPanel);
			System.out.println("Remove");
		}
		
		state = 1;
		setTitle("TRAVELABULOUS Hot-Or-Not");
		hotOrNotPanel = new HotOrNotPanel(recomControl, tag, this);
		this.getContentPane().add(titlePanel, BorderLayout.NORTH);
		this.getContentPane().add(hotOrNotPanel, BorderLayout.CENTER);
		this.revalidate();
		
		System.out.println("Hot Or Not");
		
		while(!ready) {
			this.repaint();
			System.out.println("repaint");
		}
		
		return hotAnswer;
		
	}
	
	public void hot() {
		hotAnswer = true;
	}
	
	public void not() {
		hotAnswer = false;
	}
	
	public void ready() {
		ready = true;
	}

	/** Handle the key typed event from the text field. */
	public void keyTyped(KeyEvent e) { 
	}

	/** Handle the key-pressed event from the text field. */
	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		try{
			switch( keyCode ) { 
			case KeyEvent.VK_LEFT: // Yes or left attraction
				System.out.println("Left");
				break;
			case KeyEvent.VK_RIGHT: // No or right attraction
				System.out.println("Right");
				break;
			} 
		} catch(Exception exception){}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//ready = true;
		recomControl.getRecommendation();
	}
}

class HotOrNotPanel extends JPanel implements ActionListener { 

	private static final long serialVersionUID = 7460790207139818093L;
	private JButton hot, not;
	private JPanel attractionPanel;
	private String attractionName;
	private RecommendationControl recomControl;
	private GUI gui;
	Font font = new Font("Times New Roman", Font.BOLD, 16);

	/**
	 * Constructor for the HotOrNot panel.
	 */
	public HotOrNotPanel(RecommendationControl recomControl, String attractionName, GUI gui){		
		this.attractionName = attractionName;
		this.recomControl = recomControl;
		this.gui = gui;

		hot = new JButton("HOT!");
		hot.setPreferredSize(new Dimension(250, 50));
		hot.setBackground(Color.GREEN);
		hot.setFont(font);
		hot.addActionListener(this);

		not = new JButton("Not");
		not.setPreferredSize(new Dimension(250, 50));
		not.setBackground(Color.RED);
		not.setFont(font);
		not.addActionListener(this);

		attractionPanel = new JPanel();
		attractionPanel.setPreferredSize(new Dimension(560, 300));
		//attractionPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), "Attraction Picture"));
		actionPerformed(null);
		//attractionPanel.add(new JLabel("Picture"));

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(hot, BorderLayout.WEST);
		buttonPanel.add(not, BorderLayout.EAST);

		this.add(attractionPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	public void updatePicture(String name){
		attractionPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), name));
	}

	@Override
	public void actionPerformed(ActionEvent ae) {		
		updatePicture(attractionName);
		if(ae != null){
			if (ae.getActionCommand() == "HOT!"){
				gui.hot();
				// save tag
			} // otherwise ae.getActionCommand() == "not" --> discard tag
			else {
				gui.not();
			}
			gui.ready();
		}
	}

	public String getAttractionName(){
		return attractionName;
	}
}

class HeadToHeadPanel extends JPanel implements ActionListener {

	static final long serialVersionUID = -2813693825735504436L;
	private JButton left, right, both;
	private JPanel attractionPanel;
	private JLabel leftLabel, rightLabel;
	private String[] attractionNames;
	private RecommendationControl recomControl;
	Font font = new Font("Times New Roman", Font.BOLD, 16);

	/**
	 * Constructor for the HeadToHead panel.
	 */
	public HeadToHeadPanel(RecommendationControl recomControl){	
		this.recomControl = recomControl;
		attractionNames = new String[2];
		attractionNames[0] = new String();
		attractionNames[1] = new String();

		left = new JButton("Left");
		left.setPreferredSize(new Dimension(250, 50));
		left.setBackground(Color.WHITE);
		left.setFont(font);
		left.addActionListener(this);

		right = new JButton("Right");
		right.setPreferredSize(new Dimension(250, 50));
		right.setBackground(Color.WHITE);
		right.setFont(font);
		right.addActionListener(this);

		both = new JButton("BOTH!");
		both.setPreferredSize(new Dimension(250, 50));
		both.setBackground(Color.WHITE);
		both.setFont(font);
		both.addActionListener(this);

		attractionPanel = new JPanel();
		attractionPanel.setPreferredSize(new Dimension(560, 275));
		attractionPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), "Attraction Picture"));

		leftLabel = new JLabel();
		rightLabel = new JLabel();
		actionPerformed(null);
		//leftLabel = new JLabel("Left Picture", SwingConstants.LEFT);
		//rightLabel = new JLabel("Right Picture", SwingConstants.RIGHT);
		attractionPanel.add(leftLabel, BorderLayout.WEST);
		attractionPanel.add(rightLabel, BorderLayout.EAST);

		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints(); 

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.5;
		constraints.gridx = 0;
		constraints.gridy = 0;
		buttonPanel.add(left, constraints);

		constraints.gridx = 1;
		constraints.gridy = 0;
		buttonPanel.add(right, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(10,0,0,0);  //top padding;
		constraints.gridwidth = 2;
		buttonPanel.add(both, constraints);

		this.add(attractionPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	public void updatePictures(String[] name){
		leftLabel.setText(name[0]);
		rightLabel.setText(name[1]);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {		
		attractionNames = recomControl.askHeadToHead();
		updatePictures(attractionNames);

		if(ae != null){
			if (ae.getActionCommand() == "Left"){
				// save left tag(s)
			} else if (ae.getActionCommand() == "Right"){
				// save right tag(s)
			} else if (ae.getActionCommand() == "Both"){
				// save both tags
			} else {
				// Error!
			}
		}
	}

	public String[] getAttractionNames(){
		return attractionNames;
	}
}

@SuppressWarnings("serial")
class TitlePanel extends JPanel {

	private BufferedImage image;	   

	public TitlePanel() {
		try {                
			image = ImageIO.read(new File("Logo.jpg"));
		} catch (IOException ex) {
			System.out.println("Couldnt load Travelabulous logo.jpg file");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
}

class DestinationGuruPanel extends JPanel {

	private BufferedImage image;	   

	public DestinationGuruPanel() {
		try {                
			image = ImageIO.read(new File("DestinationGuru.jpg"));
		} catch (IOException ex) {
			System.out.println("Couldnt load DestinationGuru.jpg file");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
}
