package backendrest.powergrid.code;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "name", callSuper = false)
public class Player {
	public Player(String n) {
		name = n;
	}

	String name;// id
	Color c;// ?
	List<City> cities = new ArrayList<>();
	int score = 0, money = 50;
}
