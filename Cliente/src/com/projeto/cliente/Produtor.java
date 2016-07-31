package com.projeto.cliente;

import interfaces.BufferInterface;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.Seguranca;

import com.projeto.utils.Utils;

public class Produtor implements Runnable {

	private static BufferInterface bufferRemoto;

    public BufferInterface getBufferRemoto() {
		return bufferRemoto;
	}

	public Produtor(String enderecoServidor) {
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
          	getBufferRemoto().produzir(i, nomeThread);

           	System.out.println("Colocado o valor " + i + " no Buffer pelo " + nomeThread+ " em "+ (System.currentTimeMillis()-inicioOperacao)+" milissegundos.");
        } catch (InterruptedException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException e) {
		    e.printStackTrace();
		}

        
    }

    
    
    
}