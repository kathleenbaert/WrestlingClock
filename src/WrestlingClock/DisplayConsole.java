package WrestlingClock;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class DisplayConsole extends JFrame implements ComponentListener {

	private static final long serialVersionUID = 1L;

	private static JLabel redScore;
	private static JLabel greenScore;
	private static JLabel clock;
	private static JLabel period;
	private static JLabel wrestler1Name;
	private static JLabel wrestler2Name;

	private static int redScoreVal = 0;
	private static int greenScoreVal = 0;
	private static boolean isPaused = false;
	private static String pausedTime = "";
	private static long startTime = -1;
	private static Timer timer = null;

	private static JFrame frame;

	public DisplayConsole() {
		frame = new JFrame("Scoreboard Display");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponentListener(this);

		addComponentsToPane(frame.getContentPane());

		frame.pack();
		frame.setVisible(true);

	}

	public static void addComponentsToPane(Container pane) {

		pane.setLayout(new GridBagLayout());
		pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		pane.setBackground(Color.black);
		GridBagConstraints gbc = new GridBagConstraints();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();

		// general properties
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 20, 20, 20);

		// set up first row
		gbc.gridy = 0;
		redScore = new JLabel("0", SwingConstants.CENTER);
		redScore.setVerticalTextPosition(JLabel.CENTER);
		redScore.setHorizontalAlignment(JLabel.CENTER);
		redScore.setForeground(Color.white);
		gbc.gridx = 0;
		redScore.setFont(new Font("Arial", Font.PLAIN, (int) width / Constants.BIG_FRACTIONAL_FONT_SiZE));
		redScore.setBackground(Constants.RED);
		redScore.setOpaque(true);
		pane.add(redScore, gbc);

		clock = new JLabel("0:00", SwingConstants.CENTER);
		clock.setVerticalTextPosition(JLabel.CENTER);
		clock.setHorizontalAlignment(JLabel.CENTER);
		clock.setForeground(Color.white);
		gbc.gridx = 1;
		clock.setFont(new Font("Arial", Font.PLAIN, (int) width / Constants.BIG_FRACTIONAL_FONT_SiZE));
		pane.add(clock, gbc);

		greenScore = new JLabel("0", SwingConstants.CENTER);
		greenScore.setVerticalAlignment(JLabel.CENTER);
		greenScore.setHorizontalAlignment(JLabel.CENTER);
		greenScore.setForeground(Color.white);
		gbc.gridx = 2;
		greenScore.setFont(new Font("Arial", Font.PLAIN, (int) width / Constants.BIG_FRACTIONAL_FONT_SiZE));
		greenScore.setBackground(Constants.GREEN);
		greenScore.setOpaque(true);
		pane.add(greenScore, gbc);

		// start second row
		gbc.gridy = 1;

		wrestler2Name = new JLabel("", SwingConstants.CENTER);
		wrestler2Name.setVerticalAlignment(JLabel.CENTER);
		wrestler2Name.setHorizontalAlignment(JLabel.CENTER);
		wrestler2Name.setForeground(Color.white);
		gbc.gridx = 0;
		wrestler2Name.setFont(new Font("Arial", Font.PLAIN, (int) width / Constants.SMALL_FRACTIONAL_FONT_SIZE));
		pane.add(wrestler2Name, gbc);

		period = new JLabel("Period 1");
		period.setVerticalAlignment(JLabel.CENTER);
		period.setHorizontalAlignment(JLabel.CENTER);
		period.setForeground(Color.white);
		gbc.gridx = 1;
		period.setFont(new Font("Arial", Font.PLAIN, (int) width / Constants.SMALL_FRACTIONAL_FONT_SIZE));
		pane.add(period, gbc);

		wrestler1Name = new JLabel("", SwingConstants.CENTER);
		wrestler1Name.setVerticalAlignment(JLabel.CENTER);
		wrestler1Name.setHorizontalAlignment(JLabel.CENTER);
		wrestler1Name.setForeground(Color.white);
		gbc.gridx = 2;
		wrestler1Name.setFont(new Font("Arial", Font.PLAIN, (int) width / Constants.SMALL_FRACTIONAL_FONT_SIZE));
		pane.add(wrestler1Name, gbc);

	}

	public static void increaseRedScoreVal() {
		redScoreVal++;
		redScore.setText(Integer.toString(redScoreVal));
	}

	public static void decreaseRedScoreVal() {
		if (redScoreVal > 0) {
			redScoreVal--;
			redScore.setText(Integer.toString(redScoreVal));
		}
	}

	public static void increaseGreenScoreVal() {
		greenScoreVal++;
		greenScore.setText(Integer.toString(greenScoreVal));
	}

	public static void decreaseGreenScoreVal() {
		if (greenScoreVal > 0) {
			greenScoreVal--;
			greenScore.setText(Integer.toString(greenScoreVal));
		}
	}

	public static void setPeriod(String p) {
		period.setText(p);
	}

	public static void startClock(long duration) {

		if (isPaused && !pausedTime.isEmpty()) {
			// means it's paused and need to start a new timer
			ConvertTime ct = new ConvertTime(pausedTime);
			duration = ct.getTimeMS();
		}

		startTimer(duration);

	}

	private static void startTimer(long duration) {
		isPaused = false;
		timer = new Timer(10, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (startTime < 0) {
					startTime = System.currentTimeMillis();
				}
				long now = System.currentTimeMillis();
				long clockTime = now - startTime;
				if (clockTime >= duration) {
					clockTime = duration;
					timer.stop();
				}

				SimpleDateFormat df = new SimpleDateFormat("m:ss");
				clock.setText(df.format(duration - clockTime));
			}
		});

		timer.setInitialDelay(0);

		if (!timer.isRunning()) {
			startTime = -1;
			timer.start();
		}
	}

	public static void clearClock() {
		if (timer.isRunning()) {
			timer.stop();
		}
		isPaused = false;
		pausedTime = "";
		clock.setText("0:00");

	}

	public static void setTime(String s) {
		clock.setText(s);
	}

	public static void stopClock() {
		if (timer.isRunning()) {
			timer.stop();
			pausedTime = clock.getText();
		}
		isPaused = true;
	}
	
	public static void startOrStopClock(long duration) {
		if(timer.isRunning()) {
			timer.stop();
			pausedTime = clock.getText();
			isPaused = true;
		}else {
			startTimer(duration);
		}
	}

	public void componentResized(ComponentEvent e) {
		int width = frame.getWidth();

		redScore.setFont(new Font("Arial", Font.PLAIN, width / Constants.BIG_FRACTIONAL_FONT_SiZE));
		greenScore.setFont(new Font("Arial", Font.PLAIN, width / Constants.BIG_FRACTIONAL_FONT_SiZE));
		clock.setFont(new Font("Arial", Font.PLAIN, width / Constants.BIG_FRACTIONAL_FONT_SiZE));
		period.setFont(new Font("Arial", Font.PLAIN, width / Constants.SMALL_FRACTIONAL_FONT_SIZE));
		wrestler1Name.setFont(new Font("Arial", Font.PLAIN, width / sizeNam(wrestler1Name.getText())));
		wrestler2Name.setFont(new Font("Arial", Font.PLAIN, width / sizeNam(wrestler2Name.getText())));

		frame.getContentPane().revalidate();
	}

	public static void setWrestler1Name(String n) {
		int width = frame.getWidth();
		wrestler1Name.setFont(new Font("Arial", Font.PLAIN, width / sizeNam(n)));
		wrestler1Name.setText(n);
	}

	public static void setWrestler2Name(String n) {
		int width = frame.getWidth();

		wrestler2Name.setFont(new Font("Arial", Font.PLAIN, width / sizeNam(n)));

		wrestler2Name.setText(n);

	}

	private static int sizeNam(String n) {
		if (n.length() > 10) {
			return Constants.XSMALL_FRACTIONAL_FONT_SIZE;
		}
		return Constants.SMALL_FRACTIONAL_FONT_SIZE;
	}

	public void componentMoved(ComponentEvent e) {

	}

	public void componentShown(ComponentEvent e) {

	}

	public void componentHidden(ComponentEvent e) {

	}

}
