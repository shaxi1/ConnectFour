import java.awt.*;

public class Checker {
    public static boolean checkForWinner(int currentColumn, int currentRow, Color colour) {
        if (Checker.checkUpDownLeftRight(currentColumn, currentRow, colour)) return true;
        if (Checker.checkDiagonally(currentColumn, currentRow, colour)) return true;
        return false;
    }

    private static boolean checkDiagonally(int currentColumn, int currentRow, Color colour) {
        int xStart;
        int count;

        int yStart;

        //sprawdzenie lewej gory
        count = 1;
        yStart = currentRow;
        xStart = currentColumn;
        xStart--;
        yStart--;
        while(yStart>=0 && xStart>=0){
            if(!Drawer.grid[yStart][xStart].equals(colour)){
                break;
            }
            count++;
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
        while(yStart< Drawer.grid.length && xStart< Drawer.grid[0].length){
            if(!Drawer.grid[yStart][xStart].equals(colour)){
                break;
            }
            count++;
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
        while(yStart< Drawer.grid.length && xStart>=0){
            if(!Drawer.grid[yStart][xStart].equals(colour)){
                break;
            }
            count++;
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
        while(yStart>=0 && xStart< Drawer.grid[0].length){
            if(!Drawer.grid[yStart][xStart].equals(colour)){
                break;
            }
            count++;
            if(count==4){
                return true;
            }
            yStart--;
            xStart++;
        }
        return false;
    }

    private static boolean checkUpDownLeftRight(int currentColumn, int currentRow, Color colour) {
        //sprawdzanie poziomo
        int count = 0;
        int xStart = currentColumn -3;
        if(xStart<0){
            xStart = 0;
        }
        int xEnd = currentColumn +3;
        if(xEnd>Drawer.COLUMNS-1){
            xEnd = Drawer.COLUMNS-1;
        }
        while(xStart!=xEnd+1)
        {
            if(Drawer.grid[currentRow][xStart].equals(colour))
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
        int yStart = currentRow +3;
        if(yStart>Drawer.ROWS-1){
            yStart = Drawer.ROWS-1;
        }
        int yEnd = currentRow -3;
        if(yEnd<0){
            yEnd = 0;
        }
        while(yStart!=yEnd-1)
        {
            if(Drawer.grid[yStart][currentColumn].equals(colour)){
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
        return false;
    }

    public static boolean checkForDraw(){
        for(int i = 0; i < Drawer.grid.length; i++){
            for(int j = 0; j < Drawer.grid[0].length; j++){
                if(Drawer.grid[i][j].equals(Color.white)) return false;
            }
        }
        return true;
    }

    public static int searchFreeSpot(int clickedColumn){
        int clickedRow = Drawer.grid.length-1;

        while(clickedRow>=0){
            if(Drawer.grid[clickedRow][clickedColumn].equals(Color.white)){
                return clickedRow;
            }
            clickedRow--;
        }
        return -1;
    }
}
