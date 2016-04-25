package nl.project.event;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import nl.project.team.Team;

@Entity
public class Event {

	private String type;
	
<<<<<<< HEAD
=======
	@DateTimeFormat(pattern="yyyy-MM-dd")
>>>>>>> 2afc8bf7bcf231d8985092ae72be3efd31280580
	private String date;
	private String starttime;
	private String endtime;
	private LocalDateTime start;
	private LocalDateTime end;
	
<<<<<<< HEAD
=======
	//private List<User> present;
	//private List<User> absent;
	
	
	private Team eventOwner;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="owner_id")
	public Team getEventOwner() {
		return eventOwner;
	}
	
	public void setEventOwner(Team newOwner) {
		this.eventOwner = newOwner;
	}
	
	
>>>>>>> 2afc8bf7bcf231d8985092ae72be3efd31280580
	@NotEmpty
	private String title;
	private String description;
	private String location;
<<<<<<< HEAD
	private Team team;
	
=======
		
>>>>>>> 2afc8bf7bcf231d8985092ae72be3efd31280580
	//Match
	private String homeTeam;
	private String awayTeam;
	private int scoreHome;
	private int scoreAway;
	private boolean played;
	
	//Training
	private String trainer;
	
	@Column(name="id")
	private Long id;
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long teamId) {
		this.id = teamId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		
		if (date != null){
		String [] parts = date.split("-");
		int year = Integer.valueOf(parts[0]);
		int month = Integer.valueOf(parts[1]);
		int day = Integer.valueOf(parts[2]);
		
		this.start = LocalDateTime.of(year, month, day, 0, 0);
		this.end = LocalDateTime.of(year, month, day, 0, 0);
		}
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

<<<<<<< HEAD
	@ManyToOne
	@JoinColumn(name="team_id")
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

=======
>>>>>>> 2afc8bf7bcf231d8985092ae72be3efd31280580
	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getScoreHome() {
		return scoreHome;
	}

	public void setScoreHome(int scoreHome) {
		this.scoreHome = scoreHome;
	}

	public int getScoreAway() {
		return scoreAway;
	}

	public void setScoreAway(int scoreAway) {
		this.scoreAway = scoreAway;
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
<<<<<<< HEAD
=======
	
	/*
	@ManyToMany
	@JoinTable(name="EVENTS_USERS")
	public List<User> getPresent() {
		return present;
	}

	public void setPresent(List<User> present) {
		this.present = present;
	}

	@ManyToMany
	@JoinTable(name="EVENTS_USERSNOT")
	public List<User> getAbsent() {
		return absent;
	}

	public void setAbsent(List<User> absent) {
		this.absent = absent;
	}
	
	public void addPresent(User u){
		this.present.add(u);
	}
	
	public void removePresent(User u){
		present.remove(u);
	}
	
	public void removeAllPresent(){
		present.clear();
	}

	public void addAbsent(User u){
		this.absent.add(u);
	}
	
	public void removeAbsent(User u){
		absent.remove(u);
	}
	
	public void removeAllAbsent(){
		absent.clear();
	}
	*/
>>>>>>> 2afc8bf7bcf231d8985092ae72be3efd31280580
}
