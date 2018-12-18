package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {

	public static void main(String[] args) {
		System.out.println(CryptoUtils.sha256("Hoang"));
		System.out.println(CryptoUtils.md5("Hoang"));
		System.out.println(CryptoUtils.sha256("Nguyen"));
		System.out.println(CryptoUtils.md5("Nguyen"));
		System.out.println(CryptoUtils.sha256("Viet"));
		System.out.println(CryptoUtils.md5("Viet"));
		System.out.println(CryptoUtils.hmacDigest("VietHoang", "keybimat", "HmacSHA256"));
		System.out.println(CryptoUtils.hmacDigest("VietHoang", "keybimat2", "HmacSHA256"));
		System.out.println(CryptoUtils.sha256("VietHoang"));
	}

	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String md5(String base) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(base.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String hmacDigest(String msg, String keyString, String algo) {
		String digest = null;
		try {
			SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
			Mac mac = Mac.getInstance(algo);
			mac.init(key);

			byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

			StringBuffer hash = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(0xFF & bytes[i]);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			digest = hash.toString();
		} catch (Exception e) {
		}
		return digest;
	}
	
	public static String hmacSHA256(String msg) {
		return hmacDigest(msg, "keybimat2", "HmacSHA256");
	}
}
