package nl.project.event;

import java.time.LocalDateTime;

public class DefaultEvent extends Event{
	
	private LocalDateTime start, end;
	private String title, location, description;
	
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		if (start == null){
			throw new NullPointerException();
		}
		this.start = start;
		if (this.end.isBefore(this.start)){
			this.end = this.start.plusHours(1);
		}
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		if (end == null){
			throw new NullPointerException();
		}
		if (end.isBefore(this.start)){
			throw new IllegalArgumentException();
		}
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if (title == null){
			throw new NullPointerException();
		}
		else if (title.isEmpty()){
			throw new IllegalArgumentException();
		}
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		if (location == null){
			throw new NullPointerException();
		}
		else if (location.isEmpty()){
			throw new IllegalArgumentException();
		}
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if (description == null){
			throw new NullPointerException();
		}
		else if (description.isEmpty()){
			throw new IllegalArgumentException();
		}
		this.description = description;
	}
}
