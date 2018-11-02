package library;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class EncryptLibrary {

	private final static String ALGORITMO = "AES/CBC/PKCS5Padding";
	private final String CODIFICACION = "UTF-8";

	private String encrypt(String plaintext, String keyString) {
		try {
			String key = toHex(keyString);
			byte[] raw = DatatypeConverter.parseHexBinary(key);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance(ALGORITMO);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] cipherText = cipher.doFinal(plaintext.getBytes(CODIFICACION));
			byte[] iv = cipher.getIV();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			outputStream.write(iv);
			outputStream.write(cipherText);
			byte[] finalData = outputStream.toByteArray();
			String encodedFinalData = DatatypeConverter.printBase64Binary(finalData);
			return encodedFinalData;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private String decrypt(String encodedInitialData, String keyString) {
		try {
			byte[] encryptedData = DatatypeConverter.parseBase64Binary(encodedInitialData);
			String key = toHex(keyString);
			byte[] raw = DatatypeConverter.parseHexBinary(key);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance(ALGORITMO);
			byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
			byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
			IvParameterSpec iv_specs = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv_specs);
			byte[] plainTextBytes = cipher.doFinal(cipherText);
			String plainText = new String(plainTextBytes);
			return plainText;
		} catch (Exception e) {
//			System.out.println(e.getMessage());
		}
		return null;
	}

	public String hash(String text) {
		try {
			return this.encrypt(text, "A/dsF0awq-szszfF");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkHash(String text, String hash) {
		String plainText = null;
		try {
			plainText = this.decrypt(hash, "A/dsF0awq-szszfF");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (text == plainText)
			return true;
		return false;
	}

	private String toHex(String arg) {
		return String.format("%x", new BigInteger(1, arg.getBytes(/* YOUR_CHARSET? */)));
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException {
		EncryptLibrary en = new EncryptLibrary();
		String check = en.hash("hello word 123");
		System.out.println(check);
		if (en.checkHash("hi") == true) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

	}
}
