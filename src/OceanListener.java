import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

/**
 * Created by Ruzan on 11/20/2016.
 */
public class OceanListener extends MouseAdapter
{
    OceanScreen gs;
    int lastx;
    int lasty;
    Controller c;
    boolean awake;
    JLabel turn;
    public OceanListener(OceanScreen gs, Controller c, JLabel turn)
    {
        this.gs = gs;
        lastx = -1;
        lasty = -1;
        this.c = c;
        awake = false;
        this.turn = turn;
    }
    public void setAwake(boolean aw)
    {
        awake = aw;
    }
    public void mouseClicked(MouseEvent me)
    {
        if(!awake)
        {
            turn.setText("Opponent's turn.");
            return;
        }
        turn.setText("Your turn.");
        int x = me.getX();
        int y = me.getY();
        int width = gs.getWidth();
        int height = gs.getHeight();
        int cellWidth = width / Game.MAX_COLUMN;
        int cellHeight = height / Game.MAX_ROW;

        if (x >= gs.xOffset && y >= gs.yOffset)
        {
            int column = (x - gs.xOffset) / cellWidth;
            int row = (y - gs.yOffset) / cellHeight;
            if (column == Game.MAX_COLUMN && row == Game.MAX_ROW)
            {
                return;
            }
            if (column >= 0 && row >= 0 && column < Game.MAX_COLUMN && row < Game.MAX_ROW)
            {
                try
                {
                    char outcome = c.fhit(row + 1, column + 1);
                    if(outcome != 'N')
                    {
                        c.ohit(row + 1, column + 1, outcome);
                        if (outcome == 'H')
                        {
                            String name = c.shit(row + 1, column + 1);
                            if(c.vic())
                            {
                                TurnAndVictory.vic = 'W';
                            }
                        } else if (outcome == 'M')
                        {
                            c.doneWithPlaying();
                        }
                    }
                }
                catch(RemoteException re)
                {
                    re.printStackTrace();
                }
            }

        }
        gs.repaint();
    }
}
