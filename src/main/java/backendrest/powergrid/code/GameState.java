package backendrest.powergrid.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class GameState {
	List<Integer> regions;
//	Step 2: Starts after any player has built his 7th city.
	int step = 1, turn;
//graph??
	// 7=6 6=7 5=8 4=10 3=12 2=14 1=16
	int coal = 24, oil = 18, garbage = 6, uranium = 2;
	List<Plant> actualMarket = new ArrayList<>();
	List<Plant> futureMarket = new ArrayList<>();
	List<Plant> deck;
	List<Player> turnOrder;// = new ArrayList<>();
	Map<Player, Integer> scoreTrack = new HashMap<>();

	public GameState(List<Player> pls) {
		turnOrder = pls;
		pls.forEach(p -> scoreTrack.put(p, 0));
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("\nTURN " + turn + "\n\n");
		sb.append(
				"COAL =\t\t" + (coal % 3 == 0 ? 3 : coal % 3) + "/$" + (int) (9 - Math.ceil((double) coal / 3)) + "\n");
		sb.append("OIL =\t\t" + (oil % 3 == 0 ? 3 : oil % 3) + "/$" + (int) (9 - Math.ceil((double) coal / 3)) + "\n");
		sb.append("GARBAGE =\t" + (garbage % 3 == 0 ? 3 : garbage % 3) + "/$" + (int) (9 - Math.ceil((double) coal / 3))
				+ "\n");
		sb.append("URANIUM =\t$" + (uranium < 6 ? 18 - (uranium * 2) : uranium) + "\n\n");
		sb.append("ACTUAL MARKET = " + actualMarket.toString() + "\n");
		sb.append("FUTURE MARKET = " + futureMarket.toString() + "\n\n");
		sb.append(scoreTrack.toString() + "\n");
		return sb.toString();
	}
}