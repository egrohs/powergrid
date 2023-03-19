package backendrest.powergrid.code;

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
	}

	int initCost, consume, produce;
	Plant_Type type;

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