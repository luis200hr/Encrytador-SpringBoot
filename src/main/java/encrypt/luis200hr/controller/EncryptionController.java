package encrypt.luis200hr.controller;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import encrypt.luis200hr.encrypt.App;

@Controller
@RequestMapping("/home")
public class EncryptionController {
	
	@GetMapping
    public String home(){
        return "home";
    }
    @PostMapping("/encrypt")
    public String encrypt(Model model, @RequestParam String plaintext) {
        try {
        	SecretKey secretKey = App.createAESKey();
            byte[] initialVector = App.Create_Initial_vector();
            byte[] ciphertext = App.do_AESEncryption(plaintext, secretKey, initialVector);
            String ciphertextHex = "Key: "+ 
            		DatatypeConverter.printHexBinary(secretKey.getEncoded()) +
            		" "
            		+ "Mensaje: " +
            		DatatypeConverter.printHexBinary(ciphertext);
            model.addAttribute("ciphertext", ciphertextHex);
            return "home";
        } catch (Exception e) {
        	System.out.println("Entro en el error");
            e.printStackTrace();
            return "Error occurred during encryption";
        }
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String ciphertext) {
        try {
            SecretKey secretKey = App.createAESKey();
            byte[] initialVector = App.Create_Initial_vector();
            byte[] ciphertextBytes = DatatypeConverter.parseHexBinary(ciphertext);
            String plaintext = App.do_AESDescryption(ciphertextBytes, secretKey, initialVector);
            return plaintext;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during decryption";
        }
    }


}







