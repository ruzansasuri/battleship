import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Ruzan on 11/10/2016.
 */
public class RMIClient
{
    Registry r;
    ControllerI stub;
    public RMIClient(String host, String oc) throws Exception
    {
        r = LocateRegistry.getRegistry(host);
        stub = (ControllerI) r.lookup(oc);
    }

    public void callReady() throws RemoteException
    {
        stub.readytoPlay();
    }
    public char hitFleet(int x, int y) throws RemoteException
    {
        return stub.fcheck(x,y);
    }
    public String callSHit(int x, int y) throws RemoteException
    {
        return stub.checkSHit(x,y);
    }
    public void lose() throws RemoteException
    {
        stub.youLose();
    }
    public void forceVic() throws RemoteException
    {
        stub.forcedVic();
    }
    public boolean vicCond() throws RemoteException
    {
        return stub.checkVic();
    }
}
