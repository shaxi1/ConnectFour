import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class Drawer extends JPanel /* implements MouseListener*/ {
    int startX = 10;
    int startY = 10;
    String cellColor = "";
    final int CELL_SIZE = 60;

    final int ROWS = 6;
    final int COLUMNS = 7;
    Color[][] grid = new Color[ROWS][COLUMNS];

    public Drawer(Dimension dimension) {
        setSize(dimension);
        setPreferredSize(dimension);
        //addMouseListener(this);

        clearFields();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension dimension = getSize();

        graphics2D.setColor(new Color(40, 42, 54));
        graphics2D.fillRect(0, 0, dimension.width, dimension.height);
        startX = 0;
        startY = 0;

        // narysuj tabele z polami
        for (Color[] colors : grid) {
            for (int col = 0; col < grid[0].length; col++) {

                graphics2D.setColor(colors[col]);
                graphics2D.fillOval(startX, startY, CELL_SIZE, CELL_SIZE);
                graphics2D.setColor(Color.black);
                graphics2D.drawRect(startX, startY, CELL_SIZE, CELL_SIZE);
                startX += CELL_SIZE;
            }
            startY += CELL_SIZE;
            startX = 0;
        }

        graphics2D.setColor(new Color(255, 255, 255));
        if(!Board.winner)
            if(Board.turn%2 == 0)
                graphics2D.drawString("Red's Turn",450,20);
            else
                graphics2D.drawString("Yellow's Turn", 450, 20);
        else
            graphics2D.drawString("WINNER - "+ cellColor, 450, 20);

    }


    private void clearFields(){
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[0].length; col++)
                grid[row][col] = Color.white;
    }

}
