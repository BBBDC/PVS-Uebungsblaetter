package swc.ctrl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

import swc.data.Final;
import swc.data.Game;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

public class CtrlFinals {

	public static void createDefaultFinals(SoccerWC worldCup) throws NumberFormatException, IOException {
		BufferedReader br = null;
		try {
			URL confUrl = CtrlGroup.class.getResource("/data/config/finals.cfg");
			InputStreamReader isR = new InputStreamReader(confUrl.openStream());
			br = new BufferedReader(isR);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Vector<Game> round16 = worldCup.getFinals().getRoundOf16();
		round16.clear();
		Vector<Game> quarter = worldCup.getFinals().getQuarterFinals();
		quarter.clear();
		Vector<Game> semi = worldCup.getFinals().getSemiFinals();
		semi.clear();
		Game thirdGame = worldCup.getFinals().getThirdGame();
		Game finalGame = worldCup.getFinals().getFinalGame();
		worldCup.getFinals().setWinner("");
		
		for (int i = 0; i < 8; i++) {
			round16.add(new Game(
						Integer.valueOf(br.readLine()).intValue(),
						br.readLine(),
						br.readLine(),
						br.readLine(),
						new Team(),
						new Team(),
						0,
						0,
						false
			));
			round16.get(i).getTeamH().setName(br.readLine());
			round16.get(i).getTeamG().setName(br.readLine());
		}
		for (int i = 0; i < 4; i++) {
			quarter.add(new Game(
						Integer.valueOf(br.readLine()).intValue(),
						br.readLine(),
						br.readLine(),
						br.readLine(),
						new Team(),
						new Team(),
						0,
						0,
						false
			));
			quarter.get(i).getTeamH().setName(br.readLine());
			quarter.get(i).getTeamG().setName(br.readLine());
		}
		for (int i = 0; i < 2; i++) {
			semi.add(new Game(
						Integer.valueOf(br.readLine()).intValue(),
						br.readLine(),
						br.readLine(),
						br.readLine(),
						new Team(),
						new Team(),
						0,
						0,
						false
			));
			semi.get(i).getTeamH().setName(br.readLine());
			semi.get(i).getTeamG().setName(br.readLine());
		}
		thirdGame.setIntId(Integer.valueOf(br.readLine()).intValue());
		thirdGame.setDate(br.readLine());
		thirdGame.setTime(br.readLine());
		thirdGame.setLocation(br.readLine());
		thirdGame.setTeamH(new Team());
		thirdGame.setTeamG(new Team());
		thirdGame.setGoalsH(0);
		thirdGame.setGoalsG(0);
		thirdGame.setPlayed(false);
		
		thirdGame.getTeamH().setName(br.readLine());
		thirdGame.getTeamG().setName(br.readLine());
		
		
		finalGame.setIntId(Integer.valueOf(br.readLine()).intValue());
		finalGame.setDate(br.readLine());
		finalGame.setTime(br.readLine());
		finalGame.setLocation(br.readLine());
		finalGame.setTeamH(new Team());
		finalGame.setTeamG(new Team());
		finalGame.setGoalsH(0);
		finalGame.setGoalsG(0);
		finalGame.setPlayed(false);
		
		finalGame.getTeamH().setName(br.readLine());
		finalGame.getTeamG().setName(br.readLine());
	}
	public static void calculateFinals(SoccerWC wC) {
		Vector<Team> teamsr16 = new Vector<Team>();
		Vector<Team> teamsqf = new Vector<Team>();
		teamsr16.add(wC.getGroups().get(0).getTeams().get(0));
		teamsr16.add(wC.getGroups().get(1).getTeams().get(1));
		teamsr16.add(wC.getGroups().get(2).getTeams().get(0));
		teamsr16.add(wC.getGroups().get(3).getTeams().get(1));
		teamsr16.add(wC.getGroups().get(3).getTeams().get(0));
		teamsr16.add(wC.getGroups().get(2).getTeams().get(1));
		teamsr16.add(wC.getGroups().get(1).getTeams().get(0));
		teamsr16.add(wC.getGroups().get(0).getTeams().get(1));
		teamsr16.add(wC.getGroups().get(4).getTeams().get(0));
		teamsr16.add(wC.getGroups().get(5).getTeams().get(1));
		teamsr16.add(wC.getGroups().get(6).getTeams().get(0));
		teamsr16.add(wC.getGroups().get(7).getTeams().get(1));
		teamsr16.add(wC.getGroups().get(5).getTeams().get(0));
		teamsr16.add(wC.getGroups().get(4).getTeams().get(1));
		teamsr16.add(wC.getGroups().get(7).getTeams().get(0));
		teamsr16.add(wC.getGroups().get(6).getTeams().get(1));
		Game game = wC.getFinals().getRoundOf16().get(0);
		game.setTeamH(teamsr16.get(0));
		game.setTeamG(teamsr16.get(1));
		game = wC.getFinals().getRoundOf16().get(1);
		game.setTeamH(teamsr16.get(2));
		game.setTeamG(teamsr16.get(3));
		game = wC.getFinals().getRoundOf16().get(2);
		game.setTeamH(teamsr16.get(4));
		game.setTeamG(teamsr16.get(5));
		game = wC.getFinals().getRoundOf16().get(3);
		game.setTeamH(teamsr16.get(6));
		game.setTeamG(teamsr16.get(7));
		game = wC.getFinals().getRoundOf16().get(4);
		game.setTeamH(teamsr16.get(8));
		game.setTeamG(teamsr16.get(9));
		game = wC.getFinals().getRoundOf16().get(5);
		game.setTeamH(teamsr16.get(10));
		game.setTeamG(teamsr16.get(11));
		game = wC.getFinals().getRoundOf16().get(6);
		game.setTeamH(teamsr16.get(12));
		game.setTeamG(teamsr16.get(13));
		game = wC.getFinals().getRoundOf16().get(7);
		game.setTeamH(teamsr16.get(14));
		game.setTeamG(teamsr16.get(15));
		try {
			game = wC.getFinals().getQuarterFinals().get(0);
			game.setTeamH(wC.getFinals().getRoundOf16().get(4).getWinner());
		}catch(Exception e) {
			
		}
		try {
			wC.getFinals().getQuarterFinals().get(0).setTeamG(wC.getFinals().getRoundOf16().get(5).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getQuarterFinals().get(1).setTeamH(wC.getFinals().getRoundOf16().get(0).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getQuarterFinals().get(1).setTeamG(wC.getFinals().getRoundOf16().get(1).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getQuarterFinals().get(2).setTeamH(wC.getFinals().getRoundOf16().get(3).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getQuarterFinals().get(2).setTeamG(wC.getFinals().getRoundOf16().get(2).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getQuarterFinals().get(3).setTeamH(wC.getFinals().getRoundOf16().get(6).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getQuarterFinals().get(3).setTeamG(wC.getFinals().getRoundOf16().get(7).getWinner());
		}catch(Exception e) {
			
		}
		try {
			wC.getFinals().getSemiFinals().get(0).setTeamH(wC.getFinals().getQuarterFinals().get(1).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getSemiFinals().get(0).setTeamG(wC.getFinals().getQuarterFinals().get(0).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getSemiFinals().get(1).setTeamH(wC.getFinals().getQuarterFinals().get(2).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getSemiFinals().get(1).setTeamG(wC.getFinals().getQuarterFinals().get(3).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getThirdGame().setTeamH(wC.getFinals().getSemiFinals().get(0).getLoser());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getThirdGame().setTeamG(wC.getFinals().getSemiFinals().get(1).getLoser());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().getFinalGame().setTeamH(wC.getFinals().getSemiFinals().get(0).getWinner());
		}catch(Exception e) {
		}try {
			wC.getFinals().getFinalGame().setTeamG(wC.getFinals().getSemiFinals().get(1).getWinner());
		}catch(Exception e) {
		}
		try {
			wC.getFinals().setWinner(wC.getFinals().getFinalGame().getWinner().getStrName());
		} catch (Exception e) {
		}
	}
	
}
