package com.aarshinkov.thesheriff.controllers;

import com.aarshinkov.thesheriff.domain.*;
import com.aarshinkov.thesheriff.services.*;
import java.util.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  private final CalculatorService calculatorService;

  @GetMapping(value = "/")
  public String home(Model model)
  {
    List<CardContainer> cards = initCards();
    CardForm form = new CardForm();
    form.setCards(cards);

    model.addAttribute("form", form);

    return "home";
  }

  @PostMapping(value = "/")
  public String calculateTotal(CardForm form, Model model)
  {
    if (form.getCurrentMoney() == null || form.getCurrentMoney() <= 0)
    {
      form.setCurrentMoney(0);
    }

    model.addAttribute("form", form);

    model.addAttribute("cardsTotal", calculatorService.calculateStockMoney(form.getCards()));

    return "home";
  }

  private List<CardContainer> initCards()
  {

    List<CardContainer> cards = new ArrayList<>();

    cards.add(new CardContainer(0, new Card("Пиле", null, 4, 2, false, false)));
    cards.add(new CardContainer(0, new Card("Хляб", null, 3, 2, false, false)));
    cards.add(new CardContainer(0, new Card("Сирене", null, 3, 2, false, false)));
    cards.add(new CardContainer(0, new Card("Ябълки", null, 2, 2, false, false)));

    // Contraband
    cards.add(new CardContainer(0, new Card("Пипер", null, 6, 4, true, false)));
    cards.add(new CardContainer(0, new Card("Арбалет", null, 9, 4, true, false)));
    cards.add(new CardContainer(0, new Card("Коприна", null, 8, 4, true, false)));
    cards.add(new CardContainer(0, new Card("Медовина", null, 7, 4, true, false)));

    // Golden
    cards.add(new CardContainer(0, new Card("Зелени ябълки", "Брои се за 2 ябълки в края на играта.", 4, 3, true, true)));
    cards.add(new CardContainer(0, new Card("Златни ябълки", "Брои се за 3 ябълки в края на играта.", 6, 4, true, true)));
    cards.add(new CardContainer(0, new Card("Синьо сирене", "Брои се за 3 сирена в края на играта.", 9, 5, true, true)));
    cards.add(new CardContainer(0, new Card("Сирене гауда", "Брои се за 2 сирена в края на играта.", 6, 4, true, true)));
    cards.add(new CardContainer(0, new Card("Ръжен хляб", "Брои се за 2 хляба в края на играта.", 6, 4, true, true)));
    cards.add(new CardContainer(0, new Card("Черен хляб", "Брои се за 3 хляба в края на играта.", 9, 5, true, true)));

    // Blue
    cards.add(new CardContainer(0, new Card("Кралска призовка", "Ако те проверят и си казал истината (с изключение на тази карта) шерифът ти дължи 8 допълнителни златни монети.", 0, 0, true, true)));
    cards.add(new CardContainer(0, new Card("Златна коприна", "Всяка друга твоя карта с коприна струв +1 злато.", 9, 6, true, true)));
    cards.add(new CardContainer(0, new Card("Великият настолен трофей", "Ако тази стока се намира в проверена торба, след като се плати глобата, шерифът конфискува трофея, добавяйки го към ръката си, като изчиства друга карта от там.", 11, 5, true, true)));
    cards.add(new CardContainer(0, new Card("Тежък арбалет", "Всяка друга твоя карта с Арбалет струва +2 злато.", 10, 7, true, true)));
    cards.add(new CardContainer(0, new Card("Зехтин", null, 10, 5, true, false)));
    cards.add(new CardContainer(0, new Card("Лют пипер", "Всяка друга твоя карта с пипер струва +1 злато.", 8, 6, true, true)));
    cards.add(new CardContainer(0, new Card("Рициново масло", "Ако изчистиш тази карта, вместо да я добавиш към контрабандата на щанда си, може да унищожиш до 2 еднакви легални стоки от щанда на твой опонент.", 6, 4, true, true)));
    cards.add(new CardContainer(0, new Card("Празнично блюдо", "Брои се за една произволна легална стока.", 0, 2, true, true)));
    cards.add(new CardContainer(0, new Card("Таен свитък", "Не взимаш злато за кралските бонуси, които си спечелил в края на играта.", 12, 4, true, true)));
    cards.add(new CardContainer(0, new Card("Мечът на принц Джон", null, 12, 6, true, false)));

    return cards;
  }
}
