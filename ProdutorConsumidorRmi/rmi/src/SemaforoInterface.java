import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SemaforoInterface extends Remote{
	void produce(int value) throws RemoteException;
	int consume() throws RemoteException;
}
