package com.projeto.cliente;
import java.rmi.RemoteException;
import java.util.Scanner;

import utils.Seguranca;

import com.projeto.utils.Utils;

 
 public class Cliente {
 
	 
     public static void main(String[] args) {
    	 
   	    Seguranca.configurarSeguranca();
   	 
        String tipoCliente, enderecoServidor, threads;
        int numThreads;

        Scanner leitor = new Scanner(System.in);
        System.out.println(Utils.MSG_TIPO_CLIENTE);
        tipoCliente = leitor.nextLine();
        
        tipoCliente = Utils.validarTipoCliente(tipoCliente, leitor);
        
        System.out.println(Utils.MSG_TIPO_ENDERECO);
        enderecoServidor = leitor.nextLine();
        
        enderecoServidor = Utils.validarEnderecoServidor(enderecoServidor, leitor);
        
        System.out.println(Utils.MSG_NUM_THREADS);
        
        threads = leitor.nextLine();
        
        threads = Utils.validarNumThreads(threads, leitor);
        
        numThreads = Integer.parseInt(threads);
        try {
        	
	        if (tipoCliente.toLowerCase().equals("p")){	        	
	        	for (int i=0;i<numThreads;i++){
	        		Thread prodThread = new Thread(new Produtor(enderecoServidor), "Produtor "+i);
		        	prodThread.start();	
	        	}	        	
	        }
	        
	        if (tipoCliente.toLowerCase().equals("c")){	        	
	        	for (int i=0;i<numThreads;i++){
	        		Thread consThread = new Thread(new Consumidor(enderecoServidor), "Consumidor "+i);
	        		consThread.start();
	        	}	        	
	        }
        
		} catch (RemoteException e) {
			e.printStackTrace();
		}        
        
     }
     

     
     
     
     
}