package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
 
 
 public interface BufferInterface extends Remote{
	 
	 	public void produzir(Integer valor, String nomeThread, ClienteInterface cliente)  throws RemoteException, InterruptedException;
	 	public Integer consumir(String nomeThread, ClienteInterface cliente) throws RemoteException, InterruptedException;
	 	
 }