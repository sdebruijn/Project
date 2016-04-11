package nl.project.event;

import nl.project.user.User;

public class Training extends DefaultEvent {
	
	private User trainer;

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		if (trainer != null){
			this.trainer = trainer;
		}
		else {
			throw new NullPointerException("Er is geen trainer meegegeven");
		}
	}
}
