package encrypt.luis200hr.encrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.xml.bind.DatatypeConverter;
public class Symmetric {
	public static final String AES = "AES"; //Algoritmo para encryptacion
	
	public static SecretKey createKey() throws NoSuchAlgorithmException {
		SecureRandom secureRand = new SecureRandom();
		
		KeyGenerator keygenerator = KeyGenerator.getInstance(AES);
		keygenerator.init(256, secureRand);
		SecretKey key = keygenerator.generateKey();
		return key;
	}
	
	//Driven
	public static void main(String[] args) throws NoSuchAlgorithmException {
		SecretKey SymmetricKey = createKey();
		System.out.println("--Output--");
		System.out.println("Symmetric key: " +
				DatatypeConverter.printHexBinary(SymmetricKey.getEncoded()));
	}
	
	//C528D143F3737E6747BB090A5FF5F4656F2A1DA476B705815D5E09011017BDA3
	

}
