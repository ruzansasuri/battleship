import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Thread.sleep;

/**
 * Created by Ruzan on 11/16/2016.
 */
public class GameListener extends MouseAdapter
{
    FleetScreen gs;
    int lastx;
    int lasty;
    boolean awake;
    JButton sb;
    public GameListener(FleetScreen gs, JButton sb)
    {
        this.gs = gs;
        lastx = -1;
        lasty = -1;
        awake = true;
        this.sb = sb;
    }
    public void setAwake(boolean aw)
    {
        awake = aw;
    }
    public void mouseClicked(MouseEvent me)
    {
        if(!awake)
        {
            return;
        }
        //Point p = me.getPoint();
        int x = me.getX();
        int y = me.getY();
        if(lastx == x && lasty == y)
        {
            if(gs.orient == 'H')
            {
                gs.orient = 'V';
            }
            else
            {
                gs.orient = 'H';
            }
        }
        else
        {
            lastx = x;
            lasty = y;
        }
        int width = gs.getWidth();
        int height = gs.getHeight();
        int cellWidth = width / gs.columnCount;
        int cellHeight = height / gs.rowCount;

        if (x >= gs.xOffset && y >= gs.yOffset)
        {
            int column = (x - gs.xOffset) / cellWidth;
            int row = (y - gs.yOffset) / cellHeight;
            if (column == gs.columnCount && row == gs.rowCount)
            {
                return;
            }
            if (column >= 0 && row >= 0 && column < gs.columnCount && row < gs.rowCount)
            {
                //if((gs.orient == 'H' && column != 10) || (gs.orient == 'V' && row != 10))
                //{
                gs.selectCell(column, row);
                //}
            }
            if(ShipChanger.done == 3)
            {
                sb.setEnabled(true);
            }

        }
        gs.repaint();
    }
}
