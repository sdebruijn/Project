package nl.project.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import nl.project.team.Team;

@Entity
public class User {
	private Long id;
	
	@NotBlank
	@Size(min=2,max=45)
	private String name;
	
	@NotNull
	@Size(min=2,max=45)
	private String surname;
	private List<Team> teams;
	
	@ManyToMany
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User(){}
	
	public User (Long id, String name, String surname){
		this.id = id;
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
		if (this.id.equals(u.id)){
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
