package beerdiary;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	private static String userName;
	private String fName;
	private String lName;

	public User(String userName, String fName, String lName) {
		this.userName = userName;
		this.fName = fName;
		this.lName = lName;
	}

	public static String getUserName() {
		return userName;
	}

	public String getFName() {
		return this.fName;
	}

	public String getLName() {
		return this.lName;
	}
	
	public static String get_SHA_1_SecurePassword(String passwordToHash)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
           // md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
}
