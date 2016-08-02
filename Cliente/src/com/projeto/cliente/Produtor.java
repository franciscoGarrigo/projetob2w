package com.projeto.cliente;

import interfaces.BufferInterface;
import interfaces.ClienteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.Seguranca;

import com.projeto.utils.Utils;

public class Produtor extends UnicastRemoteObject implements Runnable, ClienteInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -626534836752512390L;
	private static BufferInterface bufferRemoto;

    public BufferInterface getBufferRemoto() {
		return bufferRemoto;
	}

	public Produtor(String enderecoServidor) throws RemoteException {
		super();
		if (bufferRemoto == null){
			bufferRemoto = Seguranca.conectarComServidor(bufferRemoto, enderecoServidor);
		}
    }

    @Override
    public void run() {
    	
    	Thread threadAtual = Thread.currentThread();
    	
    	String nomeThread = threadAtual.getName();
    	Integer i = Utils.gerarNumeroAleatorio();
    	long inicioOperacao = System.currentTimeMillis();
    	
        System.out.println(nomeThread +" produziu: " + i);
        try {
        	Thread.sleep(500);
        	
          	getBufferRemoto().produzir(i, nomeThread, this);

           	System.out.println("\nColocado o valor " + i + " no Buffer pelo " + nomeThread+ " em "+ (System.currentTimeMillis()-inicioOperacao)+" milissegundos.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException e) {
		    e.printStackTrace();
		}

        
    }

	@Override
	public void notificaCliente(String mensagem) throws RemoteException {
		System.out.println(mensagem);
		
	}


    
    
    
}