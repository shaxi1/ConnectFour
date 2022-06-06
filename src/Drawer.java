import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.showMessageDialog;


public class Drawer extends JPanel implements MouseListener {
    int startX = 10;
    int startY = 10;
    String cellColor = "";
    final int CELL_SIZE = 80;

    static final int ROWS = 6;
    static final int COLUMNS = 7;
    static Color[][] grid = new Color[ROWS][COLUMNS];

    public Drawer(Dimension dimension) {
        setSize(dimension);
        setPreferredSize(dimension);
        addMouseListener(this);

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
                graphics2D.fillOval(startX, startY, CELL_SIZE-10, CELL_SIZE-10);
                graphics2D.setColor(Color.black);
                //graphics2D.drawRect(startX, startY, CELL_SIZE, CELL_SIZE);
                startX += CELL_SIZE;
            }
            startY += CELL_SIZE;
            startX = 0;
        }

        final int TURN_STRING_OFFSETX = 600;
        final int TURN_STRING_OFFSETY = 20;
        final int PLAYERNAME_STRING_OFFSETX = 680;
        final int TURN_STRING_FONTSIZE = 16;
        graphics2D.setColor(new Color(255, 255, 255));

        printLeaderBoard((Graphics2D) graphics, TURN_STRING_OFFSETX, TURN_STRING_FONTSIZE);


        if(!Board.winner){
            if(Board.turn%2 == 0) {
                graphics2D.setColor(Color.red);
                graphics2D.setFont(new Font("default", Font.BOLD, TURN_STRING_FONTSIZE));
                graphics2D.drawString("Red's Turn", TURN_STRING_OFFSETX, TURN_STRING_OFFSETY);
            }
            else {
                graphics2D.setColor(Color.yellow);
                graphics2D.setFont(new Font("default", Font.BOLD, TURN_STRING_FONTSIZE));
                graphics2D.drawString("Yellow's Turn", TURN_STRING_OFFSETX, TURN_STRING_OFFSETY);
            }
        }
        else if(Board.draw) {
            graphics2D.setColor(Color.green);
            graphics2D.setFont(new Font("default", Font.BOLD, TURN_STRING_FONTSIZE));
            graphics2D.drawString("DRAW", TURN_STRING_OFFSETX, TURN_STRING_OFFSETY);
            printRestartMessage(graphics2D);
        }
        else {
            graphics2D.setColor(Color.green);
            graphics2D.setFont(new Font("default", Font.BOLD, TURN_STRING_FONTSIZE));
            graphics2D.drawString("WINNER - ", TURN_STRING_OFFSETX, TURN_STRING_OFFSETY);
            if(Board.turn%2 == 0)
                graphics2D.setColor(Color.yellow);
            else
                graphics2D.setColor(Color.red);
            graphics2D.drawString(cellColor, PLAYERNAME_STRING_OFFSETX, TURN_STRING_OFFSETY);

            printRestartMessage(graphics2D);
        }

    }

    private void printRestartMessage(Graphics2D graphics2D) {
        final int GAMEEND_STRING_OFFSETX = 180;
        final int GAMEEND_STRING_OFFSETY = 510;
        final int GAMEEND_STRING_OFFSETY_PLAYER = GAMEEND_STRING_OFFSETY + 20;
        final int GAMEEND_STRING_OFFSETX_PLAYER = 300;
        graphics2D.setColor(Color.green);
        graphics2D.drawString("Press Arrow UP to restart and start playing!", GAMEEND_STRING_OFFSETX, GAMEEND_STRING_OFFSETY);

        if(Board.turn%2 == 0) {
            graphics2D.setColor(Color.red);
            graphics2D.drawString("Red starts!", GAMEEND_STRING_OFFSETX_PLAYER, GAMEEND_STRING_OFFSETY_PLAYER);
        }
        else {
            graphics2D.setColor(Color.yellow);
            graphics2D.drawString("Yellow starts!", GAMEEND_STRING_OFFSETX_PLAYER, GAMEEND_STRING_OFFSETY_PLAYER);
        }
    }

    private void printLeaderBoard(Graphics2D graphics, int TURN_STRING_OFFSETX, int TURN_STRING_FONTSIZE) {
        final int LEADERBOARD_STRING_OFFSETY_YELLOW = 60;
        final int LEADERBOARD_STRING_OFFSETY_RED = LEADERBOARD_STRING_OFFSETY_YELLOW + 20;

        Graphics2D leaderBoard = graphics;
        leaderBoard.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        leaderBoard.setColor(new Color(255, 255, 255));
        leaderBoard.setFont(new Font("default", Font.BOLD, TURN_STRING_FONTSIZE));
        leaderBoard.drawString("Yellow wins: " + Board.yellowWins, TURN_STRING_OFFSETX, LEADERBOARD_STRING_OFFSETY_YELLOW);
        leaderBoard.drawString("Red wins: " + Board.redWins, TURN_STRING_OFFSETX, LEADERBOARD_STRING_OFFSETY_RED);
    }


    private static void clearFields() {
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[0].length; col++)
                grid[row][col] = Color.white;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int xPosition = e.getX(); //pobranie pozycji kursora
        int yPosition = e.getY();
        if(!Board.winner) {
            if(xPosition<(CELL_SIZE*grid[0].length) && yPosition<(CELL_SIZE*grid.length)){
                int clickedRow = yPosition/CELL_SIZE;
                int clickedCol = xPosition/CELL_SIZE;

                clickedRow = searchFreeSpot(clickedCol); //sprawdzanie na jekiej wysokosci dac kolor

                if(clickedRow!=-1){

                    if(Board.turn%2==0){
                        grid[clickedRow][clickedCol]= Color.red; //ustawianie koloru
                        cellColor = "RED";
                    } else{
                        grid[clickedRow][clickedCol]= Color.yellow;
                        cellColor = "Yellow";
                    }
                    if(Board.turn%2==0) {
                        if(checkForDraw()){
                            Board.winner = true;
                            Board.draw = true;
                        }
                        if (checkForWinner(clickedCol, clickedRow, Color.red)) {
                            Board.winner = true;
                            Board.redWins++;
                            //restartGame();
                        }
                    }
                    else {
                        if(checkForDraw()){
                            Board.winner = true;
                            Board.draw = true;
                        }
                        if (checkForWinner(clickedCol, clickedRow, Color.yellow)) {
                            Board.winner = true;
                            Board.yellowWins++;
                            //restartGame();
                        }
                    }
                    Board.turn++;
//                    if(checkForWinner(clickedCol,clickedRow, grid[clickedRow][clickedCol])){
//                        Board.winner=true;

                    }
                }
            }
        repaint();
    }

    public static void restartGame() {
        clearFields();
        Board.winner = false;
    }

    public static boolean checkForDraw(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 6; j++){
                if(grid[i][j].equals(Color.white)) return false;
            }
        }
        return true;
    }

    public static boolean checkForWinner(int currentColumn, int currentRow, Color colour){
        //sprawdzanie poziomo
        int count = 0;
        int xStart = currentColumn-3;
        if(xStart<0){
            xStart = 0;
        }
        int xEnd = currentColumn+3;
        if(xEnd>6){
            xEnd = 6;
        }
        while(xStart!=xEnd+1)
        {
            if(grid[currentRow][xStart].equals(colour))
                count++;
            else{
                count = 0;
            }
            if(count==4){
                return true;
            }
            xStart++;
        }

        //sprawdzanie pionowo
        count = 0;
        int yStart = currentRow+3;
        if(yStart>5){
            yStart = 5;
        }
        int yEnd = currentRow-3;
        if(yEnd<0){
            yEnd = 0;
        }
        while(yStart!=yEnd-1)
        {
            if(grid[yStart][currentColumn].equals(colour)){
                count++;
            }
            else{
                count = 0;
            }
            if(count==4){
                return true;
            }
            yStart--;
        }

        //sprawdzenie lewej gory
        count = 1;
        yStart = currentRow;
        xStart = currentColumn;
        xStart--;
        yStart--;
        while(yStart>=0 && xStart>0){
            if(grid[yStart][xStart].equals(colour)){
                count++;
            } else{
                break;
            }
            if(count==4){
                return true;
            }
            yStart--;
            xStart--;
        }

        //sprawdzenie prawego dolu
        count = 1;
        yStart = currentRow;
        yStart++;
        xStart = currentColumn;
        xStart++;
        while(yStart<grid.length && xStart<grid[0].length){
            if(grid[yStart][xStart].equals(colour)){
                count++;
            } else{
                break;
            }
            if(count==4){
                return true;
            }
            yStart++;
            xStart++;
        }

        //sprawdzenie lewego dolu
        count = 1;
        yStart = currentRow;
        xStart = currentColumn;
        xStart--;
        yStart++;
        while(yStart<grid.length && xStart>0){
            if(grid[yStart][xStart].equals(colour)){
                count++;
            } else{
                break;
            }
            if(count==4){
                return true;
            }
            yStart++;
            xStart--;
        }

        //sprawdzenie prawej gory
        count = 1;
        yStart = currentRow;
        yStart--;
        xStart = currentColumn;
        xStart++;
        while(yStart>=0 && xStart<grid[0].length){
            if(grid[yStart][xStart].equals(colour)){
                count++;
            } else{
                break;
            }
            if(count==4){
                return true;
            }
            yStart--;
            xStart++;
        }
        return false;
    }
    public int searchFreeSpot(int clickedColumn){
        int clickedRow = grid.length-1;

        while(clickedRow>=0){
            if(grid[clickedRow][clickedColumn].equals(Color.white)){
                return clickedRow;
            }
            clickedRow--;
        }
        return -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
