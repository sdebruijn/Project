package nl.project.test;

import nl.project.user.User;
import nl.project.user.UserDao;

public class Test {

	public static void main(String[] args) {

		String name = "Sijmen";
		String surname= "de Bruijn";
		
		UserDao userDao = new UserDao();
		User user = userDao.find(new User(name,surname));
		
		System.out.println(user);
		
	
	}

}
