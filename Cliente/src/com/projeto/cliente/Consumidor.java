package com.projeto.cliente;

import interfaces.BufferInterface;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.Seguranca;


public class Consumidor implements Runnable {

	private static BufferInterface bufferRemoto;

    public BufferInterface getBufferRemoto() {
		return bufferRemoto;
	}

	public Consumidor(String enderecoServidor) {
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
          	valorRetirado = getBufferRemoto().consumir(nomeThread);
           	System.out.println("Retirado o valor "+ valorRetirado + " do Buffer pelo " +nomeThread+ " em "+ (System.currentTimeMillis()-inicioOperacao)+" milissegundos.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException e) {
		    e.printStackTrace();
		}

        
    }


}