package com.projeto.utils;

import java.util.Scanner;

import utils.Seguranca;

public class Utils {
	public static final String MSG_NUMERO_PORTA = "Digite o n�mero da porta que o servidor estar� ouvindo";
	public static final String MSG_TAMANHO_BUFFER= "Digite o n�mero correspondente ao tamanho do buffer do servidor";
	
	private static final String PADRAO_PORTA_NUMERO_VALIDO ="^\\d{4,5}$";
	private static final String MSG_PORTA_INVALIDA="A porta digitada n�o � um n�mero v�lido\nou est� fora do intervalo permitido (1024-49151)\nFavor repetir a operacao.";
	private static final String PADRAO_TAMANHO_VALIDO ="^\\d+$";
	private static final String MSG_TAMANHO_INVALIDO="O tamanho digitado n�o � um n�mero v�lido. Favor repetir a opera��o";
	
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
