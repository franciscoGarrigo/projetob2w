package com.projeto.servidor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import utils.Seguranca;

import com.projeto.servico.BufferImpl;
import com.projeto.utils.Utils;
  
  
  public class Servidor {
     public static void main(String[] args) {
         try {
        	 
        	 String porta, tamanhoBuffer;
        	  
         	 Seguranca.configurarSeguranca();
         	  
         	 Scanner leitor = new Scanner(System.in);
             System.out.println(Utils.MSG_NUMERO_PORTA);
             porta = leitor.nextLine();
             
             Utils.validarPorta(porta, leitor);
             
             System.out.println(Utils.MSG_TAMANHO_BUFFER);
             tamanhoBuffer = leitor.nextLine();
             
             Utils.validarTamanhoBuffer(tamanhoBuffer, leitor);
        	  
        	 LocateRegistry.createRegistry(Integer.parseInt(porta));
             BufferImpl servidor = new BufferImpl(Integer.parseInt(tamanhoBuffer));
  
             Naming.rebind(Seguranca.montarEnderecoServidor(porta), servidor);
             System.out.println("Servidor no ar...");
              
             

         } catch (RemoteException e) {
              System.err.println(Seguranca.MSG_ERRO_PORTA);
         } catch (MalformedURLException e) {
              e.printStackTrace();
		 }
  
    }
     
  }