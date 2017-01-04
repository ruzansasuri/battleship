import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by Ruzan on 11/6/2016.
 */
public class Controller implements ControllerI
{
    private static final String HOSTNAME = "129.21.102.253";
    private Fleet f;
    private Ocean o;
    Ship[] s;
    private int maxRow;
    private int maxColumn;
    static String fromServer;
    RMIClient rmic;
    RMIServer rmis;
    boolean done;
    String host;
    String oc;
    String pc;
    public Controller(String host, Ocean o, String pc, String oc)
    {
        maxColumn = o.getColumn() - 2;
        maxRow = o.getRow() - 2;
        f = new Fleet(maxRow,maxColumn);
        this.o = o;
        s = new Ship[4];
        System.out.println("You are trying to connect to: " + host);
        this.host = host;
        this.oc = oc;
        this.pc = pc;
    }
    public boolean connect(JProgressBar jp)
    {
        rmis = new RMIServer(this, pc);
        int i = 1;
        while(i < 100000)
        {
            jp.setValue(i % 1000);
            try
            {
                rmic = new RMIClient(host, oc);
            }
            catch (Exception e)
            {
                continue;
            }
            finally
            {
                i++;
            }
            break;
        }
        if(i == 100000)
        {
            JOptionPane.showMessageDialog(null, "Sorry! We could not connect.");
            System.exit(1);
        }
        return true;
    }
    public void readytoPlay() throws RemoteException
    {
        TurnAndVictory.myTurn = true;
    }
    public void doneWithPlaying()  throws RemoteException
    {
        TurnAndVictory.myTurn = false;
        rmic.callReady();
    }
    public boolean fillFunc (int xCoord, int yCoord, int len, char orien)
    {
        char[][] board = f.getBoard();
        boolean ok = true;
        int maxrow = board.length - 2;
        int maxcolumn = board[0].length - 2;
        //First, we check validity for horizontal position!
        if (xCoord < 1 || xCoord > maxrow || yCoord < 1 || yCoord > maxcolumn)
        {
            ok = false;
        }
        if ((orien != 'H' && orien != 'V'))
        {
            ok = false;
        }
        if (orien == 'H' && yCoord + len - 1 > maxcolumn)
        {
            ok = false;
        }
        if (orien == 'V' && xCoord + len - 1 > maxrow)
        {
            ok = false;
        }
        if (orien == 'H' && ok)
        {
            for (int i = xCoord - 1; i < xCoord + 2; i++)
            {
                for (int j = yCoord - 1; j < yCoord + len + 1; j++)
                {
                    if (i == xCoord - 1 || i == xCoord + 1 || j == yCoord - 1 || j == yCoord + len)
                    {
                        if (board[i][j] == 'S')
                        {
                            ok = false;
                        }
                    } else if (board[xCoord][j] != '~')
                    {
                        ok = false;
                    }
                }
            }

        }
        //Now, we check validity for vertical positions
        if (orien == 'V' && ok)
        {
            for (int j = yCoord - 1; j < yCoord + 2; j++)
            {
                for (int i = xCoord - 1; i < xCoord + len + 1; i++)
                {
                    if (j == yCoord - 1 || j == yCoord + 1 || i == xCoord - 1 || i == xCoord + len)
                    {
                        if (board[i][yCoord] == 'S')
                        {
                            ok = false;
                        }
                    } else if (board[i][yCoord] != '~')
                    {
                        ok = false;
                    }
                }
            }
        }
        return ok;
    }
    public void fillIt(Ship s1)
    {
        int xCoord = s1.getxCoord();
        int yCoord = s1.getyCoord();
        int len = s1.getLen();
        s[len - 2] = s1;
        char orien = s1.getOrien();
        char[][] board = f.getBoard();
        //Now, if position  is a valid horizontal position, we start filling that position:
        if (orien=='H')
        {
            for (int i=xCoord; i<xCoord+1;i++)
            {
                for (int j = yCoord; j < yCoord + len; j++)
                {
                    if (i==xCoord-1 || i==xCoord+1 || j==yCoord-1 || j==yCoord + len)
                    {
                        board[i][j] = 'B';
                    }
                    else
                    {
                        board[xCoord][j] = 'S';
                    }
                }
            }
        }
        //Now, if position  is a valid vertical position, we start filling that position:
        if (orien == 'V')
        {
            for (int j=yCoord; j<yCoord+1; j++)
            {
                for (int i = xCoord; i < xCoord + len; i++)
                {
                    if (j==yCoord-1 || j==yCoord+1 || i==xCoord-1 || i==xCoord+len)
                    {
                        board[i][j] = 'B';
                    }
                    else
                    {
                        board[i][yCoord] = 'S';
                    }
                }
            }
        }
    }
    public char fhit(int x, int y) throws RemoteException
    {
        if(x > o.getRow() && y > o.getColumn())
        {
            return 'N';
        }
        return rmic.hitFleet(x,y);
    }
    public char fcheck(int x, int y) throws RemoteException
    {
        char out = 'N';
        char board[][] = f.getBoard();
        int maxrow=board.length-2;
        int maxcolumn=board[0].length-2;
        if (1<=x &&  x<=maxrow && 1<=y && y<=maxcolumn)
        {
            if (board[x][y] == 'S')
            {
                board[x][y] = 'H';
                out = 'H';
            }
            else if(board[x][y] == '~' || board[x][y] == 'B')
            {
                board[x][y] = 'M';
                out = 'M';
            }
        }
        return out;
    }

