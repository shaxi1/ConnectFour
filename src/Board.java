import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.*;


public class Board {
    public static JFrame frame;
    public static boolean winner = false;
    public static int turn = 2;
    public static int yellowWins = 0;
    public static int redWins = 0;
    public static boolean draw = false;
    public static int GAPSIZE = 10;
    public static int BOARD_WIDTH = (Drawer.COLUMNS) * Drawer.CELL_SIZE + (GAPSIZE * Drawer.COLUMNS) + Drawer.GAMEEND_FIELDS_END_WIDTH_OFFSET;
    public static int BOARD_HEIGHT = Drawer.ROWS * Drawer.CELL_SIZE + (GAPSIZE * Drawer.ROWS) + Drawer.GAMEEND_FIELDS_END_HEIGHT_OFFSET;

    public Board() {
    }

    public void drawBoard() {
        frame = new JFrame("mainBoard");
        frame.addKeyListener(new KeyboardListen());
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        // dodaj komponenty do ramki
        frame.add(new Drawer(frame.getSize()));
        // ustawienie wielkosci ramki, tak aby jej komponenty byly rowne im "PreferredSize"
        //frame.pack();
        // pokaz ramke
        frame.setVisible(true);
        frame.setResizable(false);
        int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
        int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
        frame.setLocation((screenWidth - frame.getWidth()) / 2, (screenHeight - frame.getHeight()) / 2);
    }


}
