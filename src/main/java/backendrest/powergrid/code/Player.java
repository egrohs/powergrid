package backendrest.powergrid.code;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "name", callSuper = false)
@NoArgsConstructor
public class Player implements Serializable {
	public Player(String n) {
		name = n;
	}

	private String name;// id
	private Color c;// ?
	private List<City> cities = new ArrayList<>();
	private int score = 0, money = 50;

	public void pay(int v) {
		if (money < v) {
			throw new RuntimeException("Player has no money.");
		}
	}
}
