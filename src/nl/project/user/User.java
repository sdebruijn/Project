package nl.project.user;

public class User {
	
	final private String userId;
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
	
	@Override
	public boolean equals(Object o){
		if (this == o) { 
			return true;
		}
		if (!(o instanceof User)){
			return false;
		}
		User u = (User)o;
		if (this.userId.equals(u.userId)){
			if (this.name.equals(u.name)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "User: " + name;
	}
}
