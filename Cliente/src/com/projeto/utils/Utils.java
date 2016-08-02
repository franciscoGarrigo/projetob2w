package com.projeto.utils;

import java.util.Random;
import java.util.Scanner;

import utils.Seguranca;

public class Utils {
	public static final String MSG_TIPO_CLIENTE = "Digite o tipo de cliente a ser executado:\nP-Produtor\nC-Consumidor";
	public static final String MSG_TIPO_ENDERECO= "Digite o endereço do servidor remoto. O formato deve ser IP:PORTA";
	public static final String MSG_NUM_THREADS= "Digite o número de threads cliente desejadas...";
	
	private static final int QTD_NUM_ALEATORIOS =256;
	private static final String PADRAO_ENDERECO_VALIDO ="^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{4,5}$";
	private static final String PADRAO_TIPO_CLIENTE_VALIDO ="^[pP|cC]$";
	private static final String PADRAO_NUMERO_VALIDO ="^\\d+$";
	private static final String MSG_ENDERECO_INVALIDO = "Endereço inválido. Favor verificar:\n1- Padrão de endereço(IP:PORTA)\n2- Digitos válidos para IP e PORTA\n3- Porta dentro do intervalo permitido (1024-49151).\nFavor repetir operação.";
	private static final String MSG_TIPO_CLIENTE_INVALIDO = "Tipo de cliente inválido.\n O padrão é: \n P-Produtor\n C-Consumidor\nFavor repetir operação.";
	private static final String MSG_NUM_THREADS_INVALIDO= "O número digitado nao é válido. Favor repetir a operação.";

       
    public static String validarEnderecoServidor(String enderecoServidor, Scanner leitor){
    	
    	boolean enderecoValido = validarEnderecoEPorta(enderecoServidor);
    	if(!enderecoValido){
    		do{
        		System.err.println(MSG_ENDERECO_INVALIDO);
                enderecoServidor = leitor.nextLine();
                enderecoValido = validarEnderecoEPorta(enderecoServidor);
        	}
        	while(!enderecoValido);
    		
    	};
    	
    	return enderecoServidor;
    }
    
    private static boolean validarEnderecoEPorta(String enderecoServidor){
    	
    	boolean enderecoValido = enderecoServidor.matches(PADRAO_ENDERECO_VALIDO);
    	
    	if (!enderecoValido){
    		return enderecoValido;
    	}    	
    	
    	String[] partes = enderecoServidor.split(":");
    	
    	return Seguranca.portaIntervaloValido(partes[1]);
    	
    }
    
    public static String validarTipoCliente(String tipoCliente, Scanner leitor){
    	
    	boolean tipoValido = tipoCliente.matches(PADRAO_TIPO_CLIENTE_VALIDO);
    	if(!tipoValido){
    		do{
        		System.err.println(MSG_TIPO_CLIENTE_INVALIDO);
        		tipoCliente = leitor.nextLine();
                tipoValido = tipoCliente.matches(PADRAO_TIPO_CLIENTE_VALIDO);
        	}
        	while(!tipoValido);
    		
    	};
    	
    	return tipoCliente;
    }
    
    public static String validarNumThreads(String numThreads, Scanner leitor){
    	
    	boolean tamanhoValido = numThreads.matches(PADRAO_NUMERO_VALIDO);
    	if(!tamanhoValido){
    		do{
        		System.err.println(MSG_NUM_THREADS_INVALIDO);
        		numThreads = leitor.nextLine();
                tamanhoValido = numThreads.matches(PADRAO_NUMERO_VALIDO);
        	}
        	while(!tamanhoValido);
    		
    	};
    	
    	return numThreads;
    }
   
    
    	  
    public static Integer gerarNumeroAleatorio(){
    	    
    	 Random gerador = new Random();
    	 return gerador.nextInt(QTD_NUM_ALEATORIOS);

    }	

}
