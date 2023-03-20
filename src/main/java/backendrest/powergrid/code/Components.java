package backendrest.powergrid.code;

import java.util.ArrayList;
import java.util.List;

//@Data
//@Component
public final class Components {
	final int coal = 24, oil = 24, garbage = 24, uranium = 12, money = 20000;
// 2 players, 3 players ...
	final int[] nRegions = { 3, 3, 4, 5, 5 };
	final int[] nRemoves = { 8, 8, 4, 0, 0 };
	final int[] maxPlants = { 4, 3, 3, 3, 3 };
	final int[] nConnectionsStep2 = { 10, 7, 7, 7, 6 };
	final int[] nConnectionsEndGame = { 21, 17, 17, 15, 14 };
// 2 players, 3 players ...
// step 1,2,3 step 1,2,3 ...
	final int[] renewCoal = { 3, 4, 3, 4, 5, 3, 5, 6, 4, 5, 7, 5, 7, 9, 6 };
	final int[] renewOil = { 2, 2, 4, 2, 3, 4, 3, 4, 5, 4, 5, 6, 5, 6, 7 };
	final int[] renewGarbage = { 1, 2, 3, 1, 2, 3, 2, 3, 4, 3, 3, 5, 3, 5, 6 };
	final int[] renewUranium = { 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 2, 2, 3, 3 };
	final int[] payment = { 10, 22, 33, 44, 54, 64, 73, 82, 90, 98, 105, 112, 118, 124, 128, 134, 138, 142, 145, 148,
			150 };
	static final List<Plant> plants = new ArrayList<>();

	public static Plant getPlantById(int idPlant) {
		return plants.stream().filter(p -> p.getInitCost() == idPlant).findFirst().get();
	}

//Components() 
	static {
		plants.add(new Plant(Plant_Type.OIL, 3, 2, 1));
		plants.add(new Plant(Plant_Type.COAL, 4, 2, 1));
		plants.add(new Plant(Plant_Type.COAL_OIL, 5, 2, 1));
		plants.add(new Plant(Plant_Type.GARBAGE, 6, 1, 1));
		plants.add(new Plant(Plant_Type.OIL, 7, 3, 2));
		plants.add(new Plant(Plant_Type.COAL, 8, 3, 2));
		plants.add(new Plant(Plant_Type.OIL, 9, 1, 1));
		plants.add(new Plant(Plant_Type.COAL, 10, 2, 2));
		plants.add(new Plant(Plant_Type.URANIUM, 11, 1, 2));
		plants.add(new Plant(Plant_Type.COAL_OIL, 12, 2, 2));
		plants.add(new Plant(Plant_Type.WIND, 13, 0, 1));
		plants.add(new Plant(Plant_Type.GARBAGE, 14, 2, 2));
		plants.add(new Plant(Plant_Type.COAL, 15, 2, 3));
		plants.add(new Plant(Plant_Type.OIL, 16, 2, 3));
		plants.add(new Plant(Plant_Type.URANIUM, 17, 1, 2));
		plants.add(new Plant(Plant_Type.WIND, 18, 0, 2));
		plants.add(new Plant(Plant_Type.GARBAGE, 19, 2, 3));
		plants.add(new Plant(Plant_Type.COAL, 20, 3, 5));
		plants.add(new Plant(Plant_Type.COAL_OIL, 21, 2, 4));
		plants.add(new Plant(Plant_Type.WIND, 22, 0, 2));
		plants.add(new Plant(Plant_Type.URANIUM, 23, 1, 3));
		plants.add(new Plant(Plant_Type.GARBAGE, 24, 2, 4));
		plants.add(new Plant(Plant_Type.COAL, 25, 2, 5));
		plants.add(new Plant(Plant_Type.OIL, 26, 2, 5));
		plants.add(new Plant(Plant_Type.WIND, 27, 0, 3));
		plants.add(new Plant(Plant_Type.URANIUM, 28, 1, 4));
		plants.add(new Plant(Plant_Type.COAL_OIL, 29, 1, 4));
		plants.add(new Plant(Plant_Type.GARBAGE, 30, 3, 6));
		plants.add(new Plant(Plant_Type.COAL, 31, 3, 6));
		plants.add(new Plant(Plant_Type.WIND, 33, 0, 4));
		plants.add(new Plant(Plant_Type.URANIUM, 34, 1, 5));
		plants.add(new Plant(Plant_Type.OIL, 35, 1, 5));
		plants.add(new Plant(Plant_Type.COAL, 36, 3, 7));
		plants.add(new Plant(Plant_Type.WIND, 37, 0, 4));
		plants.add(new Plant(Plant_Type.GARBAGE, 38, 3, 7));
		plants.add(new Plant(Plant_Type.URANIUM, 39, 1, 6));
		plants.add(new Plant(Plant_Type.OIL, 40, 2, 6));

		plants.add(new Plant(Plant_Type.COAL, 42, 2, 6));

		plants.add(new Plant(Plant_Type.WIND, 44, 0, 5));

		plants.add(new Plant(Plant_Type.COAL_OIL, 46, 3, 7));

		plants.add(new Plant(Plant_Type.WIND, 50, 0, 6));

		String[] cities = { "Aachen", "Augsburg", "Berlin", "Bielefeld", "Bochum", "Bonn", "Bottrop", "Bremen",
				"Bremerhaven", "Brunswick", "Cologne", "Dortmund", "Dresden", "Duisburg", "Düsseldorf", "Erfurt",
				"Essen", "Frankfurt", "Freiburg im Breisgau", "Füssen", "Halle (Saale)", "Hamburg", "Hanover",
				"Heidelberg", "Hildesheim", "Kassel", "Kiel", "Koblenz", "Leipzig", "Lübeck", "Ludwigshafen",
				"Magdeburg", "Mainz", "Mannheim", "Mönchengladbach", "Munich", "Münster", "Nuremberg", "Offenbach",
				"Oldenburg" };
	}
	static final Integer[][] graph = {
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, 8, null, null, null,
					null, null, null, null, null, null, null, null },
			{ 7, null, null, null, null, 20, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, 4, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, 11, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null },
			{ 19, null, null, null, 11, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null },
			{ null, 0, 21, 11, 10, 18, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, 17, 6, null, 13, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, 18, null, null,
					null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, 14, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, 14, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, 10, 12, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 12, null,
					null, null, null, null, null, null, null },
			{ null, null, null, 6, 17, null, null, 15, 16, 16, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 6, null, 10,
					null, null, 15, null, null, null, null, null, null, null, null, null, null, 17, null, null, null,
					null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, 16, null, null, null, 21, null,
					null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, 18, null, 6, 16, 6,
					null, 19, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, 19, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, 8, null, null, 10, null, 11, null, null, null, null, null, null, null,
					null, null, null, null, null, null, 11 },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 6, null,
					null, 8, null, 11, 11, null, null, 17, 8, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 15,
					null, 19, null, null, null, null, null, null, null, null, null, 19, null, null, null, null, null,
					null, null, null, null, 15, null, 16 },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 4, null,
					null, null, null, null, null, 4, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, 14 },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, 19, null, 13, 6, null, 21, null,
					null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 11,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null,
					null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, 13, null, null, null, null, null,
					null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, 13, null, 10, null, null, null, 19, null, null, null, null, null, 12, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, 11, null, null, 8, null, null,
					null, null, null, null, null, null },
			{ null, 20, 10, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, 4, 18, 2, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ 9, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, 0, 2, null, null, 6, null },
			{ null, 13, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, 15, 8, null, null, null, null,
					null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, 20, 7, null }, };
}
