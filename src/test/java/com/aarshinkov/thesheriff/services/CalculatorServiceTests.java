package com.aarshinkov.thesheriff.services;

import com.aarshinkov.thesheriff.domain.Card;
import com.aarshinkov.thesheriff.domain.CardContainer;
import com.aarshinkov.thesheriff.domain.game.total.KingsAndQueens;
import com.aarshinkov.thesheriff.domain.game.total.LegalCardType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public class CalculatorServiceTests {

  private CalculatorService calculatorService;

  @BeforeEach
  public void init() {
    calculatorService = new CalculatorServiceImpl();
  }

  @Test
  public void test_kingsAndQueens_one() {

    List<CardContainer> cards1 = new ArrayList<>();
    cards1.add(new CardContainer(1, new Card()));

    List<CardContainer> cards2 = new ArrayList<>();
    cards2.add(new CardContainer(6, new Card()));

    List<CardContainer> cards3 = new ArrayList<>();
    cards3.add(new CardContainer(6, new Card()));
    
    KingsAndQueens result = calculatorService.calculateCardsKingsAndQueens(LegalCardType.APPLES, cards1, cards2, cards3);

    assertNotNull(result);
    
    // Kings
    assertNotNull(result.getKingPlayersIndexes());
    assertEquals(6, result.getKingCardCount());
    assertEquals(2, result.getKingPlayersIndexes().size());
    assertEquals(1, result.getKingPlayersIndexes().get(0));
    assertEquals(2, result.getKingPlayersIndexes().get(1));
    
    // Queens
    assertNotNull(result.getQueenPlayersIndexes());
    assertEquals(1, result.getQueenCardCount());
    assertEquals(1, result.getQueenPlayersIndexes().size());
    assertEquals(0, result.getQueenPlayersIndexes().get(0));
  }
  
  @Test
  public void test_kingsAndQueens_two() {

    List<CardContainer> cards1 = new ArrayList<>();
    cards1.add(new CardContainer(1, new Card()));

    List<CardContainer> cards2 = new ArrayList<>();
    cards2.add(new CardContainer(7, new Card()));

    List<CardContainer> cards3 = new ArrayList<>();
    cards3.add(new CardContainer(3, new Card()));
    
    KingsAndQueens result = calculatorService.calculateCardsKingsAndQueens(LegalCardType.APPLES, cards1, cards2, cards3);

    assertNotNull(result);
    
    // Kings
    assertNotNull(result.getKingPlayersIndexes());
    assertEquals(7, result.getKingCardCount());
    assertEquals(1, result.getKingPlayersIndexes().size());
    assertEquals(1, result.getKingPlayersIndexes().get(0));
    
    // Queens
    assertNotNull(result.getQueenPlayersIndexes());
    assertEquals(3, result.getQueenCardCount());
    assertEquals(1, result.getQueenPlayersIndexes().size());
    assertEquals(2, result.getQueenPlayersIndexes().get(0));
  }
  
  @Test
  public void test_kingsAndQueens_three() {

    List<CardContainer> cards1 = new ArrayList<>();
    cards1.add(new CardContainer(8, new Card()));

    List<CardContainer> cards2 = new ArrayList<>();
    cards2.add(new CardContainer(8, new Card()));

    List<CardContainer> cards3 = new ArrayList<>();
    cards3.add(new CardContainer(8, new Card()));
    
    KingsAndQueens result = calculatorService.calculateCardsKingsAndQueens(LegalCardType.APPLES, cards1, cards2, cards3);

    assertNotNull(result);
    
    // Kings
    assertNotNull(result.getKingPlayersIndexes());
    assertEquals(8, result.getKingCardCount());
    assertEquals(3, result.getKingPlayersIndexes().size());
    assertEquals(0, result.getKingPlayersIndexes().get(0));
    assertEquals(1, result.getKingPlayersIndexes().get(1));
    assertEquals(2, result.getKingPlayersIndexes().get(2));
    
    // Queens
    assertNotNull(result.getQueenPlayersIndexes());
    assertEquals(0, result.getQueenCardCount());
    assertEquals(0, result.getQueenPlayersIndexes().size());
  }
}
