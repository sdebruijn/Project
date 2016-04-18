package nl.project.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import nl.project.team.Team;

@Entity
public class DefaultEvent{
	
	private String date;
	private LocalDate d;
	private LocalDateTime start;
	private LocalDateTime end;
	private String type;
	
	@Size(min=2, max=50)
	private String title;
	private String location;
	
	@NotEmpty
	private String description;
	private List<Team> teams;
	
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		
		d = LocalDate.of(year, month, day);
		}
	}
	
	public LocalDate getD() {
		return d;
	}

	public void setD(LocalDate d) {
		this.d = d;
	}

	@ManyToMany(mappedBy="events")
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
