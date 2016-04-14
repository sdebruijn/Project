package nl.project.event;

import nl.project.user.UserOld;

public class Training extends DefaultEvent {
	
	private UserOld trainer;

	public UserOld getTrainer() {
		return trainer;
	}

	public void setTrainer(UserOld trainer) {
		if (trainer != null){
			this.trainer = trainer;
		}
		else {
			throw new NullPointerException("Er is geen trainer meegegeven");
		}
	}
}
