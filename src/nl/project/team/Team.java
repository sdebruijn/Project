package nl.project.team;

import java.util.List;

//import nl.project.users;
public class Team {
	private String teamId; // or int?
	private String name;
	private List<User> members;
	private User manager;
	
	
	public Team(String teamId, String name, List<User> members, User manager){
		this.teamId = teamId;
		this.name = name;
		this.members = members;
		this.manager = manager;		
	}
}


class User {}