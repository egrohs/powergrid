package backendrest.powergrid.code;

public enum City {
	ESSEN(1), DUSSELDORF(1);
	City(int r) {
		region=r;
	}
	//String name;// id
	int region; // 1-6
	//List<Link> links;

//	class Link {
//		String name;
//		int cost;
//	}
}
