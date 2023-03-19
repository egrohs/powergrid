package backendrest.powergrid.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class GameState {
	List<Integer> regions = List.of(1,2,3,4,5,6);
//	Step 2: Starts after any player has built his 7th city.
	int step = 1;
//graph??
	int coal = 24, oil = 18, garbage = 6, uranium = 4;
	List<Plant> actualMarket = new ArrayList<>();
	List<Plant> futureMarket = new ArrayList<>();
	List<Plant> deck;
	List<Player> turnOrder;
	Map<Player, Integer> scoreTrack;
}