/**
 * Fleet.java
 * @author: Ruzan Sasuri            rps7183@g.rit.edu
 * @author: Akash Venkatachalam     av2833@g.rit.edu
 * @author: Ghodratollah Aalipour   ga5481@g.rit.edu
 * Id: $ Fleet.java v1.0, 2016/11/07$
 * Revision: First Revision
 */

public class Fleet
{
    protected int maxRow;
    protected int maxColumn;
    protected char board[][];
/**
*The constructor which initializes the position of SHIPS in the ocean
*/
    public Fleet(int r, int c)
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
    public char[][] getBoard()
    {
        return board;
    }
/**
     * The nest method simply is the toString methd of our function
     * @return The string "Fleet" as the print method of ourtr object
     */
    public String toString()
    {
        return "Fleet";
    }
/**
*Method to get the row size
*/
    public int getRow()
    {
        return maxRow;
    }
/**
*Method to get the column size
*/
    public int getColumn()
    {
        return maxColumn;
    }
    /*public static void main (String[] ar)
    {
        Fleet oc = new Fleet(10,10);
        oc.emptyBoard();
        System.out.println(oc.fillFunc(1,1,2,'V'));
        System.out.println(oc.fillFunc(5,6,3,'H'));
        System.out.println(oc.fillFunc(1,6,4,'H'));
        System.out.println(oc.fillFunc(10,1,5,'H'));
        char outcome = oc.hit(5,6,'Z');
        System.out.println("Outcome = " + outcome);
        oc.print();
    }*/

}
