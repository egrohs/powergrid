package backendrest.powergrid.code;

public enum City {
	AACHEN(1), FRANKFURT_M(1), KOLN(1), MANNHEIM(1), SAARSBRÜCKEN(1), TRIER(1), WEINSBADEN(1), AUGSBURG(2), FREIBURG(2),
	KONSTANZ(2), MÜNCHEN(2), PASSAU(2), REGENSBURG(2), STUTTGART(2), BERLIN(3), FRANKFURT_D(3), LÜBECK(3), MAGDEBURG(3),
	ROSTOCK(3), SCHWERIN(3), TORGELOW(3), BREMEN(4), CUXHAVEN(4), FLENSBURG(4), HAMBURG(4), HANOVER(4), KIEL(4),
	WILHELMSHAVEN(4), DRESDEN(5), ERFURT(5), FULDA(5), HALLE(5), LEIPZIG(5), NUREMBERG(5), WÜRSBURG(5), DORTMUND(6),
	DUISBURG(6), DÜSSELDORF(6), ESSEN(6), KASSEL(6), MÜNSTER(6), OSNABRÜCK(6);

	City(int r) {
		region = r;
	}

	// String name;// id
	int region; // 1-6
	// List<Link> links;

//	class Link {
//		String name;
//		int cost;
//	}
}
