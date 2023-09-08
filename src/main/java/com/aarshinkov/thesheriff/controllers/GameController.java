package com.aarshinkov.thesheriff.controllers;

import com.aarshinkov.thesheriff.domain.game.PlayerResult;
import com.aarshinkov.thesheriff.base.*;
import com.aarshinkov.thesheriff.domain.*;
import com.aarshinkov.thesheriff.domain.game.GameForm;
import com.aarshinkov.thesheriff.memory.Memory;
import com.aarshinkov.thesheriff.memory.objects.Player;
import com.aarshinkov.thesheriff.services.*;
import java.util.*;
import lombok.*;
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
public class GameController extends Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final CalculatorService calculatorService;

  private final Memory memory;

  @GetMapping(value = "/game")
  public String game(Model model) {

    GameForm form = new GameForm();
    List<Player> players = getPlayers();

    List<PlayerResult> playersResult = new ArrayList<>();

    for (Player player : players) {
      PlayerResult playerResult = new PlayerResult();
      playerResult.setPlayer(player);
      playerResult.setCurrentMoney(0);
      playerResult.setLegalCards(getLegalCards());
      playerResult.setBaseContrabandCards(getBaseContrabandCards());
      playerResult.setGoldContrabandCards(getGoldContrabandCards());
      playerResult.setKingContrabandCards(getKingContrabandCards());
      playersResult.add(playerResult);
    }

    form.setPlayersResult(playersResult);

    model.addAttribute("form", form);
    model.addAttribute("players", players);

    return "game";
  }

  @PostMapping(value = "/game")
  public String calculateTotal(GameForm form, Model model) {

//    if (form.getCurrentMoney() == null || form.getCurrentMoney() <= 0) {
//      form.setCurrentMoney(0);
//    }
    log.debug(form.toString());
    model.addAttribute("form", form);
    
//    model.addAttribute("cardsTotal", calculatorService.calculateStockMoney(form.getCards()));
    return "game";
  }

  @GetMapping(value = "/game/clear")
  public String clearGame() {

    memory.clearMemory();

    return "redirect:/players";
  }

  private PlayerResult initPlayerResult(Player player) {

    PlayerResult playerResult = new PlayerResult();
    playerResult.setPlayer(player);
    playerResult.setCurrentMoney(0);
    playerResult.setLegalCards(getLegalCards());
    playerResult.setBaseContrabandCards(getBaseContrabandCards());
    playerResult.setGoldContrabandCards(getGoldContrabandCards());
    playerResult.setKingContrabandCards(getKingContrabandCards());

    return playerResult;
  }

  private List<CardContainer> getLegalCards() {

    List<CardContainer> cards = new ArrayList<>();

    cards.add(new CardContainer(0, new Card("Ябълки", null, 2, 2, "/cards/legal/apples.JPG", false, false)));
    cards.add(new CardContainer(0, new Card("Сирене", null, 3, 2, "/cards/legal/cheese.JPG", false, false)));
    cards.add(new CardContainer(0, new Card("Хляб", null, 3, 2, "/cards/legal/bread.JPG", false, false)));
    cards.add(new CardContainer(0, new Card("Пиле", null, 4, 2, "/cards/legal/chicken.JPG", false, false)));

    return cards;
  }

  private List<CardContainer> getBaseContrabandCards() {

    List<CardContainer> cards = new ArrayList<>();

    // Contraband
    cards.add(new CardContainer(0, new Card("Пипер", null, 6, 4, "/cards/contraband/base/pepper.JPG", true, false)));
    cards.add(new CardContainer(0, new Card("Медовина", null, 7, 4, "/cards/contraband/base/mead.JPG", true, false)));
    cards.add(new CardContainer(0, new Card("Коприна", null, 8, 4, "/cards/contraband/base/silk.JPG", true, false)));
    cards.add(new CardContainer(0, new Card("Арбалет", null, 9, 4, "/cards/contraband/base/crossbow.JPG", true, false)));

    return cards;
  }

  private List<CardContainer> getGoldContrabandCards() {

    List<CardContainer> cards = new ArrayList<>();

    cards.add(new CardContainer(0, new Card("Зелени ябълки", "Брои се за 2 ябълки в края на играта.", 4, 3, "/cards/contraband/gold/green_apples.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Златни ябълки", "Брои се за 3 ябълки в края на играта.", 6, 4, "/cards/contraband/gold/gold_apples.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Ръжен хляб", "Брои се за 2 хляба в края на играта.", 6, 4, "/cards/contraband/gold/rye_bread.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Черен хляб", "Брои се за 3 хляба в края на играта.", 9, 5, "/cards/contraband/gold/black_bread.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Петел", "Брои се за 2 пилета в края на играта.", 8, 4, "/cards/contraband/gold/rooster.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Сирене гауда", "Брои се за 2 сирена в края на играта.", 6, 4, "/cards/contraband/gold/gouda_cheese.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Синьо сирене", "Брои се за 3 сирена в края на играта.", 9, 5, "/cards/contraband/gold/blue_cheese.JPG", true, true)));

    return cards;
  }

  private List<CardContainer> getKingContrabandCards() {

    List<CardContainer> cards = new ArrayList<>();

    cards.add(new CardContainer(0, new Card("Кралска призовка", "Ако те проверят и си казал истината (с изключение на тази карта) шерифът ти дължи 8 допълнителни златни монети.", 0, 0, "/cards/contraband/king/royal_summons.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Празнично блюдо", "Брои се за една произволна легална стока.", 0, 2, "/cards/contraband/king/festive_dish.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Рициново масло", "Ако изчистиш тази карта, вместо да я добавиш към контрабандата на щанда си, може да унищожиш до 2 еднакви легални стоки от щанда на твой опонент.", 6, 4, "/cards/contraband/king/castor_oil.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Лют пипер", "Всяка друга твоя карта с пипер струва +1 злато.", 8, 6, "/cards/contraband/king/cayenne_pepper.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Ягодова медовина", "Всяка друга твоя карта с медовина струва +1 злато.", 8, 6, "/cards/contraband/king/strawberry_mead.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Златна коприна", "Всяка друга твоя карта с коприна струв +1 злато.", 9, 6, "/cards/contraband/king/golden_silk.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Зехтин", null, 10, 5, "/cards/contraband/king/olive_oil.JPG", true, false)));
    cards.add(new CardContainer(0, new Card("Тежък арбалет", "Всяка друга твоя карта с Арбалет струва +2 злато.", 10, 7, "/cards/contraband/king/heavy_crossbow.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Великият настолен трофей", "Ако тази стока се намира в проверена торба, след като се плати глобата, шерифът конфискува трофея, добавяйки го към ръката си, като изчиства друга карта от там.", 11, 5, "/cards/contraband/king/great_table_trophy.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Таен свитък", "Не взимаш злато за кралските бонуси, които си спечелил в края на играта.", 12, 4, "/cards/contraband/king/secret_scroll.JPG", true, true)));
    cards.add(new CardContainer(0, new Card("Мечът на принц Джон", null, 12, 6, "/cards/contraband/king/prince_john_sword.JPG", true, false)));

    return cards;
  }

  private List<CardContainer> initCards() {

    List<CardContainer> cards = new ArrayList<>();

    cards.add(new CardContainer(0, new Card("Пиле", null, 4, 2, "", false, false)));
    cards.add(new CardContainer(0, new Card("Хляб", null, 3, 2, "", false, false)));
    cards.add(new CardContainer(0, new Card("Сирене", null, 3, 2, "", false, false)));
    cards.add(new CardContainer(0, new Card("Ябълки", null, 2, 2, "", false, false)));

    // Contraband
    cards.add(new CardContainer(0, new Card("Пипер", null, 6, 4, "", true, false)));
    cards.add(new CardContainer(0, new Card("Арбалет", null, 9, 4, "", true, false)));
    cards.add(new CardContainer(0, new Card("Коприна", null, 8, 4, "", true, false)));
    cards.add(new CardContainer(0, new Card("Медовина", null, 7, 4, "", true, false)));

    // Golden
    cards.add(new CardContainer(0, new Card("Зелени ябълки", "Брои се за 2 ябълки в края на играта.", 4, 3, "", true, true)));
    cards.add(new CardContainer(0, new Card("Златни ябълки", "Брои се за 3 ябълки в края на играта.", 6, 4, "", true, true)));
    cards.add(new CardContainer(0, new Card("Синьо сирене", "Брои се за 3 сирена в края на играта.", 9, 5, "", true, true)));
    cards.add(new CardContainer(0, new Card("Сирене гауда", "Брои се за 2 сирена в края на играта.", 6, 4, "", true, true)));
    cards.add(new CardContainer(0, new Card("Ръжен хляб", "Брои се за 2 хляба в края на играта.", 6, 4, "", true, true)));
    cards.add(new CardContainer(0, new Card("Черен хляб", "Брои се за 3 хляба в края на играта.", 9, 5, "", true, true)));

    // Blue
    cards.add(new CardContainer(0, new Card("Кралска призовка", "Ако те проверят и си казал истината (с изключение на тази карта) шерифът ти дължи 8 допълнителни златни монети.", 0, 0, "", true, true)));
    cards.add(new CardContainer(0, new Card("Златна коприна", "Всяка друга твоя карта с коприна струв +1 злато.", 9, 6, "", true, true)));
    cards.add(new CardContainer(0, new Card("Великият настолен трофей", "Ако тази стока се намира в проверена торба, след като се плати глобата, шерифът конфискува трофея, добавяйки го към ръката си, като изчиства друга карта от там.", 11, 5, "", true, true)));
    cards.add(new CardContainer(0, new Card("Тежък арбалет", "Всяка друга твоя карта с Арбалет струва +2 злато.", 10, 7, "", true, true)));
    cards.add(new CardContainer(0, new Card("Зехтин", null, 10, 5, "", true, false)));
    cards.add(new CardContainer(0, new Card("Лют пипер", "Всяка друга твоя карта с пипер струва +1 злато.", 8, 6, "", true, true)));
    cards.add(new CardContainer(0, new Card("Рициново масло", "Ако изчистиш тази карта, вместо да я добавиш към контрабандата на щанда си, може да унищожиш до 2 еднакви легални стоки от щанда на твой опонент.", 6, 4, "", true, true)));
    cards.add(new CardContainer(0, new Card("Празнично блюдо", "Брои се за една произволна легална стока.", 0, 2, "", true, true)));
    cards.add(new CardContainer(0, new Card("Таен свитък", "Не взимаш злато за кралските бонуси, които си спечелил в края на играта.", 12, 4, "", true, true)));
    cards.add(new CardContainer(0, new Card("Мечът на принц Джон", null, 12, 6, "", true, false)));

    return cards;
  }
}
