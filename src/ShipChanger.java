import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ruzan on 11/20/2016.
 */
public class ShipChanger implements ActionListener
{
    int len;
    FleetScreen gs;
    Controller c;
    static int done;
    JButton sb;
    public ShipChanger(int len, FleetScreen gs, Controller c, JButton sb)
    {
        this.len = len;
        this.gs = gs;
        this.c = c;
        done = 0;
        this.sb = sb;
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(gs.s != null)
        {
            gs.setLen(len);
            Game.jr[gs.s.getLen() - 2].setEnabled(false);
            c.fillIt(gs.s);
            gs.s = null;
            done++;
        }
    }
}
