package backendrest.powergrid.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
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

	@GetMapping("/init")
	@ResponseBody
	void initGame(@NotNull @RequestParam(value = "myParam[]") List<String> pNames) {
		g.init(pNames);
	};

	int cityCost(Player p, City dest) {
		return 5 + g.getGs().getStep() * 5 + g.getMinPathCost(p, dest);
	};

	Player powerCity(Player p, City c) {
		// TODO ver se player tem alguma adjacencia antes...
		if (!g.getGs().getRegions().contains(c.region))
			throw new RuntimeException("City out of valid regions.");
		if (p.getCities().contains(c))
			throw new RuntimeException("City already powered by this player.");
		int cityCost = 5 + g.getGs().getStep() * 5 + g.getMinPathCost(p, c);
		if (cityCost > p.getMoney())
			throw new RuntimeException("No money.");
		p.setMoney(p.getMoney() - cityCost);
		p.getCities().add(c);
		return p;
	};

	@GetMapping("/porder")
	@ResponseBody
	int[] playerOrder() {
		log.debug("passou");
		return new int[6];
	};

	void bid(Player p, Integer value) {
	};// null or 0 = pass

	void buyResource(Plant_Type resource, int qnt) {
	};
}