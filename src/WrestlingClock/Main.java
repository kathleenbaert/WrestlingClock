package WrestlingClock;

import java.awt.EventQueue;

public class Main {

	// kbaert
	// Initial MVP: 11/7/23

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminConsole adminConsole = new AdminConsole();
					DisplayConsole displayConsole = new DisplayConsole();
					adminConsole.setVisible(true);
					displayConsole.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
