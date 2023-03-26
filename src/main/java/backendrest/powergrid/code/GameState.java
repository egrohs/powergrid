package backendrest.powergrid.code;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class GameState extends Image {
	List<Integer> regions = List.of(1, 2, 3, 4, 5, 6);
//	Step 2: Starts after any player has built his 7th city.
	int step = 1, turn;
//graph??
	// 7=6 6=7 5=8 4=10 3=12 2=14 1=16
	Integer coal = 24, oil = 18, garbage = 6, uranium = 2;
	List<Plant> market = new ArrayList<>();
//	List<Plant> actualMarket = new ArrayList<>();
//	List<Plant> futureMarket = new ArrayList<>();
	List<Plant> deck;
	List<Player> turnOrder;// = new ArrayList<>();
	Map<Player, Integer> scoreTrack = new HashMap<>();

	public GameState(List<Player> pls) {
		this.src = "powergrid.jpg";
		//this.style = "'background-image:url('+@{/powergrid.jpg}+');  background-size: 100% 100%;  background-repeat:no-repeat;  background-attachment: fixed; ";
		turnOrder = pls;
		pls.forEach(p -> scoreTrack.put(p, 0));
	}

	public void drawPlant() {
		if (deck.size() < 1)
			throw new RuntimeException("no plants left.");
		market.add(deck.remove(0));
		market = market.stream().sorted(Comparator.comparingInt(Plant::getInitCost)).collect(Collectors.toList());
	}

	public Plant bottomHighestPlant() {
		if (step != 1)
			throw new RuntimeException("not step 1.");
		return market.remove(market.size() - 1);
	}

	public void removeLowestPlant() {
		if (step == 1)
			throw new RuntimeException("step 1 yet.");
		market.remove(0);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("\nTURN " + turn + "\n\n");
		sb.append(
				"COAL =\t\t" + (coal % 3 == 0 ? 3 : coal % 3) + "/$" + (int) (9 - Math.ceil((double) coal / 3)) + "\n");
		sb.append("OIL =\t\t" + (oil % 3 == 0 ? 3 : oil % 3) + "/$" + (int) (9 - Math.ceil((double) coal / 3)) + "\n");
		sb.append("GARBAGE =\t" + (garbage % 3 == 0 ? 3 : garbage % 3) + "/$" + (int) (9 - Math.ceil((double) coal / 3))
				+ "\n");
		sb.append("URANIUM =\t$" + (uranium < 6 ? 18 - (uranium * 2) : uranium) + "\n\n");
		sb.append("ACTUAL MARKET = " + market.subList(0, 4).toString() + "\n");
		sb.append("FUTURE MARKET = " + market.subList(4, 8).toString() + "\n\n");
		sb.append(scoreTrack.toString() + "\n");
		return sb.toString();
	}

	public Player getPlayerByName(String pname) {
		return getTurnOrder().stream().filter(p -> pname.equals(p.getName())).findFirst().get();
	}
}