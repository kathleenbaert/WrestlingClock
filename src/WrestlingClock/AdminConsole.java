package WrestlingClock;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AdminConsole extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JButton redUp;
	private static JButton greenUp;
	private static JButton redDown;
	private static JButton greenDown;
	private static JButton startTime;
	private static JButton stopClock;
	private static JButton clearClock;
	private static JButton updateWrestler1Name;
	private static JButton updateWrestler2Name;
	private static JButton startCustomTime;

	private static JTextField wrestler1Name;
	private static JTextField wrestler2Name;

	private static JComboBox<String> timeDropDown;
	private static JComboBox<String> periodDropDown;
	private static JComboBox<String> customMinDropDown;
	private static JComboBox<String> customSecDropDown;

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

		timeDropDown = new JComboBox<>(Constants.TIME_OPTIONS);
		timeDropDown.setSelectedIndex(0);
		gbc.gridx = 1;
		pane.add(timeDropDown, gbc);

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

		startTime = new JButton("Start Clock");
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		pane.add(startTime, gbc);

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
		periodDropDown = new JComboBox<>(Constants.PERIOD_OPTIONS);
		periodDropDown.setSelectedIndex(0);
		gbc.gridx = 1;
		pane.add(periodDropDown, gbc);

		// set up sixth row
		gbc.gridy = 5;
		customMinDropDown = new JComboBox<>(Constants.MIN_OPTIONS);
		customMinDropDown.setSelectedIndex(0);
		gbc.gridx = 2;
		pane.add(customMinDropDown, gbc);

		customSecDropDown = new JComboBox<>(Constants.SEC_OPTIONS);
		customSecDropDown.setSelectedIndex(0);
		gbc.gridx = 1;
		pane.add(customSecDropDown, gbc);

		startCustomTime = new JButton("Start Custom Time");
		gbc.gridx = 0;
		pane.add(startCustomTime, gbc);

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

		startTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConvertTime ct = new ConvertTime(Constants.TIME_OPTIONS[timeDropDown.getSelectedIndex()]);
				DisplayConsole.startClock(ct.getTimeMS());
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

		periodDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.setPeriod(Constants.PERIOD_OPTIONS[periodDropDown.getSelectedIndex()]);
			}
		});

		startCustomTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String min = Constants.MIN_OPTIONS[customMinDropDown.getSelectedIndex()];
				String sec = Constants.SEC_OPTIONS[customSecDropDown.getSelectedIndex()];
				ConvertTime ct = new ConvertTime(min + ":" + sec);
				DisplayConsole.startClock(ct.getTimeMS());
			}
		});

		timeDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.setTime(Constants.TIME_OPTIONS[timeDropDown.getSelectedIndex()]);
			}

		});

		customMinDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.setTime(Constants.MIN_OPTIONS[customMinDropDown.getSelectedIndex()] + ":"
						+ Constants.SEC_OPTIONS[customSecDropDown.getSelectedIndex()]);
			}

		});

		customSecDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayConsole.setTime(Constants.MIN_OPTIONS[customMinDropDown.getSelectedIndex()] + ":"
						+ Constants.SEC_OPTIONS[customSecDropDown.getSelectedIndex()]);
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

	}

}
