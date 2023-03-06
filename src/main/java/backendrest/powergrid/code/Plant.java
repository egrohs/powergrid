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
}

enum Plant_Type {
	COAL, COAL_OIL, OIL, GARBAGE, URANIUM, WIND;
}