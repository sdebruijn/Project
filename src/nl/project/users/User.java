package nl.project.users;

public class User {
	
	private String userId;
	private String name;
	
	User (String userId, String name){
		this.userId = userId;
		this.name = name;
	}
	
	public String getName (){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
