import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KeyboardListen extends JPanel implements KeyListener {

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_UP && Board.winner) {
            Drawer.restartGame();
            System.out.println("Restart Pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
