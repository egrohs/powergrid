package backendrest.powergrid.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
//	@PatchMapping("/atualiza-bens")
//	@Operation(summary = "Efetua o salvamento do arrolamento alterando seus bens.")
//	@ResponseBody
//	public Arrolamento associarBens(@NotNull @RequestParam String ni, @RequestParam @NotNull Long idArrl,
//			@RequestBody Set<BemDireitoPK> bens) {
//		return servico.associarBensRpfs(ni, idArrl, bens, null, null);
//	}

	@GetMapping("/")
//	@RequestMapping("/")
	// @ResponseBody
	public String asd(Model model) {
		model.addAttribute("board", g.init(List.of("p1", "p2")));
		// return "init";
		return "hello";
	}

	@MessageMapping("/myendpoint")
	@SendTo("/topic/msgs")
	public GameState asd() {
		System.out.println("asd");
		return g.gs;
	}

//	@MessageMapping("/news")
//	public void broadcastNews(@Payload GameState message) {
//	  this.simpMessagingTemplate.convertAndSend("/topic/news", g.gs);
//	}

	// @GetMapping("/init")
	// @ResponseBody
	public GameState initGame(@NotNull @RequestParam(value = "myParam[]") List<String> pNames) {
		return g.init(pNames);
	}

	@GetMapping("/bid")
	@ResponseBody
	public void bid(String pname, Integer idPlant, Integer value) {
		Player p = g.gs.getPlayerByName(pname);
		p.canPay(value);
		if (p.isBoughtPlant())
			throw new RuntimeException("Player already bought");
		Plant pl = Components.getPlantById(idPlant);
		if (g.gs.step != 3 && g.gs.market.indexOf(pl) > 3)
			throw new RuntimeException("Plant belongs to future market.");
		pl.bid(p, value);
		g.trySell(pl);
	}

	int cityCost(Player p, City dest) {
		return 5 + g.getGs().getStep() * 5 + g.getMinPathCost(p, dest, false);
	}

	@PostMapping("/buy-city")
	@ResponseBody
	public Player buyCity(@RequestParam("pname") String pname, @RequestParam("city") City city,
			@RequestParam("firstCity") boolean firstCity) {
		Player player = g.gs.getPlayerByName(pname);
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

	@GetMapping("/resource")
	@ResponseBody
	public void buyResource(Player p, Plant_Type resource, int qnt) {

	}
}