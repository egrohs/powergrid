package backendrest.powergrid.code;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "name", callSuper = false)
@NoArgsConstructor
@ToString
public class Player implements Serializable {
	public Player(String n) {
		name = n;
	}

	private String name;// id
	private Color c;// ?
	private List<Plant> plants = new ArrayList<>();
	private List<City> cities = new ArrayList<>();
	private int score = 0, money = 50;
	@Setter
	private boolean boughtPlant, passedPlant, outOfAuctions;

	public boolean canPay(int v) {
		if (money < v) {
			throw new RuntimeException("Player has no money.");
		}
		return true;
	}
	
	public void pay(int v) {
		canPay(v);
		money -= v;
	}

	public void buyPlant(Plant pl) {
		pay(pl.getLastBids().get(this));
		plants.add(pl);
		boughtPlant = true;
	}
}
