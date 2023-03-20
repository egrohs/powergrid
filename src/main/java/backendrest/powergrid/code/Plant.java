package backendrest.powergrid.code;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "initCost", callSuper = false) // TODO e se o custo mudar ?
public class Plant {
	public Plant(Plant_Type t, int ct, int c, int p) {
		initCost = ct;
		consume = c;
		produce = p;
		type = t;
		storage = new HashMap<>();
	}

	private int initCost, consume, produce;
	private Map<Player, Integer> lastBids = new HashMap<>();
	private Plant_Type type;
	private HashMap<Plant_Type, Integer> storage;

	public void bid(Player p, Integer val) {
		lastBids.put(p, val);
	}

	public Entry<Player, Integer> highestBid() {
		return lastBids.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get();
	}

	public boolean store(Plant_Type resType, int qnt) {
		if (type != resType) {
			throw new RuntimeException("This plant cannot store this type.");
		}
		int used = storage.values().stream().reduce(0, (subtotal, element) -> subtotal + element);
		if (used >= consume * 2) {
			throw new RuntimeException("This plant cannot store anymore.");
		}
		storage.put(resType, storage.get(resType) + qnt);
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("[(" + initCost);
		sb.append(") ");
		for (int i = 0; i < consume; i++) {
			sb.append(type.getRes());
		}
		sb.append(" -> " + produce + "]");
		return sb.toString();
	}
}

enum Plant_Type {
	COAL("C"), COAL_OIL("H"), OIL("O"), GARBAGE("G"), URANIUM("U"), WIND("F");

	private String res;

	public String getRes() {
		return res;
	}

	Plant_Type(String string) {
		res = string;
	}
}