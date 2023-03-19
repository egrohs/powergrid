package backendrest.powergrid.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Service
public class Game {
//	@Autowired
//	Components components;// TODO ou seria static?
	GameState gs;

	public void init(@NotNull List<String> pNames) {
		// setup
		List<Player> pls = new ArrayList<>();
		for (String n : pNames) {
			pls.add(new Player(n));
		}
		Collections.shuffle(pls);
		gs = new GameState(pls);
		//gs.setTurnOrder(pls);
		gs.setActualMarket(new ArrayList<>(Components.plants.subList(0, 4)));
		gs.setFutureMarket(new ArrayList<>(Components.plants.subList(4, 8)));
		List<Plant> deck = new ArrayList<>(Components.plants.subList(8, 41));
		Plant p13 = deck.remove(2);
		Collections.shuffle(deck);
		deck.add(0, p13);
		gs.setDeck(deck);
	}

	/*
	 * p=null considera como a primeira cidade, sem adjacencias necessarias.
	 */
	public Integer getMinPathCost(Player p, City c) {
		List<City> adjs = List.of(City.values());
		if (p != null)
			adjs = p.getCities();// .stream().map(city -> city.ordinal()).collect(Collectors.toList());

		Integer minCost = 2000000;
		Integer[][] g = Components.graph;
		// procura na linha
		for (int j = 0; j < g.length; j++) {
			Integer v = g[c.ordinal()][j];
			if (v != null && minCost > v
					&& adjs.stream().map(city -> city.ordinal()).collect(Collectors.toList()).contains(j)) {
				minCost = v;
			}
		}
//		for (Integer v : g[c.ordinal()]) {
//			if (v != null && minCost > v && p.getCities().contains(v))
//				minCost = v;
//		}
		// procura na coluna
		for (int i = 0; i < g.length; i++) {
			Integer v = g[i][c.ordinal()];
			if (v != null && minCost > v
					&& adjs.stream().map(city -> city.ordinal()).collect(Collectors.toList()).contains(i)) {
				minCost = v;
			}
		}

		return minCost;
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.init(List.of("p1", "p2"));
		System.out.println(g.getMinPathCost(null, City.ESSEN));
		// System.out.println(Components.plants.get(27));
		System.out.println(g);
	}
}