    public String shit(int x, int y) throws RemoteException
    {
        return rmic.callSHit(x,y);
    }

    public String checkSHit(int x, int y) throws RemoteException
    {
        boolean des = false;
        String name = "";
        for(int iter = 0; iter < s.length; iter ++)
        {
            char orien = s[iter].getOrien();

            int xCoord = s[iter].getxCoord();
            int yCoord = s[iter].getyCoord();
            int len = s[iter].getLen();
            int left = s[iter].getLeft();
            name = s[iter].getName();
            if (orien == 'H' && x == xCoord && y >= yCoord && y < yCoord + len)
            {
                s[iter].decrLeft();
                left --;
                if (left == 0)
                {
                    des = true;
                }
                break;
            }
            else if (orien == 'V' && y == yCoord && x >= xCoord && x < xCoord + len)
            {
                s[iter].decrLeft();
                left --;
                if (left == 0)
                {
                    des = true;
                }
                break;
            }
        }
        if(des)
        {
            return name;
        }
        else
        {
            return "Nothing";
        }
    }
    public char ohit(int x, int y,char out)
    {
        char [][] board = o.getBoard();
        board[x][y] = out;
        return out;
    }
    public void loss() throws RemoteException
    {
        rmic.lose();
    }
    public int getMaxRow()
    {
        return maxRow;
    }
    public int getMaxColumn()
    {
        return maxColumn;
    }
    public boolean alreadyHit(int x, int y)
    {
        char [][]board = o.getBoard();
        if(board[x][y] != '~')
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public char[][] getFBoard()
    {
        return f.getBoard();
    }
    public char[][] getOBoard()
    {
        return o.getBoard();
    }
    public boolean vic() throws RemoteException
    {
        return rmic.vicCond();
    }
    public boolean checkVic() throws RemoteException
    {
        boolean vic = true;
        for(int i = 0; i < s.length; i++)
        {
            if(s[i].getLeft() > 0)
            {
                vic = false;
            }
        }
        return vic;
    }
    public void forceVic() throws RemoteException
    {
        rmic.forceVic();
    }
    public void forcedVic() throws RemoteException
    {
        TurnAndVictory.vic = 'S';
    }
    public void youLose() throws RemoteException
    {
        TurnAndVictory.vic = 'L';
    }
    public void createShip(int x, int y, int i, char orien, String name)
    {
        s[i - 2] = new Ship(x,y,i,orien,name);
    }
    public int noOfShips()
    {
        return s.length;
    }
    public static void main(String a[]) throws IOException
    {
        Game g = new Game();
        JFrame f = new JFrame("BATTLESHIP");
        int maxRow = 10;
        int maxColumn = 10;
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setMinimumSize(new Dimension(800, 500));
        g.createUI(f.getContentPane());
        f.pack();
        f.setVisible(true);
    }
}
