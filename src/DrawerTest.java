import java.awt.*;

import org.junit.Assert;
class DrawerTest {
    //Wykrycie czy remis jest
    @org.junit.jupiter.api.Test
    void checkForDrawT() {
        Board board = new Board();
        board.drawBoard();
        for (int row = 0; row < Drawer.grid.length; row++)
            for (int col = 0; col < Drawer.grid[0].length; col++)
                Drawer.grid[row][col] = Color.white;
        boolean result = Drawer.checkForDraw();
        Assert.assertEquals(false, result);
    }

    //Wykrycie czy remisu nie ma
    @org.junit.jupiter.api.Test
    void checkForDrawF() {
        Board board = new Board();
        board.drawBoard();
        for (int row = 0; row < Drawer.grid.length; row++)
            for (int col = 0; col < Drawer.grid[0].length; col++)
                Drawer.grid[row][col] = Color.black;
        boolean result = Drawer.checkForDraw();
        Assert.assertEquals(true, result);
        Drawer.grid[0][0] = Color.white;
        result = Drawer.checkForDraw();
        Assert.assertEquals(false, result);
    }

    //Sprawdzenie wygrania poziomo
    @org.junit.jupiter.api.Test
    void checkForWinnerC() {
        Board board = new Board();
        board.drawBoard();
        for (int row = 0; row < Drawer.grid.length; row++)
            for (int col = 0; col < Drawer.grid[0].length; col++)
                Drawer.grid[row][col] = Color.white;

        for (int check = 0; check < 4; check++)
            Drawer.grid[0][check] = Color.black;
        boolean result = CheckForWinner.checkForWinner(0, 0, Color.black);
        Assert.assertEquals(true, result);

    }

    //Sprawdzenie pionowo wygranej
    @org.junit.jupiter.api.Test
    void checkForWinnerR() {
        Board board = new Board();
        board.drawBoard();
        for (int row = 0; row < Drawer.grid.length; row++)
            for (int col = 0; col < Drawer.grid[0].length; col++)
                Drawer.grid[row][col] = Color.white;

        for (int check = 0; check < 4; check++)
            Drawer.grid[check][0] = Color.orange;
        boolean result = CheckForWinner.checkForWinner(0, 0, Color.orange);
        Assert.assertEquals(true, result);
    }

    //Sprawdzenie wygranej na ukos
    @org.junit.jupiter.api.Test
    void checkForWinnerDiagonal() {
        Board board = new Board();
        board.drawBoard();
        for (int row = 0; row < Drawer.grid.length; row++)
            for (int col = 0; col < Drawer.grid[0].length; col++)
                Drawer.grid[row][col] = Color.white;

        for (int check = 0; check < 4; check++)
            Drawer.grid[check][check] = Color.orange;
        boolean result = CheckForWinner.checkForWinner(0, 0, Color.orange);
        Assert.assertEquals(true, result);
    }
    //Sprawdzenie wygranej na ukos
    @org.junit.jupiter.api.Test
    void checkForWinnerDiagonalOptionTwo() {
        Board board = new Board();
        board.drawBoard();
        for (int row = 0; row < Drawer.grid.length; row++)
            for (int col = 0; col < Drawer.grid[0].length; col++)
                Drawer.grid[row][col] = Color.white;

        for (int check = 0; check < 4; check++)
            Drawer.grid[3-check][3-check] = Color.orange;
        boolean result = CheckForWinner.checkForWinner(0, 0, Color.orange);
        Assert.assertEquals(true, result);
    }
    //Sprawdzenie wygranej na ukos
    @org.junit.jupiter.api.Test
    void checkForWinnerDiagonalOptionThree() {
        Board board = new Board();
        board.drawBoard();
        for (int row = 0; row < Drawer.grid.length; row++)
            for (int col = 0; col < Drawer.grid[0].length; col++)
                Drawer.grid[row][col] = Color.white;

        for (int check = 0; check < 4; check++)
            Drawer.grid[3-check][check] = Color.orange;
        boolean result = CheckForWinner.checkForWinner(0, 3, Color.orange);
        Assert.assertEquals(true, result);
    }
}
