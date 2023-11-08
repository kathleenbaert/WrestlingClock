package WrestlingClock;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminConsole frame = new AdminConsole();
					DisplayConsole frame1 = new DisplayConsole();
					frame.setVisible(true);
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
