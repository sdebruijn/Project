package nl.project.event;

public class Match extends DefaultEvent {
	/*
	 * 	private LocalDateTime start, end;
	 *  private String title, location, description;
	 *  private List<Team> attendingTeams;
	 */
	
	private String homeTeam, awayTeam;
	private int scoreHome, scoreAway;
	private boolean played;
	
	public boolean isPlayed() {
		return played;
	}
	public void setPlayed(boolean played) {
		this.played = played;
	}
	public String getHome() {
		return homeTeam;
	}
	public void setHome(String home) {
		this.homeTeam = home;
	}
	public String getAway() {
		return awayTeam;
	}
	public void setAway(String away) {
		this.awayTeam = away;
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
	
	public void setScores(int scoreHome, int scoreAway) {
		this.setScoreHome(scoreHome);
		this.setScoreAway(scoreAway);
		this.setPlayed(true);
	}
}
