package nl.project.user;

public class User {
	
	final private String userId;
	private String name, surname;
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if (surname == null){
			throw new NullPointerException();
		}
		else if (surname.isEmpty()){
			throw new IllegalArgumentException();
		}
		this.surname = surname;
	}

	public String getUserId() {
		return userId;
	}

	public User(){userId="";}
	
	public User (String userId, String name, String surname){
		this.userId = userId;
		this.name = name;
		this.surname = surname;
	}
	
	public String getName (){
		return name;
	}
	
	public void setName(String name){
		if (name == null){
			throw new NullPointerException();
		}
		else if (name.isEmpty()){
			throw new IllegalArgumentException();
		}
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
