package backendrest.powergrid.code;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = "name", callSuper = false)
@NoArgsConstructor
public class Player implements Serializable{
	public Player(String n) {
		name = n;
	}

	String name;// id
	Color c;// ?
	List<City> cities = new ArrayList<>();
	int score = 0, money = 50;
}
