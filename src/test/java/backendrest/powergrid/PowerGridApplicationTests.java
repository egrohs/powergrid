package backendrest.powergrid;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import backendrest.powergrid.code.City;
import backendrest.powergrid.code.GameRest;

@SpringBootTest
class PowerGridApplicationTests {
	@Autowired
	GameRest gameRest;

	@BeforeAll
	void init() {
		gameRest.initGame(List.of("p1", "p2"));
	}

	@Test
	void buyPlant() {
		gameRest.bid(null, null, null);
	}

	@Test
	void buyCities() {
		gameRest.buyCity("p1", City.ESSEN, true);
		gameRest.buyCity("p1", City.MÜNSTER, false);
		System.out.println(gameRest.g);
	}
}