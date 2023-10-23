import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The class is meant to support visualizing image-processing algorithms,
 * RGB channel extraction in this case
 * Java Swing is used to implement the GUI of the application
 */

@SuppressWarnings("serial")
public class GraphicsApp extends JFrame implements ActionListener{
	private BufferedImage sourceImage, resultImage;
	private JButton getRed, getGreen, getBlue;
	private JLabel sourceImageLabel, resultImageLabel;
	private JPanel sourcePanel, middlePanel, resultPanel, rgbPanel;
	double imageScale;

	/**
	 * @param fileName name of the image file to process
	 * loads the image with the filename provided
	 */
	public GraphicsApp(String fileName) {
		try {
			sourceImage = ImageIO.read(new File(fileName));
			resultImage = ImageIO.read(new File(fileName)); 
		} catch (IOException e) { }
		initUI();
	}

	private void initUI() {
		final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		imageScale = 0.35 * SCREEN_WIDTH / sourceImage.getWidth();
		if (0.75 * SCREEN_HEIGHT / sourceImage.getHeight() < imageScale) 
			imageScale = 0.33 * SCREEN_HEIGHT / sourceImage.getHeight();
		
		sourceImageLabel = new JLabel(new ImageIcon(sourceImage.getScaledInstance((int)(sourceImage.getWidth() * imageScale), 
				(int)(sourceImage.getHeight() * imageScale), Image.SCALE_SMOOTH)));
		resultImageLabel = new JLabel(new ImageIcon(resultImage.getScaledInstance((int)(resultImage.getWidth() * imageScale), 
				(int)(resultImage.getHeight() * imageScale), Image.SCALE_SMOOTH)));
		

		getRed = new JButton("getRed");
		getRed.setPreferredSize(new Dimension(86,24));
		getRed.addActionListener(this);
		getGreen = new JButton("getGreen");
		getGreen.setPreferredSize(new Dimension(86,24));
		getGreen.addActionListener(this);
		getBlue = new JButton("getBlue");
		getBlue.setPreferredSize(new Dimension(86,24));
		getBlue.addActionListener(this);

		getContentPane().setLayout(new BorderLayout());
		sourcePanel = new JPanel();
		middlePanel = new JPanel();
		resultPanel = new JPanel();       
		sourcePanel.setLayout(new BoxLayout(sourcePanel, BoxLayout.Y_AXIS));
		sourcePanel.add (new JLabel("Source"));
		sourcePanel.add (sourceImageLabel);
		add(sourcePanel,BorderLayout.WEST);

		middlePanel.setLayout(new BorderLayout(10,10));

		rgbPanel = new JPanel(new GridBagLayout());
		rgbPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		rgbPanel.add(getRed, c);
		c.gridy = 1;
		rgbPanel.add(getGreen, c);
		c.gridy = 2;
		rgbPanel.add(getBlue, c);
		middlePanel.add(new JLabel(" "), BorderLayout.NORTH);
		middlePanel.add(rgbPanel, BorderLayout.SOUTH);
		add(middlePanel,BorderLayout.CENTER);

		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
		resultPanel.add (new JLabel("Result"));
		resultPanel.add (resultImageLabel);
		add(resultPanel,BorderLayout.EAST);

		pack();
		setTitle("ImageFilter");
		setLocationRelativeTo(null); //place in the centre of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		String fileName;// = args[0];
		//fileName = "flower.png";
		fileName = "tv-pattern.png";
		GraphicsApp app = new GraphicsApp(fileName);
		//GraphicsApp app = new GraphicsApp(args[0]);

		app.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getRed){
			int []rgbData = sourceImage.getRGB(0, 0, sourceImage.getWidth(), sourceImage.getHeight(), null, 0, sourceImage.getWidth());
			//System.out.println(Integer.toHexString(rgbData[1]));
			//System.out.println(sourceImage.getWidth());
			rgbData = ImageFilter.getRed(rgbData, sourceImage.getWidth());
			resultImage.setRGB(0, 0, sourceImage.getWidth(), sourceImage.getHeight(), rgbData, 0, sourceImage.getWidth());
			resultImageLabel.setIcon(new ImageIcon(resultImage.getScaledInstance((int)(resultImage.getWidth() * imageScale), 
					(int)(resultImage.getHeight() * imageScale), Image.SCALE_SMOOTH)));
		}
		else if (e.getSource() == getGreen){
				int []rgbData = sourceImage.getRGB(0, 0, sourceImage.getWidth(), sourceImage.getHeight(), null, 0, sourceImage.getWidth());
				rgbData = ImageFilter.getGreen(rgbData, sourceImage.getWidth());
				resultImage.setRGB(0, 0, sourceImage.getWidth(), sourceImage.getHeight(), rgbData, 0, sourceImage.getWidth());
				resultImageLabel.setIcon(new ImageIcon(resultImage.getScaledInstance((int)(resultImage.getWidth() * imageScale), 
						(int)(resultImage.getHeight() * imageScale), Image.SCALE_SMOOTH)));
		}
		else if (e.getSource() == getBlue){
				int []rgbData = sourceImage.getRGB(0, 0, sourceImage.getWidth(), sourceImage.getHeight(), null, 0, sourceImage.getWidth());
				rgbData = ImageFilter.getBlue(rgbData, sourceImage.getWidth());
				resultImage.setRGB(0, 0, sourceImage.getWidth(), sourceImage.getHeight(), rgbData, 0, sourceImage.getWidth());
				resultImageLabel.setIcon(new ImageIcon(resultImage.getScaledInstance((int)(resultImage.getWidth() * imageScale), 
						(int)(resultImage.getHeight() * imageScale), Image.SCALE_SMOOTH)));
		}
	}


}
