import javax.swing.SwingUtilities;

/**
 * @author Volodymyr Osadchuk
 *
 * Program 'Graphics Editor' in Java
 * using library swing
 */
public class Starter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new Window();
            }
        });
    }
}