package nl.project.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
=======
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
>>>>>>> 2afc8bf7bcf231d8985092ae72be3efd31280580
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
<<<<<<< HEAD
	  @ManyToOne
	  @JoinColumn(name="team_id")
=======
	@ManyToOne
	@JoinColumn(name="team_id")
>>>>>>> 2afc8bf7bcf231d8985092ae72be3efd31280580
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
<<<<<<< HEAD
=======
		//team.addMember(this);
>>>>>>> 2afc8bf7bcf231d8985092ae72be3efd31280580
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
