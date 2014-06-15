package Client;
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

import recommender.RecommendationControl;

class ClientBoundary {

	private GUI gui;
	private RecommendationControl recomControl;

	public ClientBoundary(){
		recomControl = new RecommendationControl();
		gui = new GUI(recomControl);
	}

	/**
	 * Main method used to run the client.
	 */
	public static void main(String args[]) throws Exception {
		ClientBoundary clientBoundary = new ClientBoundary();
	}
}

/**
 * Gui class used for spectator view.
 */
class GUI extends JFrame implements KeyListener { // KeyListener to allow for keyboard shortcuts... not yet working

	private static final long serialVersionUID = 7237211201250882835L;
	private TitlePanel titlePanel;
	Font font = new Font("Times New Roman", Font.BOLD, 16);
	
	private String attractionName;
	
	private RecommendationControl recomControl;

	/**
	 * Constructor for GUI objects.
	 */
	public GUI(RecommendationControl recomControl) {
		
		this.recomControl = recomControl;
		attractionName = new String();
		
		// Initial (Skipped)
		setTitle("TRAVELABULOUS");
		setSize(562,700);

		titlePanel = new TitlePanel();
		titlePanel.setPreferredSize(new Dimension(563, 275));
		
		setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);

		// Hot or Not (Skipped)
		setTitle("TRAVELABULOUS Hot-Or-Not");
		HotOrNotPanel hotOrNotPanel = new HotOrNotPanel(recomControl, attractionName);
		this.getContentPane().add(titlePanel, BorderLayout.NORTH);
		this.getContentPane().add(hotOrNotPanel, BorderLayout.CENTER);
		revalidate();
		
		while(attractionName != null){ // Condition is never failing even though attractionName is being set to false...
			repaint();
		}
		
		this.getContentPane().remove(hotOrNotPanel);

		// Head to Head
		setTitle("TRAVELABULOUS Head-To-Head");
		
		this.getContentPane().add(titlePanel, BorderLayout.NORTH);
		this.getContentPane().add(new HeadToHeadPanel(), BorderLayout.CENTER);		
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
}

class HotOrNotPanel extends JPanel implements KeyListener, ActionListener { // KeyListener to allow for keyboard shortcuts... not yet working

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
		
		addKeyListener(this); 

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
		attractionPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), "Attraction Picture"));
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
	public void actionPerformed(ActionEvent ae) {		
		attractionName = recomControl.askHotOrNot();
		updatePicture(attractionName);
		//System.out.println(attractionName); // DEBUGGING
	}
}

class HeadToHeadPanel extends JPanel implements KeyListener { // KeyListener to allow for keyboard shortcuts... not yet working

	static final long serialVersionUID = -2813693825735504436L;
	private JButton left, right, both;
	private JPanel attractionPanel;
	Font font = new Font("Times New Roman", Font.BOLD, 16);

	/**
	 * Constructor for the HeadToHead panel.
	 */
	public HeadToHeadPanel(){		

		addKeyListener(this);

		left = new JButton("Left");
		left.setPreferredSize(new Dimension(250, 50));
		left.setBackground(Color.WHITE);
		left.setFont(font);

		right = new JButton("Right");
		right.setPreferredSize(new Dimension(250, 50));
		right.setBackground(Color.WHITE);
		right.setFont(font);
		
		both = new JButton("BOTH!");
		both.setPreferredSize(new Dimension(250, 50));
		both.setBackground(Color.WHITE);
		both.setFont(font);

		attractionPanel = new JPanel();
		attractionPanel.setPreferredSize(new Dimension(560, 275));
		attractionPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), "Attraction Picture"));
		attractionPanel.add(new JLabel("Left Picture", SwingConstants.LEFT), BorderLayout.WEST);
		attractionPanel.add(new JLabel("Right Picture", SwingConstants.RIGHT), BorderLayout.EAST);

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
}

@SuppressWarnings("serial")
class TitlePanel extends JPanel {

	private BufferedImage image;	   

	public TitlePanel() {
		try {                
			image = ImageIO.read(new File("Logo.jpg"));
			setFocusable(true);
			requestFocus();

			InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
			im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT");

			ActionMap am = getActionMap();
			am.put("LEFT", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
				}
			}
					);

		} catch (IOException ex) {
			System.out.println("Couldnt read file");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
}
