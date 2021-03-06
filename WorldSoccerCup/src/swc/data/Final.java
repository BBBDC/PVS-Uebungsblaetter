package swc.data;
import java.util.Vector;
public class Final {
	private Vector<Game> roundOf16;
	private Vector<Game> quarterFinals;
	private Vector<Game> semiFinals;
	private Game thirdGame;
	private Game finalGame;
	private String winner;
	public Final(SoccerWC wC) {
		Team defaultT = new Team("default", 0, 0, 0, 0, 0, 0, 0);
		roundOf16 = new Vector<Game>();

		quarterFinals = new Vector<Game>();

		semiFinals = new Vector<Game>();

		thirdGame = new Game(63, "","","",defaultT,defaultT,0,0,false);
		finalGame = new Game(64, "","","",defaultT,defaultT,0,0,false);
	}
	public Vector<Game> getRoundOf16() {
		return roundOf16;
	}
	public Vector<Game> getQuarterFinals() {
		return quarterFinals;
	}
	public Vector<Game> getSemiFinals() {
		return semiFinals;
	}
	public Game getThirdGame() {
		return thirdGame;
	}
	public Game getFinalGame() {
		return finalGame;
	}
	public String getWinner() {
		return winner;
	}
	public void setRoundOf16(Vector<Game> roundOf16) {
		this.roundOf16 = roundOf16;
	}
	public void setQuarterFinals(Vector<Game> quarterFinals) {
		this.quarterFinals = quarterFinals;
	}
	public void setSemiFinals(Vector<Game> semiFinals) {
		this.semiFinals = semiFinals;
	}
	public void setThirdGame(Game thirdGame) {
		this.thirdGame = thirdGame;
	}
	public void setFinalGame(Game finalGame) {
		this.finalGame = finalGame;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
}
