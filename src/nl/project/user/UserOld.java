package nl.project.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UserOld {
	
	private Long userId;
	private String name, surname;
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
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

	public UserOld(){}
	
	public UserOld (Long userId, String name, String surname){
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
		if (!(o instanceof UserOld)){
			return false;
		}
		UserOld u = (UserOld)o;
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
