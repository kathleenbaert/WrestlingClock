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

	private static Timer timer = null;
	private static int time;
	private static String sTime;

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
		AdminConsole.updateTime();
	}

	public static void decreaseRedScoreVal() {
		if (redScoreVal > 0) {
			redScoreVal--;
			redScore.setText(Integer.toString(redScoreVal));
		}
		AdminConsole.updateTime();
	}

	public static void increaseGreenScoreVal() {
		greenScoreVal++;
		greenScore.setText(Integer.toString(greenScoreVal));
		AdminConsole.updateTime();
	}

	public static void decreaseGreenScoreVal() {
		if (greenScoreVal > 0) {
			greenScoreVal--;
			greenScore.setText(Integer.toString(greenScoreVal));
		}
		AdminConsole.updateTime();
	}

	public static void setPeriod(String p) {
		period.setText(p);
	}

	public static void startClock() {
		int delay = 1000;

		ActionListener countDown = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				time--;
				setVisualTime();

			}
		};
		if (timer == null) {
			timer = new Timer(delay, countDown);
		}
		if (!timer.isRunning()) {
			timer.start();
		}
	}

	public static void clearClock() {
		if (timer.isRunning()) {
			timer.stop();
		}
		clock.setText("0:00");
		time = 0;

	}

	public static void stopClock() {
		if (timer.isRunning()) {
			timer.stop();
		}
	}
	
	public static void startOrStopClock() {
		if(timer.isRunning()) {
			timer.stop();
		}else {
			startClock();
		}
	}

	public static void addTime(int addTime) {
		if ((time + addTime) >= 0) {
			time += addTime;
		}
		setVisualTime();
	}

	public static void setVisualTime() {
		if (time > 0) {
			if ((time % 60) < 10) {
				sTime = time / 60 + ":0" + time % 60;
			} else {
				sTime = time / 60 + ":" + time % 60;
			}
		} else {
			sTime = "0:00";
			timer.stop();
		}
		clock.setText(sTime);
		AdminConsole.updateTime();
	}

	public void componentResized(ComponentEvent e) {
		int width = frame.getWidth();

		redScore.setFont(new Font("Arial", Font.PLAIN, width / Constants.BIG_FRACTIONAL_FONT_SiZE));
		greenScore.setFont(new Font("Arial", Font.PLAIN, width / Constants.BIG_FRACTIONAL_FONT_SiZE));
		clock.setFont(new Font("Arial", Font.PLAIN, width / Constants.BIG_FRACTIONAL_FONT_SiZE));
		period.setFont(new Font("Arial", Font.PLAIN, width / Constants.SMALL_FRACTIONAL_FONT_SIZE));
		wrestler1Name.setFont(new Font("Arial", Font.PLAIN, width / sizeName(wrestler1Name.getText())));
		wrestler2Name.setFont(new Font("Arial", Font.PLAIN, width / sizeName(wrestler2Name.getText())));

		frame.getContentPane().revalidate();
	}

	public static void setWrestler1Name(String n) {
		int width = frame.getWidth();
		wrestler1Name.setFont(new Font("Arial", Font.PLAIN, width / sizeName(n)));
		wrestler1Name.setText(n);
	}

	public static void setWrestler2Name(String n) {
		int width = frame.getWidth();

		wrestler2Name.setFont(new Font("Arial", Font.PLAIN, width / sizeName(n)));

		wrestler2Name.setText(n);

	}

	private static int sizeName(String n) {
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

	public static String getsTime() {
		return sTime;
	}
	public static int getTime() {
		return time;
	}
	
	public static int getRedScoreVal() {
		return redScoreVal;
	}

	public static int getGreenScoreVal() {
		return greenScoreVal;
	}

}
