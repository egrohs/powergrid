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
	Game g;
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
	void initGame(@NotNull @RequestParam(value = "myParam[]") List<String> pNames) {
		g.init(pNames);
	}

	int cityCost(Player p, City dest) {
		return 5 + g.getGs().getStep() * 5 + g.getMinPathCost(p, dest);
	}

	@PostMapping("/power")
	@ResponseBody
	Player powerCity(@RequestParam("pname") String pname, @RequestParam("city") City city) {
		Player p = g.getGs().getTurnOrder().get(0);
		// TODO ver se player tem alguma adjacencia antes...
		if (!g.getGs().getRegions().contains(city.region))
			throw new RuntimeException("City out of valid regions.");
		if (p.getCities().contains(city))
			throw new RuntimeException("City already powered by this player.");
		int cityCost = 5 + g.getGs().getStep() * 5 + g.getMinPathCost(p, city);
		if (cityCost > p.getMoney())
			throw new RuntimeException("No money.");
		p.setMoney(p.getMoney() - cityCost);
		p.getCities().add(city);
		return p;
	}

	@GetMapping("/porder")
	@ResponseBody
	int[] playerOrder() {
		log.debug("passou");
		return new int[6];
	}

	void bid(Player p, Integer value) {
	}

	void buyResource(Plant_Type resource, int qnt) {
	}
}