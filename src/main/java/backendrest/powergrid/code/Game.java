package backendrest.powergrid.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
		gs = new GameState();
		gs.setTurnOrder(pls);
		gs.setActualMarket(new ArrayList<>(Components.plants.subList(0, 4)));
		gs.setFutureMarket(new ArrayList<>(Components.plants.subList(4, 8)));
		List<Plant> deck = new ArrayList<>(Components.plants.subList(8, 41));
		Plant p13 = deck.remove(2);
		Collections.shuffle(deck);
		deck.add(0, p13);
		gs.setDeck(deck);
	}

	public Integer getMinPathCost(Player p, City c) {
		int minCost = Integer.MAX_VALUE;
		Integer[][] g = Components.graph;
		// procura na linha
		for (Integer v : g[c.ordinal()]) {
			if (v != null && minCost > v)
				minCost = v;
		}
		// procura na coluna
		Integer[] col = Arrays.stream(g)
				.map(i -> g[i][c.ordinal()])
				//.map(x -> x[address])
				.toArray(Integer[]::new);
		for (Integer v : col) {
			if (v != null && minCost > v)
				minCost = v;
		}
		return minCost;
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		System.out.println(g.getMinPathCost(null, City.ESSEN));
	}
}
