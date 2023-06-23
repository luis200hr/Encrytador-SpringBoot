package encrypt.luis200hr.controller;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
	private SecretKey secretKey;
	private byte[] initialVector;

	@GetMapping
    public String home(){
        return "home";
    }
	@GetMapping("/encry")
	public String encry() {
	    return "encry";
	}
	@GetMapping("/desencry")
	public String desencry() {
	    return "desencry";
	}
	@GetMapping("/about")
	public String about() {
	    return "about";
	}
	@GetMapping("/error")
	public String error() {
	    return "errorView";
	}
    @PostMapping("/encrypt")
    public String encrypt(Model model, @RequestParam String plaintext) {
        try {
        	secretKey = App.createAESKey();
            initialVector = App.Create_Initial_vector();
            byte[] ciphertext = App.do_AESEncryption(plaintext, secretKey, initialVector);
            String result = "Key: "+ DatatypeConverter.printHexBinary(secretKey.getEncoded());
            String ciphertextHex = "Mensaje: " + DatatypeConverter.printHexBinary(ciphertext);
            model.addAttribute("ciphertext", ciphertextHex);
            model.addAttribute("result", result);
            return "encry";
        } catch (Exception e) {
        	System.out.println("Entro en el error");
            e.printStackTrace();
            return  "errorView";
        }
    }

    @PostMapping("/decrypt")
    public String decrypt(Model model, @RequestParam  String mensaje) {
        try {
        	byte[] ciphertextBytes = DatatypeConverter.parseHexBinary(mensaje);
            String result = App.do_AESDescryption(ciphertextBytes, secretKey, initialVector);
            model.addAttribute("ciphertext", result);
             return "desencry";
        } catch (Exception e) {
            e.printStackTrace();
            return "errorView";
        }
    }


}







