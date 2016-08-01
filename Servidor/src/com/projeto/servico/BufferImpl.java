package com.projeto.servico;
import interfaces.BufferInterface;
import interfaces.ClienteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import utils.Seguranca;
  
  
  public class BufferImpl extends UnicastRemoteObject implements BufferInterface{

	private static final long serialVersionUID = 258720965157413927L;
	
	private static final String NOME_SERVIDOR = "Servidor: " + Seguranca.NOME_SERVIDOR +": ";

	private Vector<Integer> buffer;

	private int tamanhoBuffer;
	  
	      public BufferImpl(int tamanhoBuffer) throws RemoteException {
	          super();
	    	  this.buffer = new Vector<Integer>(tamanhoBuffer);
	    	  this.tamanhoBuffer = tamanhoBuffer;
	      }
	  
	      @Override
	      public void produzir(Integer valor, String nomeThread, ClienteInterface cliente) throws RemoteException, InterruptedException {
	          while (buffer.size() == tamanhoBuffer) {
	              synchronized (buffer) {
	                  System.out.println(" - Buffer está cheio " + nomeThread
	                                      + " está esperando espaco no buffer! O tamanho da fila é: " + buffer.size());
                      cliente.notificaCliente(NOME_SERVIDOR+nomeThread+" tentou colocar item no Buffer cheio!");
	                  buffer.wait();
	              }
	          }

	          synchronized (buffer) {
	        	  buffer.add(valor);
		          System.out.println(" - " + nomeThread + " adicionou "+ valor+ " ao buffer.");
		          System.out.println(" - Buffer depois que "+nomeThread+" inseriu seu valor: "+buffer);
	              buffer.notifyAll();
	          }
	    	  
	      }
	      
		  
	      @Override
	      public Integer consumir(String nomeThread, ClienteInterface cliente)
	              throws RemoteException, InterruptedException {

	          while (buffer.isEmpty()) {
	              synchronized (buffer) {
	                  System.out.println(" - Buffer está vazio " + nomeThread
	                                      + " está esperando... Tamanho da fila é: " + buffer.size());
                      cliente.notificaCliente(NOME_SERVIDOR+nomeThread+" tentou consumir item de um Buffer vazio!");
	                  buffer.wait();
	              }
	          }

	          synchronized (buffer) {
	        	  Integer itemRemovido = buffer.remove(0);
		          System.out.println(" - " + nomeThread + " removeu "+ itemRemovido+ " do buffer.");
		          System.out.println(" - Buffer depois que "+nomeThread+" removeu um valor: "+buffer);
	        	  buffer.notifyAll();
	              return itemRemovido;
	          }                 
	          
	          
	          
	      }

  
  }