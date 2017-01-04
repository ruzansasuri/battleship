import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

/**
 * Created by Ruzan on 11/20/2016.
 */
public class ServerConnection extends Thread implements ActionListener
{
    FleetScreen f;
    Controller c;
    JButton res;
    JProgressBar jp;
    OceanScreen oc;
    JButton sb;
    TurnAndVictory tv;
    public ServerConnection(FleetScreen f, Controller c, JButton sb, JButton res,JProgressBar jp, OceanScreen oc, TurnAndVictory tv)
    {
        this.c = c;
        this.res = res;
        this.jp = jp;
        this.oc = oc;
        this.sb = sb;
        this.f = f;
        this.tv = tv;
    }
    public void run()
    {
        c.fillIt(f.s);
        if (!c.connect(jp))
        {
            System.err.println("Could not connect.");
            System.exit(1);
        }
        else
        {
            res.setEnabled(true);
            try
            {
                c.doneWithPlaying();
            }
            catch(RemoteException e)
            {
                e.printStackTrace();
            }
        }
        sb.setText("Connected.");
        //sb.setEnabled(true);
    }
    public void actionPerformed(ActionEvent a)
    {
        tv.start();
        f.wake(false);
        sb.setEnabled(false);
        sb.setText("Connecting...");
        start();
    }
}
