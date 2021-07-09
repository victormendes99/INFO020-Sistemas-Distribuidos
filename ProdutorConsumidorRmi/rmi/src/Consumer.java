import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

public class Consumer
{
    static public void main(String args[])
    {
       SemaforoInterface rmiServer;
       Registry registry;
       String serverAddress="192.168.0.14";
       String serverPort="3234";
       
       try{
           // get the “registry” 
           registry=LocateRegistry.getRegistry(
               serverAddress,
               (new Integer(serverPort)).intValue()
           );
           
           // look up the remote object
           rmiServer = (SemaforoInterface)(registry.lookup("rmiServer"));
           
           // call the remote method
           while (true) {
		       int item = rmiServer.consume();
		       
		       System.out.println("consumer: consuming item "+item);
		       for (int i =0;i<10000;i++);
		   }
       }
       catch(RemoteException e){
           e.printStackTrace();
       }
       catch(NotBoundException e){
           e.printStackTrace();
       }
    }
}
