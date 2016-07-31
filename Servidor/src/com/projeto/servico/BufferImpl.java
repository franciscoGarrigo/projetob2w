package com.projeto.servico;
import interfaces.BufferInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
  
  
  public class BufferImpl extends UnicastRemoteObject implements BufferInterface{

	private static final long serialVersionUID = 258720965157413927L;

	private Vector<Integer> buffer;

	private int tamanhoBuffer;
	  
	      public BufferImpl(int tamanhoBuffer) throws RemoteException {
	          super();
	    	  this.buffer = new Vector<Integer>(tamanhoBuffer);
	    	  this.tamanhoBuffer = tamanhoBuffer;
	      }
	  
	      @Override
	      public Integer consumir(String nomeThread)
	              throws RemoteException, InterruptedException {

	          while (buffer.isEmpty()) {
	              synchronized (buffer) {
	                  System.out.println("Servidor: - Buffer esta vazio " + nomeThread
	                                      + " esta esperando. Tamanho da fila e: " + buffer.size());

	                  buffer.wait();
	              }
	          }

	          synchronized (buffer) {
	        	  Integer itemRemovido = buffer.remove(0);
		          System.out.println("Servidor: - " + nomeThread + " removeu "+ itemRemovido+ " do buffer.");
		          System.out.println("Servidor: - Buffer depois que "+nomeThread+" removeu um valor: "+buffer);
	        	  buffer.notifyAll();
	              return itemRemovido;
	          }                 
	          
	          
	          
	      }
	  
	      @Override
	      public void produzir(Integer valor, String nomeThread) throws RemoteException, InterruptedException {
	    	  
	          while (buffer.size() == tamanhoBuffer) {
	              synchronized (buffer) {
	                  System.out.println("Servidor: - Buffer esta cheio " + nomeThread
	                                      + " esta esperando espaco no buffer! O tamanho da fila e: " + buffer.size());

	                  buffer.wait();
	              }
	          }

	          synchronized (buffer) {
	        	  buffer.add(valor);
		          System.out.println("Servidor: - " + nomeThread + " adicionou "+ valor+ " ao buffer.");
		          System.out.println("Servidor: - Buffer depois que "+nomeThread+" inseriu seu valor: "+buffer);
	              buffer.notifyAll();
	          }
	    	  
	      }
  
  }