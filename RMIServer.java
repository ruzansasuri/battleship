import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Ruzan on 11/11/2016.
 */
public class RMIServer
{
    public RMIServer(Controller cont, String pc)
    {
        try
        {
            ControllerI stub = (ControllerI) UnicastRemoteObject.exportObject(cont, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            System.out.println(stub.getClass());
            registry.bind(pc, stub);

            System.err.println("Server ready");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Something went wrong with the server. Please make sure rmiregistry is running.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
