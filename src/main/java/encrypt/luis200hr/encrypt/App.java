package encrypt.luis200hr.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;

public class App {
	private static final String AES = "AES";
	
	private static final String AES_CIP = "AES/CBC/PKCS5PADDING";
	
	private static Scanner msg;
	
	public static SecretKey createAESKey() throws NoSuchAlgorithmException {
		SecureRandom securerandom = new SecureRandom();
		KeyGenerator keygenerator = KeyGenerator.getInstance(AES);
		keygenerator.init(256, securerandom);
		SecretKey key = keygenerator.generateKey();
		return key;
		
	}
	
	//Init Vector with any value
	public static byte[] Create_Initial_vector() {
		byte[] Initial_Vector = new byte[16];
		SecureRandom securerandom = new SecureRandom();
		securerandom.nextBytes(Initial_Vector);
		return Initial_Vector;
	}
	//Take text and with key convert to cypher text
	public static byte[] do_AESEncryption(
			String plainText,
			SecretKey secretkey,byte[] Initial_Vector) throws Exception{
		Cipher cipher = Cipher.getInstance(AES_CIP);
		
		//Parameter aspects of cipher block
		IvParameterSpec ivParameterSpec = new IvParameterSpec(Initial_Vector);
		//Cipher Init
		cipher.init(Cipher.ENCRYPT_MODE, secretkey, ivParameterSpec);
		
		return cipher.doFinal(plainText.getBytes());
	}
			
	//Method to perform Cipher text --> plain text
	public static String do_AESDescryption(byte[] cipherText, SecretKey secretkey,
			byte[] Initial_Vector) throws Exception {
		Cipher cipher = Cipher.getInstance(AES_CIP);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(Initial_Vector);
		//Decryp Mode
		cipher.init(Cipher.DECRYPT_MODE, secretkey, ivParameterSpec);
		byte[] result = cipher.doFinal(cipherText);
		return new String(result);
		
	}
	//Driver code
	public static void main(String[] args) throws Exception{
		SecretKey sk = createAESKey();
		System.out.println("*--Encryt mode--*");
		System.out.println("La llave simetrica es: " + 
		DatatypeConverter.printHexBinary(sk.getEncoded()));
		byte[] Initial_Vector = Create_Initial_vector();
		String plainText = "This is the mensaje " + "I want to Encryt";
		//Encryting the mensaje
		byte[] cipherText = do_AESEncryption(plainText, sk, Initial_Vector);
		System.out.println("Encryted message is: " + 
				DatatypeConverter.printHexBinary(cipherText));
		
		//Descrypting the message
		System.out.println("*--Descryted mode-*");
		String descryptedText = do_AESDescryption(cipherText, sk, Initial_Vector);
		System.out.println("Your original message is: " + descryptedText);
	}

}
