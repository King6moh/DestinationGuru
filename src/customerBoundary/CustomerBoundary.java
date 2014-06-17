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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import dataManagement.Attraction;
import recommender.RecommendationControl;

public class CustomerBoundary {

	private GUI gui;
	private RecommendationControl recomControl;

	public CustomerBoundary(){
		recomControl = new RecommendationControl(this);
		gui = new GUI(recomControl);
	}

	public boolean askHotOrNot(String tag) {

		return true;

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
	Font font = new Font("Times New Roman", Font.BOLD, 16);
	private boolean ready;

	private String attractionName;

	private RecommendationControl recomControl;

	/**
	 * Constructor for GUI objects.
	 */
	public GUI(RecommendationControl recomControl) {

		this.recomControl = recomControl;
		attractionName = new String("attractionName");
		ready = false;

		// Initial
		setTitle("TRAVELABULOUS - Destination Guru");
		setSize(562,700);

		titlePanel = new TitlePanel();
		titlePanel.setPreferredSize(new Dimension(563, 175));

		DestinationGuruPanel dgPanel = new DestinationGuruPanel();
		dgPanel.setPreferredSize(new Dimension(563, 150));

		JPanel mainPanel = new JPanel();
		JButton continueButton = new JButton("Get Recommendations!");
		continueButton.addActionListener(this);
		mainPanel.add(continueButton);

		this.getContentPane().add(dgPanel, BorderLayout.NORTH);
		this.getContentPane().add(titlePanel, BorderLayout.SOUTH);
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);

		setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

		while(!ready){ // wait for button click
			repaint();
		} 

		this.getContentPane().remove(mainPanel);
		this.getContentPane().remove(titlePanel);

		// Hot or Not
		setTitle("TRAVELABULOUS Hot-Or-Not");
		HotOrNotPanel hotOrNotPanel = new HotOrNotPanel(recomControl, attractionName);
		//this.getContentPane().add(titlePanel, BorderLayout.NORTH);
		this.getContentPane().add(dgPanel, BorderLayout.NORTH);
		this.getContentPane().add(hotOrNotPanel, BorderLayout.CENTER);
		revalidate();

		while(!hotOrNotPanel.getAttractionName().equals("DONE")){ 
			repaint();
		}

		this.getContentPane().remove(hotOrNotPanel);

		// Head to Head
		setTitle("TRAVELABULOUS Head-To-Head");

		HeadToHeadPanel headToHeadPanel = new HeadToHeadPanel(recomControl);
		//this.getContentPane().add(titlePanel, BorderLayout.NORTH);
		this.getContentPane().add(dgPanel, BorderLayout.NORTH);
		this.getContentPane().add(headToHeadPanel, BorderLayout.CENTER);	
		revalidate();

		while(headToHeadPanel.getAttraction()[0] != (null)){ 
			repaint();
		}

		this.getContentPane().remove(headToHeadPanel);

		// Head to Head
		setTitle("TRAVELABULOUS Recommendations");

		JPanel recommendationsPanel = new JPanel();
		JLabel recommendationsLabel = new JLabel("Recommendations:");
		recommendationsPanel.add(recommendationsLabel);

		ArrayList<Attraction> finalRecoms = recomControl.updateRecomEntity();
		for (Attraction att: finalRecoms){
			recommendationsPanel.add(new JLabel("<html><br>--" + att.getName() + "</html>"));
		}

		this.getContentPane().add(dgPanel, BorderLayout.NORTH);
		this.getContentPane().add(recommendationsPanel, BorderLayout.CENTER);

		revalidate();
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
		ready = true;
	}
}

class HotOrNotPanel extends JPanel implements ActionListener { 

	private static final long serialVersionUID = 7460790207139818093L;
	private JButton hot, not;
	private JPanel attractionPanel;
	private String attractionName;
	private RecommendationControl recomControl;
	Font font = new Font("Times New Roman", Font.BOLD, 16);

	/**
	 * Constructor for the HotOrNot panel.
	 */
	public HotOrNotPanel(RecommendationControl recomControl, String attractionName){		
		this.attractionName = attractionName;
		this.recomControl = recomControl;

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
		attractionPanel.removeAll();
		attractionPanel.add(new SingleGraphicsPanel(name, 560, 300));
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {		
		attractionName = recomControl.askHotOrNot();
		updatePicture(attractionName);
		if(ae != null){
			if (ae.getActionCommand() == "HOT!"){
				recomControl.hot(attractionName);
			} // otherwise ae.getActionCommand() == "not" --> discard tag
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
	private Attraction[] attractions;
	private String[] attractionNames;
	private RecommendationControl recomControl;
	Font font = new Font("Times New Roman", Font.BOLD, 16);

	/**
	 * Constructor for the HeadToHead panel.
	 */
	public HeadToHeadPanel(RecommendationControl recomControl){	
		this.recomControl = recomControl;
		attractions = new Attraction[2];
		attractions[0] = new Attraction();
		attractions[1] = new Attraction();
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
		//attractionPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), "Attraction Picture"));

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

	public void updatePictures(Attraction[] name){
		attractionPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), recomControl.getCurrentTag()));

		if (name[0] != null){
			leftLabel.setText(name[0].getName());
		} else if (name[0] == null){
			leftLabel.setText("NO picture found");
		}

		if (name[1] != null){
			rightLabel.setText(name[1].getName());
		} else if (name[1] == null){
			rightLabel.setText("No picture found");
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {		
		attractions = recomControl.askHeadToHead();
		//System.out.println(attractions[0] + "\t" + attractions[1]);
		updatePictures(attractions);

		if(ae != null){
			if (ae.getActionCommand() == "Left"){
				recomControl.headToHead(attractions[0], null);
				// save left tag(s)
			} else if (ae.getActionCommand() == "Right"){
				recomControl.headToHead(null, attractions[1]);
				// save right tag(s)
			} else if (ae.getActionCommand() == "Both"){
				// save both tags
				recomControl.headToHead(attractions[0], attractions[1]);
			} else {
				// Error!
			}
		}
	}

	public String[] getAttractionNames(){
		return attractionNames;
	}

	public Attraction[] getAttraction() {
		return attractions;
	}
}

class SingleGraphicsPanel extends JPanel {
	private BufferedImage image;

	public SingleGraphicsPanel(String fname, int width, int height){
		try {                
			image = ImageIO.read(new File(fname + ".jpg"));
		} catch (IOException ex) {
			System.out.println("Couldnt load " + fname + ".jpg file");
		}
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int x = (this.getWidth() - image.getWidth(null)) / 2;
		int y = (this.getHeight() - image.getHeight(null)) / 2;
		g2d.drawImage(image, x, y, null);            
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