package utils;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

import interfaces.BufferInterface;

public class Seguranca {
	
	private static final String NOME_SERVIDOR ="BufferServer";
	private static final String RMI_CODEBASE ="java.rmi.server.codebase";
	private static final String JAVA_POLICY = "java.security.policy";

	private static final int MENOR_PORTA = 1024;
	private static final int MAIOR_PORTA = 49151;
	
	private static final String MSG_ERRO_CONEXAO ="Não foi possível estabelecer conexão com o servidor.\nO servidor possivelmente está desligado ou operando em um IP/Porta diferente.";
	public static final String MSG_ERRO_PORTA ="A porta escolhida já está em uso. Favor escolher outra.";



	public static void configurarSeguranca(){
		
		System.setProperty(RMI_CODEBASE,BufferInterface.class.getProtectionDomain()
				  .getCodeSource().getLocation().toString());
		  
		  System.setProperty(JAVA_POLICY,PolicyFileLocator.getLocationOfPolicyFile());
		  
		  if (System.getSecurityManager()==null){
			  System.setSecurityManager(new SecurityManager());
		  }	
		
	}
	
    public static BufferInterface conectarComServidor(BufferInterface msn, String enderecoServidor){
        try {
            msn = (BufferInterface)
            Naming.lookup(montarEnderecoParaCliente(enderecoServidor));
        } catch (IOException e) {
			System.err.println(MSG_ERRO_CONEXAO);
			System.exit(1);
        } catch (NotBoundException e) {
			e.printStackTrace();
		} 
        return msn;

    }
    
    public static boolean portaIntervaloValido(String porta){
    	Integer intPorta = Integer.parseInt(porta);
    	
    	return intPorta>=MENOR_PORTA || intPorta<=MAIOR_PORTA;
    }
    
    public static String montarEnderecoServidor(String porta){
    	StringBuilder enderecoRMI = new StringBuilder();
    	
    	enderecoRMI.append("rmi://localhost:")
    	           .append(porta)
    	           .append("/")
    			   .append(NOME_SERVIDOR);
    	
    	return enderecoRMI.toString();
    }
    
    
    private static String montarEnderecoParaCliente(String endereco){
    	
    	StringBuilder enderecoRMI = new StringBuilder();
    	
    	enderecoRMI.append("rmi://")
    			   .append(endereco)
    			   .append("/")
    			   .append(NOME_SERVIDOR);
    	
    	return enderecoRMI.toString();
    }
	
	
}
