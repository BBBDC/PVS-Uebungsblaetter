package swc.data;

public class Game {
	private int intId;
	private String time;
	private String date;
	private String location;
	private int goalsH;
	private int goalsG;
	private boolean isPlayed;
	private Team teamH;
	private Team teamG;
	public Game() {
		
	}
	public Game(int id, String date, String time, String location, Team home, Team guest, int goalsH, int goalsG, boolean isPlayed){
		this.intId = id;
		this.date = date;
		this.time = time;
		this.location = location;
		this.teamH = home;
		this.teamG = guest;
		this.goalsH = goalsH;
		this.goalsG = goalsG;
		this.isPlayed = isPlayed;
	}
	public int getIntId() {
		return intId;
	}
	public String getTime() {
		return time;
	}
	public String getDate() {
		return date;
	}
	public String getLocation() {
		return location;
	}
	public int getGoalsH() {
		return goalsH;
	}
	public int getGoalsG() {
		return goalsG;
	}
	public boolean isPlayed() {
		return isPlayed;
	}
	public Team getTeamH() {
		return teamH;
	}
	public Team getTeamG() {
		return teamG;
	}
	public void setIntId(int intId) {
		this.intId = intId;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setGoalsH(int goalsH) {
		this.goalsH = goalsH;
	}
	public void setGoalsG(int goalsG) {
		this.goalsG = goalsG;
	}
	public void setPlayed(boolean isPlayed) {
		this.isPlayed = isPlayed;
	}
	public void setTeamH(Team teamH) {
		this.teamH = teamH;
	}
	public void setTeamG(Team teamG) {
		this.teamG = teamG;
	}
	public Team getWinner() throws Exception {
		if(goalsH > goalsG)
			return teamH;
		if(goalsH < goalsG)
			return teamG;
		else
			throw new Exception();
	}
	public Team getLoser() throws Exception {
		if(goalsH < goalsG)
			return teamH;
		if(goalsH > goalsG)
			return teamG;
		else
			throw new Exception();
	}
}
