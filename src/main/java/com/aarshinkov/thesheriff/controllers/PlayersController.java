package com.aarshinkov.thesheriff.controllers;

import com.aarshinkov.thesheriff.base.*;
import com.aarshinkov.thesheriff.memory.objects.Player;
import com.aarshinkov.thesheriff.services.MemoryService;
import static com.aarshinkov.thesheriff.utils.AppConstants.PLAYERS_KEY;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Controller
@RequiredArgsConstructor
public class PlayersController extends Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final MemoryService memoryService;

  @GetMapping(value = "/players")
  public String players(Model model) {

//    model.addAttribute("notUsedPlayers", getNotUsedPlayers());
    return "players";
  }

  @PostMapping(value = "/addPlayer")
  public String addPlayer(@RequestParam("hero") String hero, @RequestParam("name") String name) {

    List<Player> players = memoryService.getPlayers();

    if (players == null) {
      players = new ArrayList<>();
    }

    log.debug(Arrays.toString(players.toArray()));

//    log.debug("Available players: " + getNotUsedPlayers());
//    boolean isPlayerMarkedAsUsed = markPlayerAsUsed(player);
//    log.debug("Is player marked as used? " + isPlayerMarkedAsUsed);
    players.add(new Player(name, hero));

    memoryService.storeObjectToMemory(PLAYERS_KEY, players);
    memoryService.markHeroAsUsed(hero);

    return "redirect:/";
  }
}
