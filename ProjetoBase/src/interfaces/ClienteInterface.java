package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClienteInterface extends Remote{
	
	public void notificaCliente(String mensagem) throws RemoteException;

}
