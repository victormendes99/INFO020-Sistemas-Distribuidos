import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.net.*;
 
public class RmiServer extends java.rmi.server.UnicastRemoteObject
implements SemaforoInterface
{
    int      thisPort;
    String   thisAddress;
    Registry registry;    // rmi registry for lookup the remote objects.
    
    ArrayList buffer;
    Semaphore mutex;
    Semaphore items;
 
    public RmiServer() throws RemoteException
    {
    	buffer = new ArrayList(); 
        mutex = new Semaphore(1);
        items = new Semaphore();
    	
        try{
            // get the address of this host.
            thisAddress= (InetAddress.getLocalHost()).toString();
        }
        catch(Exception e){
            throw new RemoteException("can't get inet address.");
        }
        thisPort=3234;  // this port(registry’s port)
        System.out.println("this address=" + thisAddress + ",port=" + thisPort + "\n");
        try{
        // create the registry and bind the name and object.
        registry = LocateRegistry.createRegistry( thisPort );
            registry.rebind("rmiServer", this);
        }
        catch(RemoteException e){
        	throw e;
        }
    }
   
    static public void main(String args[])
    {
        try{
        	RmiServer s = new RmiServer();
        }
        catch (Exception e) {
           e.printStackTrace();
           System.exit(1);
        }
    }

	@Override
	public void produce(int value) throws RemoteException {
       	this.mutex.down();
       	this.buffer.add(value);
       	this.mutex.up();
       	this.items.up();
       	
       	System.out.println("produtor: producing item "+ value);
	}

	@Override
	public int consume() throws RemoteException {
		this.items.down();
		this.mutex.down();
		
       	int item = (Integer) this.buffer.get(0);
       	this.buffer.remove(0);
       	this.mutex.up();
       	
       	System.out.println("consumer: consuming item "+item);
		return item;
	}
}