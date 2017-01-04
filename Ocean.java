/**
 * Ocean.java
 * @author: Ruzan Sasuri            rps7183@g.rit.edu
 * @author: Akash Venkatachalam     av2833@g.rit.edu
 * @author: Ghodratollah Aalipour   ga5481@g.rit.edu
 * Id: $ Ocean.java v1.0, 2016/11/07$
 * Revision: First Revision
 */

public class Ocean
{
    protected int maxRow;
    protected int maxColumn;
    protected char board[][];
/**
     * The constructor assigns the value for MAX_ROW and MAX_COLUMN
     * @param r number of rows
     * @param c number of columns
     */

    public Ocean(int r,int c)
    {
        maxRow = r + 2;
        maxColumn = c + 2;
        board = new char[maxRow][maxColumn];
        for(int i = 0; i < board.length; i++)
        {
            board[i][0] = 'B';
            board[i][board[i].length - 1] = 'B';
        }
        for(int i = 1; i < board[0].length - 1; i++)
        {
            board[0][i] = 'B';
            board[board.length - 1][i] = 'B';
        }
        for(int i = 1; i < board.length - 1; i++)
        {
            for(int j = 1; j < board[i].length - 1; j++)
            {
                board[i][j] = '~';
            }
        }
    }
/**
     * Returns back our Ocean as an array
     * @return
     */
    public char[][] getBoard()
    {
        return board;
    }
 /**
     * Getter method for maximum row
     * @return MAX_ROW
     */

    public int getRow()
    {
        return maxRow;
    }
/**
     * Getter method for maximum column
     * @return MAX_ROW
     */
    public int getColumn()
    {
        return maxColumn;
    }
/**
     * The next method just prints out our object as Ocean when called
     * @return
     */

    public String toString()
    {
        return "Ocean";
    }
    /*
    public static void main(String args[])
    {
        Fleet f = new Fleet(10,10);
        f.emptyBoard();
        System.out.println(f.fillFunc(1,1,2,'V'));
        System.out.println(f.fillFunc(5,6,3,'H'));
        System.out.println(f.fillFunc(1,6,4,'H'));
        System.out.println(f.fillFunc(10,1,5,'H'));
        char outcome = f.hit(5,6,'Z');
        f.print();

        System.out.println();

        Ocean c = new Ocean(10,10);
        c.emptyBoard();
        c.hit(5,6,outcome);
        c.print();
    }*/
}