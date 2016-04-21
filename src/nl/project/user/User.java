package nl.project.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
<<<<<<< HEAD
=======
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import nl.project.event.Event;
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
	
	private List<Team> teams;
	private List<Event> eventsPresent;
	private List<Event> eventsAbsent;
	
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
	
	@ManyToMany(mappedBy="members")
	public List<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	@ManyToMany(mappedBy="present")
	public List<Event> getEventsPresent() {
		return eventsPresent;
	}

	public void setEventsPresent(List<Event> eventsPresent) {
		this.eventsPresent = eventsPresent;
	}

	@ManyToMany(mappedBy="absent")
	public List<Event> getEventsAbsent() {
		return eventsAbsent;
	}

	public void setEventsAbsent(List<Event> eventsAbsent) {
		this.eventsAbsent = eventsAbsent;
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
