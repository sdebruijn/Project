package nl.project.event;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import nl.project.team.Team;
import nl.project.user.User;

@Entity
public class Event {

	@NotEmpty
	private String type;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private String date;
	private String starttime;
	private String endtime;
	private LocalDateTime start;
	private LocalDateTime end;
	
	private List<User> present;
	private List<User> absent;
	
	@NotEmpty
	private String title;
	private String description;
	private String location;
	private List<Team> teams;
	
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
		
		start = LocalDateTime.of(year, month, day, 0, 0);
		end = LocalDateTime.of(year, month, day, 0, 0);
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
	@ManyToMany(mappedBy="events")
=======
	@ManyToMany
>>>>>>> 651e66823299e807d7e66dd350bf7f074c3da588
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

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
}
