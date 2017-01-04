import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Ruzan on 11/11/2016.
 */
public interface ControllerI extends Remote
{
    void readytoPlay()throws RemoteException;
    char fcheck(int x, int y)throws RemoteException;
    String checkSHit(int x, int y)throws RemoteException;
    boolean checkVic()throws RemoteException;
    void youLose()throws RemoteException;
    void forcedVic()throws RemoteException;
}
