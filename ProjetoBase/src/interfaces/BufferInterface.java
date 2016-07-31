package interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
 
 
 public interface BufferInterface extends Remote{
	 
	 	public void produzir(Integer valor, String nomeThread)  throws RemoteException, InterruptedException;
	 	public Integer consumir(String nomeThread) throws RemoteException, InterruptedException;
	 	
 }