package nl.project.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import nl.project.team.Team;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"name", "surname"})) 
public class User {
/*** CONSTRUCTORS ***/
	public User(){}
	
	public User (Long id, String name, String surname){
		this(name, surname);
		this.id = id;
	}
	public User(String name, String surname) {
		this();
		this.setName(name);
		this.setSurname(surname);
	}
	
	/*** Fields ***/
	private Long id;
	
	@NotBlank
	@Size(min=2,max=45)
	private String name;
	
	@NotNull
	@Size(min=2,max=45)
	private String surname;
	
	private Team team;
	
	/*** Getters and stters***/
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		this.name = name.trim();
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
		this.surname = surname.trim();
	}
	
  @ManyToOne
  @JoinColumn(name="team_id")

	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;

		//team.addMember(this);
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
		
		if ( this.name.equals(u.name) && 
			 this.surname.equals(u.surname) ) { 
				return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "User: " + name + " " + surname;
	}
}
