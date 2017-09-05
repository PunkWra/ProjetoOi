package classes.mltech;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	
	static MessageDigest md ;
	
	
	public static String retornaSenha(String senha){
		
		try{
			md = MessageDigest.getInstance("MD5");
			md.update(senha.getBytes(),0,senha.length());			
			
		}catch(NoSuchAlgorithmException e){
			
		}	
		
		return (new BigInteger(1, md.digest()).toString(16));
	}
	
	
}
