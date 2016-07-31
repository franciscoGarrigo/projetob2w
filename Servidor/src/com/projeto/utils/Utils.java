package com.projeto.utils;

import java.util.Scanner;

import utils.Seguranca;

public class Utils {
	public static final String MSG_NUMERO_PORTA = "Digite o numero da porta que o servidor estara ouvindo";
	public static final String MSG_TAMANHO_BUFFER= "Digite o numero correspondente ao tamanho do buffer do servidor";
	
	private static final String PADRAO_PORTA_NUMERO_VALIDO ="^\\d{4,5}$";
	private static final String MSG_PORTA_INVALIDA="A porta digitada nao e um numero valido\nou esta fora do intervalo permitido (1024-49151)\nFavor repetir a operacao.";
	private static final String PADRAO_TAMANHO_VALIDO ="^\\d+$";
	private static final String MSG_TAMANHO_INVALIDO="O tamanho digitado nao e um numero valido. Favor repetir a operacao";
	
    public static void validarPorta(String porta, Scanner leitor){
    	
    	boolean portaValida = porta.matches(PADRAO_PORTA_NUMERO_VALIDO)&& Seguranca.portaIntervaloValido(porta);
    	if(!portaValida){
    		do{
        		System.err.println(MSG_PORTA_INVALIDA);
        		porta = leitor.nextLine();
                portaValida = porta.matches(PADRAO_PORTA_NUMERO_VALIDO)&& Seguranca.portaIntervaloValido(porta);
        	}
        	while(!portaValida);
    		
    	};
    	   	
    	
    }
    

    
    public static void validarTamanhoBuffer(String tamanhoBuffer, Scanner leitor){
    	
    	boolean tamanhoValido = tamanhoBuffer.matches(PADRAO_TAMANHO_VALIDO);
    	if(!tamanhoValido){
    		do{
        		System.err.println(MSG_TAMANHO_INVALIDO);
        		tamanhoBuffer = leitor.nextLine();
                tamanhoValido = tamanhoBuffer.matches(PADRAO_TAMANHO_VALIDO);
        	}
        	while(!tamanhoValido);
    		
    	};
    	
    }

}
