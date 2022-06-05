import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Board {
    public static JFrame frame;
    public static boolean winner = false;
    public static int turn = 2;
    public static int yellowWins = 0;
    public static int redWins = 0;
    public static boolean draw = false;

    public Board() {
    }

    public void drawBoard() {
        frame = new JFrame("mainBoard");
        frame.addKeyListener(new KeyboardListen());
        frame.setSize(750, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        // dodaj komponenty do ramki
        frame.add(new Drawer(frame.getSize()));
        // ustawienie wielkosci ramki, tak aby jej komponenty byly rowne im "PreferredSize"
        frame.pack();
        // pokaz ramke
        frame.setVisible(true);
        frame.setResizable(false);
        int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
        int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
        frame.setLocation((screenWidth - frame.getWidth()) / 2, (screenHeight - frame.getHeight()) / 2);
    }


}
