

import javax.swing.SwingUtilities;

public class AppClient {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new AppFrame();
				
			}
			
		});

	}

}
