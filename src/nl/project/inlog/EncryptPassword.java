package nl.project.inlog;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPassword {

	public static String encrypt(String str) {
		return new BCryptPasswordEncoder().encode(str);
	}
	
	public static void main(String[] args) {
		String[] testing = {"test", "password", "wachtwoord", "sijmen","sander", "user"};
		
		for (String string : testing) {
			System.out.println(String.format("%10s -> %s", string, encrypt(string)));
		}

	}

}
