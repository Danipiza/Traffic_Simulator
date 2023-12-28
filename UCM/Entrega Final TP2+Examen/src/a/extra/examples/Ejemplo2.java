package a.extra.examples;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Ejemplo2{
  public static void main(String []args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame ventana = 
          new JFrame("Mi primera ventana - Ejemplo 2");
        ventana.setSize(320, 200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true); // Llamada a setVisible usando el thread de Swing
      }
    });
  }
}
