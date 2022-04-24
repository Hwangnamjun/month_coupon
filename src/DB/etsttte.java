package DB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

public class etsttte {

	public static void main(String[] args) {
		Properties properties  = new Properties();
		try {
			properties.load(new FileInputStream("D:\\workspace\\month_coupon\\src\\DB\\db"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String salt()
	{
		String salt;
		
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			random.nextBytes(bytes);
			salt = new String(Base64.getEncoder().encode(bytes));
			return salt;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("난수값생성 실패"+e.toString());
			return null;
		}
	}
	public static String SHA256(String word, String salt)
	{
		String salt11 = "";
		return null;
	}
}
