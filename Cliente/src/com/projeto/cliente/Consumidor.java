package com.projeto.cliente;

import interfaces.BufferInterface;
import interfaces.ClienteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.Seguranca;


public class Consumidor extends UnicastRemoteObject implements Runnable, ClienteInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2219089504546444303L;
	private static BufferInterface bufferRemoto;

    public BufferInterface getBufferRemoto() {
		return bufferRemoto;
	}

	public Consumidor(String enderecoServidor) throws RemoteException{
		if (bufferRemoto == null){
			bufferRemoto = Seguranca.conectarComServidor(bufferRemoto, enderecoServidor);
		}
    }

    @Override
    public void run() {
    	
    	Thread threadAtual = Thread.currentThread();    	
    	String nomeThread = threadAtual.getName();
    	long inicioOperacao = System.currentTimeMillis();
    	Integer valorRetirado;
    	
        try {
        	Thread.sleep(500);
          	valorRetirado = getBufferRemoto().consumir(nomeThread, this);
           	System.out.println("Retirado o valor "+ valorRetirado + " do Buffer pelo " +nomeThread+ " em "+ (System.currentTimeMillis()-inicioOperacao)+" milissegundos.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException e) {
		    e.printStackTrace();
		}

        
    }

	@Override
	public void notificaCliente(String mensagem) throws RemoteException {
		System.out.println(mensagem);
		
	}


}