
package robotbuilder;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author brad
 */
public class RobotBuilder {


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}