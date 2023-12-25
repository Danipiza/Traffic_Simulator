package a.extra.examples;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Ejemplo1 {
	public static void main(String []args) {
		final JFrame ventana = new JFrame("Mi primera ventana - Ejemplo 1");
		ventana.setSize(320, 200);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ventana.setVisible(true);
			}
		});
	}
}
