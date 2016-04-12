package nl.project.event;

import java.time.LocalDateTime;

public class Party extends DefaultEvent {
	
	public Party (){
		super();
	}
	
	@Override
	public void setEnd(LocalDateTime end) {
		
		if (super.getStart().getHour() < 6) {
			super.setEnd(super.getStart().withHour(6).withMinute(30));
		} else {
			super.setEnd(super.getStart().plusDays(1).withHour(6).withMinute(30));
		}
		
	}
}
