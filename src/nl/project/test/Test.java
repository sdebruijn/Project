package nl.project.test;

import nl.project.user.User;
import nl.project.user.UserDao;

public class Test {

	public static void main(String[] args) {

		String name = "Sijmen";
		String surname= "de Bruijn";
		
		User user = UserDao.find(new User(name,surname));
		
		System.out.println(user);
		
	
	}

}
