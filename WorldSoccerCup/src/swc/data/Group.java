package swc.data;

import java.util.Vector;

public class Group {
	private String strGroupName;
	private Vector<Team> teams;
	private Vector<Game> games;
	private boolean isGroupCompleted;
	
	public Group(String groupName) {
		this.strGroupName = groupName;
		teams = new Vector<Team>();
		games = new Vector<Game>();
	}
	public void addTeam(Team teamName) {
		teams.add(teamName);
	}
	public void addGame(Game newGame) {
		games.add(newGame);
	}
	public Vector<Team> getTeams(){
		return teams;
	}
	public Vector<Game> getGames(){
		return games;
	}
	public String getStrGroupName() {
		return strGroupName;
	}
	public boolean isGroupCompleted() {
		return isGroupCompleted;
	}
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}
	public void setTeams(Vector<Team> teams) {
		this.teams = teams;
	}
	public void setGames(Vector<Game> games) {
		this.games = games;
	}
	public void setGroupCompleted(boolean isGroupCompleted) {
		this.isGroupCompleted = isGroupCompleted;
	}
	
}
