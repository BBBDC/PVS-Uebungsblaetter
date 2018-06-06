package swc.data;

public class Team implements Comparable<Team> {
	private String strName;
	private int points;
	private int gf;
	private int ga;
	private int played;
	private int won;
	private int loss;
	private int draw;
	@Override
	public int compareTo(Team arg0) {
		if(this.points < arg0.points)
			return 1;
		else if(this.points == arg0.points) {
			int difThis = this.ga - this.gf;
			int difOther = arg0.ga - arg0.gf;
			if(difThis < difOther) {
				return -1;
			}
			else if(difThis == difOther) {
				if(this.gf < arg0.gf)
					return 1;
				else if(this.gf == arg0.gf)
					return 0;
				else
					return -1;
			}
			else {
				return 1;
			}
		}
		else {
			return -1;
		}
	}
	public Team(String name, int points, int gf, int ga, int played, int won, int loss, int draw) {
		this.strName = name;
		this.points = points;
		this.gf = gf;
		this.ga = ga;
		this.played = played;
		this.won = won;
		this.loss = loss;
		this.draw = draw;
	}
	public Team() {
	}
	public void clearTeam() {
		this.points = 0;
		this.gf = 0;
		this.ga = 0;
		this.played = 0;
		this.won = 0;
		this.loss = 0;
		this.draw = 0;
	}
	public String getStrName() {
		return strName;
	}
	public int getPoints() {
		return points;
	}
	public int getGf() {
		return gf;
	}
	public int getGa() {
		return ga;
	}
	public int getPlayed() {
		return played;
	}
	public int getWon() {
		return won;
	}
	public int getLoss() {
		return loss;
	}
	public int getDraw() {
		return draw;
	}
	public void setName(String strName) {
		this.strName = strName;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public void setGf(int gf) {
		this.gf = gf;
	}
	public void setGa(int ga) {
		this.ga = ga;
	}
	public void setPlayed(int played) {
		this.played = played;
	}
	public void setWon(int won) {
		this.won = won;
	}
	public void setLoss(int loss) {
		this.loss = loss;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
}
