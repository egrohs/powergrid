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

	public GameState init(@NotNull List<String> pNames) {
		// setup
		List<Player> pls = new ArrayList<>();
		for (String n : pNames) {
			pls.add(new Player(n));
		}
		Collections.shuffle(pls);
		gs = new GameState(pls);
		// gs.setTurnOrder(pls);
		gs.setMarket(new ArrayList<>(Components.plants.subList(0, 8)));
		// gs.setFutureMarket(new ArrayList<>(Components.plants.subList(4, 8)));
		List<Plant> deck = new ArrayList<>(Components.plants.subList(8, 41));
		Plant p13 = deck.remove(2);
		Collections.shuffle(deck);
		deck.add(0, p13);
		gs.setDeck(deck);
		return gs;
	}

	public void trySell(Plant pl) {
		List<Player> auctionners = gs.turnOrder.stream()
				.filter(p -> !p.isBoughtPlant() && !p.isPassedPlant() && !p.isOutOfAuctions()).toList();
		if (auctionners.size() == 1) {
			auctionners.get(0).buyPlant(pl);
		}
	}

	// TODO incompleto
	public Player buyResource(Player p, Plant plant, Plant_Type resource, int qnt) {
		Integer res = null;
		int price = 0;
		switch (resource) {
		case URANIUM: {
			res = gs.uranium;
			price = res < 6 ? 18 - (res * 2) : res;
			// yield type;
		}
		case COAL: {
			res = gs.coal;
			price = (int) (9 - Math.ceil((double) gs.coal / 3));
		}
		default:
			// throw new IllegalArgumentException("Unexpected value: " + resource);
		}

		if (res < qnt) {
			throw new RuntimeException("Not enought resources.");
		}
		do {
			p.pay(price);
			res--;
			plant.store(resource, qnt);
			qnt--;
		} while (qnt > 0);
		return p;
	}

	// TODO recursive
	public Integer getMinPathCost(Player p, City c, boolean firstCity) {
		if (!firstCity) {
			List<City> adjs = p.getCities();// .stream().map(city -> city.ordinal()).collect(Collectors.toList());
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
		} // p.getCities().add(c);
		return 0;
	}
}