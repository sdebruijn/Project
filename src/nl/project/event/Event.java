package nl.project.event;

import java.time.LocalDateTime;

public abstract class Event {

	abstract public String getTitle();
	abstract public String getDescription();
	abstract public String getLocation();
	abstract public LocalDateTime getStart();
	abstract public LocalDateTime getEnd();
	
	abstract public void setTitle(String title);
	abstract public void setDescription(String description);
	abstract public void setLocation(String location);
	abstract public void setStart(LocalDateTime ldt);
	abstract public void setEnd(LocalDateTime ldt);
}
