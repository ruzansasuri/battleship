import javax.swing.*;
import java.rmi.RemoteException;

/**
 * Created by Ruzan on 11/21/2016.
 */
public class TurnAndVictory extends Thread
{
    Controller c;
    OceanScreen os;
    static boolean myTurn;
    static char vic;
    public TurnAndVictory(Controller c, OceanScreen os)
    {
        this.c = c;
        this.os = os;
        myTurn = false;
        vic = 'N';
    }
    public void run()
    {
        while(true)
        {
            os.wake(myTurn);
            if(vic == 'W')
            {
                try
                {
                    c.loss();
                }
                catch(RemoteException re)
                {
                    re.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "CONGRATULATONS!!! YOU HAVE WON!!!");
                System.exit(0);
            }
            else if(vic == 'L')
            {
                JOptionPane.showMessageDialog(null, "Sorry! You lose...");
                System.exit(0);
            }
            else if(vic == 'F')
            {
                try
                {
                    c.forceVic();
                }
                catch(RemoteException re)
                {
                    re.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Looks like you gave up :(");
                System.exit(0);
            }
            else if (vic == 'S')
            {
                JOptionPane.showMessageDialog(null, "Looks like your opponent gave up...CONGRATULATONS!!! YOU HAVE WON!!!");
                System.exit(0);
            }
        }
    }
}
