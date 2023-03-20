package backendrest.powergrid.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//Replace @RestController with @Controller. @RestController will make the returned object the HTTP response body.
@Controller
//@RestController
//@RequestMapping(path = /*GameRest.URL_PREFIX +*/ "/game")
public class GameRest {
	// public static final String URL_PREFIX = "/api/v1";
	@Autowired
	public Game g;
//	@PatchMapping("/atualiza-bens")
//	@Operation(summary = "Efetua o salvamento do arrolamento alterando seus bens.")
//	@ResponseBody
//	public Arrolamento associarBens(@NotNull @RequestParam String ni, @RequestParam @NotNull Long idArrl,
//			@RequestBody Set<BemDireitoPK> bens) {
//		return servico.associarBensRpfs(ni, idArrl, bens, null, null);
//	}

	@GetMapping("/")
	// @ResponseBody
	public String asd(Model model) {
		model.addAttribute("valor", "blah");
		return "hello";
	}

	@GetMapping("/init")
	@ResponseBody
	public void initGame(@NotNull @RequestParam(value = "myParam[]") List<String> pNames) {
		g.init(pNames);
	}

	int cityCost(Player p, City dest) {
		return 5 + g.getGs().getStep() * 5 + g.getMinPathCost(p, dest, false);
	}

	@PostMapping("/buy-city")
	@ResponseBody
	public Player buyCity(@RequestParam("pname") String pname, @RequestParam("city") City city,
			@RequestParam("firstCity") boolean firstCity) {
		Player player = g.getGs().getTurnOrder().stream().filter(p -> pname.equals(p.getName())).findFirst().get();
		if (!g.getGs().getRegions().contains(city.region))
			throw new RuntimeException("City out of valid regions.");
		if (player.getCities().contains(city))
			throw new RuntimeException("City already powered by this player.");
		int owners = (int) g.getGs().turnOrder.stream().filter(p -> p.getCities().contains(city)).count();
		int cityCost = firstCity ? 0 : 10 + owners * 5 + g.getMinPathCost(player, city, firstCity);
//		if (cityCost > player.getMoney())
//			throw new RuntimeException(
//					"Player " + pname + " money " + player.getMoney() + " has no sufficient money " + cityCost);
		player.pay(cityCost);
		player.getCities().add(city);
		return player;
	}

	@GetMapping("/porder")
	@ResponseBody
	int[] playerOrder() {
		log.debug("passou");
		return new int[6];
	}

	@GetMapping("/bid")
	@ResponseBody
	public void bid(Player p, Plant plant, Integer value) {
		// TODO onde armazenar esse trio? lista bids no game, gs, PLANT!, player?
		if (g.gs.step != 3 && g.gs.market.indexOf(plant) > 3)
			throw new RuntimeException("Plant belongs to future market.");
	}

	@GetMapping("/resource")
	@ResponseBody
	public void buyResource(Player p, Plant_Type resource, int qnt) {

	}
}