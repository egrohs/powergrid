package backendrest.powergrid;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import backendrest.powergrid.code.City;
import backendrest.powergrid.code.Components;
import backendrest.powergrid.code.GameRest;

@SpringBootTest
class PowerGridApplicationTests {
	@Autowired
	GameRest gameRest;

	@BeforeEach
	void init() {
		gameRest.initGame(List.of("p1", "p2"));
	}

	@Test
	void buyPlant() {
		Exception e1 = assertThrows(RuntimeException.class, () -> {
			gameRest.bid("p1", 5, 4);// lower than init
		});
//	    String expectedMessage = "For input string";
//	    String actualMessage = exception.getMessage();
//	    assertTrue(actualMessage.contains(expectedMessage));
		Exception e2 = assertThrows(RuntimeException.class, () -> {
			gameRest.bid("p1", 5, 51);// no money
		});
		gameRest.bid("p1", 5, 5);
		gameRest.bid("p2", 5, 6);
		Exception e3 = assertThrows(RuntimeException.class, () -> {
			gameRest.bid("p1", 5, 5);// lower
		});
		gameRest.bid("p1", 5, 7);
		gameRest.bid("p2", 5, 0);

		System.out.println(Components.getPlantById(5).getLastBids());
	}

	@Test
	void buyCities() {
		gameRest.buyCity("p1", City.ESSEN, true);
		gameRest.buyCity("p1", City.MÜNSTER, false);
		System.out.println(gameRest.g);
	}
}