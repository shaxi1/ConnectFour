import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListen implements MouseListener {
    @Override
    public void mousePressed(MouseEvent e)
    {
        int xPosition = e.getX(); //pobranie pozycji kursora
        int yPosition = e.getY();
        if(!Board.winner) {
            if(xPosition<(Drawer.CELL_SIZE * Drawer.grid[0].length) && yPosition<(Drawer.CELL_SIZE * Drawer.grid.length)){
                int clickedRow = yPosition/Drawer.CELL_SIZE;
                int clickedCol = xPosition/Drawer.CELL_SIZE;

                clickedRow = Checker.searchFreeSpot(clickedCol); //sprawdzanie na jekiej wysokosci dac kolor
                System.out.println("XDDDDD1");
                if(clickedRow!=-1) {
                    System.out.println("XDDDDD2");


                    Drawer.grid[clickedRow][clickedCol] = Color.yellow;
                    Drawer.cellColor = "Yellow";

                    if(Board.turn%2==0){
                        Drawer.grid[clickedRow][clickedCol]= Color.red; //ustawianie koloru
                        Drawer.cellColor = "RED";
                    }


                    if(Checker.checkForDraw()){
                        Board.winner = true;
                        System.out.println("XDDDDD3");
                        Board.draw = true;
                    }
                    if (Checker.checkForWinner(clickedCol, clickedRow, Color.red)) {
                        Board.winner = true;
                        Board.redWins++;
                    }
                    else if (Checker.checkForWinner(clickedCol, clickedRow, Color.yellow)) {
                        Board.winner = true;
                        Board.yellowWins++;
                    }
                    Board.turn++;
                }
            }
        }
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
