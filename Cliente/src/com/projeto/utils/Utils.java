package com.projeto.utils;

import java.util.Random;
import java.util.Scanner;

import utils.Seguranca;

public class Utils {
	public static final String MSG_TIPO_CLIENTE = "Digite o Tipo de Cliente a ser executado:\nP-Produtor\nC-Consumidor";
	public static final String MSG_TIPO_ENDERECO= "Digite o endereco do servidor remoto. O formato deve ser IP:PORTA";
	public static final String MSG_NUM_THREADS= "Digite o numero de threads cliente desejadas...";
	
	private static final int QTD_NUM_ALEATORIOS =256;
	private static final String PADRAO_ENDERECO_VALIDO ="^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{4,5}$";
	private static final String PADRAO_TIPO_CLIENTE_VALIDO ="^[pP|cC]$";
	private static final String PADRAO_NUMERO_VALIDO ="^\\d+$";
	private static final String MSG_ENDERECO_INVALIDO = "Endereco invalido. Favor verificar:\n1- Padrao de endereco(IP:PORTA)\n2- Digitos validos para IP e PORTA\n3- Porta dentro do intervalo permitido (1024-49151).\nFavor repetir operacao.";
	private static final String MSG_TIPO_CLIENTE_INVALIDO = "Tipo de cliente invalido.\n O padrao e: \n P-Produtor\n C-Consumidor\nFavor repetir operacao.";
	private static final String MSG_NUM_THREADS_INVALIDO= "O numero digitado nao e valido. Favor repetir a operacao.";

       
    public static void validarEnderecoServidor(String enderecoServidor, Scanner leitor){
    	
    	boolean enderecoValido = validarEnderecoEPorta(enderecoServidor);
    	if(!enderecoValido){
    		do{
        		System.err.println(MSG_ENDERECO_INVALIDO);
                enderecoServidor = leitor.nextLine();
                enderecoValido = validarEnderecoEPorta(enderecoServidor);
        	}
        	while(!enderecoValido);
    		
    	};
    	
    }
    
    private static boolean validarEnderecoEPorta(String enderecoServidor){
    	
    	boolean enderecoValido = enderecoServidor.matches(PADRAO_ENDERECO_VALIDO);
    	
    	if (!enderecoValido){
    		return enderecoValido;
    	}    	
    	
    	String[] partes = enderecoServidor.split(":");
    	
    	return Seguranca.portaIntervaloValido(partes[1]);
    	
    }
    
    public static void validarTipoCliente(String tipoCliente, Scanner leitor){
    	
    	boolean tipoValido = tipoCliente.matches(PADRAO_TIPO_CLIENTE_VALIDO);
    	if(!tipoValido){
    		do{
        		System.err.println(MSG_TIPO_CLIENTE_INVALIDO);
        		tipoCliente = leitor.nextLine();
                tipoValido = tipoCliente.matches(PADRAO_TIPO_CLIENTE_VALIDO);
        	}
        	while(!tipoValido);
    		
    	};
    	
    }
    
    public static void validarNumThreads(String numThreads, Scanner leitor){
    	
    	boolean tamanhoValido = numThreads.matches(PADRAO_NUMERO_VALIDO);
    	if(!tamanhoValido){
    		do{
        		System.err.println(MSG_NUM_THREADS_INVALIDO);
        		numThreads = leitor.nextLine();
                tamanhoValido = numThreads.matches(PADRAO_NUMERO_VALIDO);
        	}
        	while(!tamanhoValido);
    		
    	};
    	
    }
   
    
    	  
    public static Integer gerarNumeroAleatorio(){
    	    
    	 Random gerador = new Random();
    	 return gerador.nextInt(QTD_NUM_ALEATORIOS);

    }	

}
