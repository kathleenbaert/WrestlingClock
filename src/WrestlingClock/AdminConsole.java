package WrestlingClock;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AdminConsole extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JButton redUp;
	private static JButton greenUp;
	private static JButton redDown;
	private static JButton greenDown;
	private static JButton startClock;
	private static JButton stopClock;
	private static JButton clearClock;
	
	private static JButton period1;
	private static JButton period2;
	private static JButton period3;

	
	private static JButton p30s;
	private static JButton p10s;
	private static JButton p5s;
	private static JButton p1s;
	private static JButton m30s;
	private static JButton m10s;
	private static JButton m5s;
	private static JButton m1s;	
	
	private static JLabel currentTimeLabel;
	private static JLabel currentTime;
	private static JLabel currentRed;
	private static JLabel currentGreen;
	
	
	private static JButton updateWrestler1Name;
	private static JButton updateWrestler2Name;

	private static JTextField wrestler1Name;
	private static JTextField wrestler2Name;


	private static JFrame frame;

	public AdminConsole() {
		
		frame = new JFrame("Wrestling Clock Admin Console");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		

	}

	public static void addComponentsToPane(Container pane) {

		pane.setLayout(new GridBagLayout());
		pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc = new GridBagConstraints();

		// general properties
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.BOTH;

		// set up first row
		gbc.gridy = 0;

		redUp = new JButton("+1 Red");
		redUp.setBackground(Constants.RED);
		redUp.setOpaque(true);
		gbc.gridx = 0;
		pane.add(redUp, gbc);


		greenUp = new JButton("+1 Green");
		greenUp.setBackground(Constants.GREEN);
		greenUp.setOpaque(true);
		gbc.weightx = 0.5;
		gbc.gridx = 2;
		pane.add(greenUp, gbc);

		// set up second row
		gbc.gridy = 1;

		redDown = new JButton("-1 Red");
		redDown.setBackground(Constants.RED);
		redDown.setOpaque(true);
		gbc.gridx = 0;
		pane.add(redDown, gbc);

		startClock = new JButton("Start Clock");
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		pane.add(startClock, gbc);

		greenDown = new JButton("-1 Green");
		greenDown.setBackground(Constants.GREEN);
		greenDown.setOpaque(true);
		gbc.gridx = 2;
		pane.add(greenDown, gbc);

		// set up third row
		gbc.gridy = 2;

		wrestler1Name = new JTextField();
		wrestler1Name.setText("Enter Name");
		wrestler1Name.setHorizontalAlignment(JTextField.CENTER);
		gbc.gridx = 2;
		pane.add(wrestler1Name, gbc);

		wrestler2Name = new JTextField();
		wrestler2Name.setText("Enter Name");
		wrestler2Name.setHorizontalAlignment(JTextField.CENTER);
		gbc.gridx = 0;
		pane.add(wrestler2Name, gbc);

		stopClock = new JButton("Stop Clock");
		gbc.gridx = 1;
		pane.add(stopClock, gbc);

		// set up fourth row
		gbc.gridy = 3;

		clearClock = new JButton("Clear Clock");
		gbc.gridx = 1;
		pane.add(clearClock, gbc);

		updateWrestler1Name = new JButton("Update Name");
		gbc.gridx = 2;
		pane.add(updateWrestler1Name, gbc);

		updateWrestler2Name = new JButton("Update Name");
		gbc.gridx = 0;
		pane.add(updateWrestler2Name, gbc);

		// set up fifth row
			
		gbc.gridy = 4;
		
		period1 = new JButton("Period 1");
		gbc.gridx = 2;
		pane.add(period1, gbc);
		
		period2 = new JButton("Period 2");
		gbc.gridx = 1;
		pane.add(period2, gbc);		
		
		period3 = new JButton("Period 3");
		gbc.gridx = 0;
		pane.add(period3, gbc);	

		//set up sixth row
		gbc.gridy = 5;	
		
		p30s = new JButton("+30 s");
		gbc.gridx = 3;
		pane.add(p30s, gbc);

		p10s = new JButton("+10 s");
		gbc.gridx = 2;
		pane.add(p10s, gbc);		
		
		p5s = new JButton("+5 s");
		gbc.gridx = 1;
		pane.add(p5s, gbc);		
		
		p1s = new JButton("+1 s");
		gbc.gridx = 0;
		pane.add(p1s, gbc);			
		
		//set up seventh row
		gbc.gridy = 6;	
		
		m30s = new JButton("-30 s");
		gbc.gridx = 3;
		pane.add(m30s, gbc);

		m10s = new JButton("-10 s");
		gbc.gridx = 2;
		pane.add(m10s, gbc);		
		
		m5s = new JButton("-5 s");
		gbc.gridx = 1;
		pane.add(m5s, gbc);		
		
		m1s = new JButton("-1 s");
		gbc.gridx = 0;
		pane.add(m1s, gbc);		
		
		//set up eighth row
		gbc.gridy = 7;
		
		currentTimeLabel = new JLabel("Current Scoreboard:");
		gbc.gridx = 3;
		pane.add(currentTimeLabel, gbc);
		
		currentTime = new JLabel("0:00");
		gbc.gridx = 2;
		pane.add(currentTime, gbc);
				
		currentGreen = new JLabel("Green: 0");
		gbc.gridx = 1;
		pane.add(currentGreen,gbc);

		currentRed = new JLabel("Red: 0");
		gbc.gridx = 0;
		pane.add(currentRed, gbc);

		redUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.increaseRedScoreVal();
			}
		});

		redDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.decreaseRedScoreVal();
			}
		});

		greenUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DisplayConsole.increaseGreenScoreVal();
			}
		});

		greenDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.decreaseGreenScoreVal();
			}
		});

		stopClock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.stopClock();
			}

		});

		clearClock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.clearClock();
			}
		});

		startClock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.startClock();
			}
		});

		updateWrestler1Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.setWrestler1Name(wrestler1Name.getText());
			}

		});

		updateWrestler2Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.setWrestler2Name(wrestler2Name.getText());
			}

		});
		
		
		period1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.setPeriod("Period 1");
			}
		});
		
		period2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.setPeriod("Period 2");
			}
		});
		
		period3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.setPeriod("Period 3");
			}
		});
		
		
		p30s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.addTime(30);
			}
		});

		p10s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.addTime(10);
			}
		});	
		
		p5s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.addTime(5);
			}
		});	
		
		p1s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.addTime(1);
			}
		});	
		
		m30s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.addTime(-30);
			}
		});	
		
		m10s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.addTime(-10);
			}
		});
		
		m5s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.addTime(-5);
			}
		});
		
		m1s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.addTime(-1);
			}
		});
	}
	
	public static void updateTime() {
		currentTime.setText(DisplayConsole.getsTime());
		
		currentRed.setText("Red: " + DisplayConsole.getRedScoreVal());
		currentGreen.setText("Green: " + DisplayConsole.getGreenScoreVal());

		
		if(DisplayConsole.getTime() <= 5) {
			frame.getContentPane().setBackground(Constants.RED);	
		}else {
			frame.getContentPane().setBackground(null);
		}		
	}

}
