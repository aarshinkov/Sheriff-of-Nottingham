package com.aarshinkov.thesheriff.services;

import com.aarshinkov.thesheriff.domain.CardContainer;
import com.aarshinkov.thesheriff.domain.game.*;
import com.aarshinkov.thesheriff.domain.game.total.*;
import static com.aarshinkov.thesheriff.utils.GameConstants.APPLES_KING_QUEEN;
import static com.aarshinkov.thesheriff.utils.GameConstants.BREAD_KING_QUEEN;
import static com.aarshinkov.thesheriff.utils.GameConstants.CHEESE_KING_QUEEN;
import static com.aarshinkov.thesheriff.utils.GameConstants.CHICKEN_KING_QUEEN;
import java.util.*;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public TotalResult calculateTotalResult(List<PlayerResult> playersResult) {

    TotalResult totalResult = new TotalResult();

    List<PlayerTotal> playersTotal = new ArrayList<>();

    List<CardContainer>[] legalCardsList = new ArrayList[playersResult.size()];

    for (int i = 0; i < playersResult.size(); i++) {

      PlayerResult playerResult = playersResult.get(i);
      legalCardsList[i] = playerResult.getLegalCards();

      PlayerTotal playerTotal = new PlayerTotal();
      playerTotal.setPlayer(playerResult.getPlayer());
      playerTotal.setCashMoney(playerResult.getCurrentMoney());
      playerTotal.setCardMoney(calculateCardsMoney(playerResult.getLegalCards(), playerResult.getBaseContrabandCards(), playerResult.getGoldContrabandCards(), playerResult.getKingContrabandCards()));
      playersTotal.add(playerTotal);
    }

    KingsAndQueens applesKingsAndQueens = calculateCardsKingsAndQueens(LegalCardType.APPLES, legalCardsList);
    KingsAndQueens cheeseKingsAndQueens = calculateCardsKingsAndQueens(LegalCardType.CHEESE, legalCardsList);
    KingsAndQueens breadKingsAndQueens = calculateCardsKingsAndQueens(LegalCardType.BREAD, legalCardsList);
    KingsAndQueens chickenKingsAndQueens = calculateCardsKingsAndQueens(LegalCardType.CHICKEN, legalCardsList);

//    for (int i = 0; i < applesKingsAndQueens.getKingPlayersIndexes().size(); i++) {
//      for (int j = 0; j < auxPlayersTotal.size(); j++) {
//        if (applesKingsAndQueens.getKingPlayersIndexes().get(i) == j) {
//          if (applesKingsAndQueens.getKingPlayersIndexes().size() > 1) {
//            auxPlayersTotal.get(j).addKingAndQueenMoney((APPLES_KING_QUEEN.getKingPrice() + APPLES_KING_QUEEN.getQueenPrice()) / applesKingsAndQueens.getKingPlayersIndexes().size());
//          } else {
//            auxPlayersTotal.get(j).addKingAndQueenMoney(APPLES_KING_QUEEN.getKingPrice() / applesKingsAndQueens.getKingPlayersIndexes().size());
//          }
//        }
//      }
//    }
//
//    if (applesKingsAndQueens.getKingPlayersIndexes().size() == 1) {
//      for (int i = 0; i < applesKingsAndQueens.getQueenPlayersIndexes().size(); i++) {
//        for (int j = 0; j < auxPlayersTotal.size(); j++) {
//          if (applesKingsAndQueens.getQueenPlayersIndexes().get(i) == j) {
//            auxPlayersTotal.get(j).addKingAndQueenMoney(APPLES_KING_QUEEN.getQueenPrice() / applesKingsAndQueens.getQueenPlayersIndexes().size());
//          }
//        }
//      }
//    }
    playersTotal = assignKingsAndQueensMoney(playersTotal, applesKingsAndQueens, APPLES_KING_QUEEN);
    playersTotal = assignKingsAndQueensMoney(playersTotal, cheeseKingsAndQueens, CHEESE_KING_QUEEN);
    playersTotal = assignKingsAndQueensMoney(playersTotal, breadKingsAndQueens, BREAD_KING_QUEEN);
    playersTotal = assignKingsAndQueensMoney(playersTotal, chickenKingsAndQueens, CHICKEN_KING_QUEEN);

    totalResult.setPlayersTotal(playersTotal);

    List<PlayerTotal> winners = new ArrayList<>();

    Integer winnerMoney = 0;

    for (PlayerTotal playerTotal : playersTotal) {
      if (playerTotal.getTotal() >= winnerMoney) {
        winnerMoney = playerTotal.getTotal();
      }
    }

    for (PlayerTotal playerTotal : playersTotal) {
      if (playerTotal.getTotal().equals(winnerMoney)) {
        winners.add(playerTotal);
        playerTotal.setIsWinner(Boolean.TRUE);
      }
    }

    totalResult.setWinners(winners);

    return totalResult;
  }

  @Override
  public KingsAndQueens calculateCardsKingsAndQueens(LegalCardType legalCardType, List<CardContainer>... playersCards) {

    List<Integer> kingPlayersIndexes = new ArrayList<>();
    List<Integer> queenPlayersIndexes = new ArrayList<>();

    int kingsCardsCount = 0;
    int queensCardsCount = 0;

    // Kings Max
    for (List<CardContainer> playerCards : playersCards) {

      Integer cardCount = playerCards.get(legalCardType.getNumber()).getCount();

      if (cardCount > 0) {
        if (cardCount >= kingsCardsCount) {
          kingsCardsCount = cardCount;
        }
      }
    }

    // Queens Max
    for (List<CardContainer> playerCards : playersCards) {

      Integer cardCount = playerCards.get(legalCardType.getNumber()).getCount();

      if (cardCount > 0) {
        if (cardCount >= queensCardsCount && cardCount < kingsCardsCount) {
          queensCardsCount = cardCount;
        }
      }
    }

    // Determining players
    for (int i = 0; i < playersCards.length; i++) {

      Integer cardCount = playersCards[i].get(legalCardType.getNumber()).getCount();

      if (cardCount > 0) {
        if (cardCount == kingsCardsCount) {
          kingPlayersIndexes.add(i);
        }

        if (cardCount == queensCardsCount) {
          queenPlayersIndexes.add(i);
        }
      }
    }

    KingsAndQueens kingsAndQueens = new KingsAndQueens();
    kingsAndQueens.setKingCardCount(kingsCardsCount);
    kingsAndQueens.setKingPlayersIndexes(kingPlayersIndexes);
    kingsAndQueens.setQueenCardCount(queensCardsCount);
    kingsAndQueens.setQueenPlayersIndexes(queenPlayersIndexes);

    return kingsAndQueens;
  }

  private Integer calculateCardsMoney(List<CardContainer> legalCards, List<CardContainer> baseContrabandCards, List<CardContainer> goldContrabandCards, List<CardContainer> kingContrabandCards) {

    Integer total = 0;

    total += calculateCardsTypeTotal(legalCards);
    total += calculateCardsTypeTotal(baseContrabandCards);
    total += calculateCardsTypeTotal(goldContrabandCards);
    total += calculateCardsTypeTotal(kingContrabandCards);

    return total;
  }

  private Integer calculateCardsTypeTotal(List<CardContainer> cards) {

    Integer total = 0;

    for (CardContainer cardContainer : cards) {
      total += (cardContainer.getCount() * cardContainer.getCard().getPrice());
    }

    return total;
  }

  private List<PlayerTotal> assignKingsAndQueensMoney(List<PlayerTotal> playersTotal, KingsAndQueens kingsAndQueens, KingQueen kingQueen) {

    for (int i = 0; i < kingsAndQueens.getKingPlayersIndexes().size(); i++) {
      for (int j = 0; j < playersTotal.size(); j++) {
        if (kingsAndQueens.getKingPlayersIndexes().get(i) == j) {
          if (kingsAndQueens.getKingPlayersIndexes().size() > 1) {
            playersTotal.get(j).addKingAndQueenMoney((kingQueen.getKingPrice() + kingQueen.getQueenPrice()) / kingsAndQueens.getKingPlayersIndexes().size());
          } else {
            playersTotal.get(j).addKingAndQueenMoney(kingQueen.getKingPrice() / kingsAndQueens.getKingPlayersIndexes().size());
          }
        }
      }
    }

    if (kingsAndQueens.getKingPlayersIndexes().size() == 1) {
      for (int i = 0; i < kingsAndQueens.getQueenPlayersIndexes().size(); i++) {
        for (int j = 0; j < playersTotal.size(); j++) {
          if (kingsAndQueens.getQueenPlayersIndexes().get(i) == j) {
            playersTotal.get(j).addKingAndQueenMoney(kingQueen.getQueenPrice() / kingsAndQueens.getQueenPlayersIndexes().size());
          }
        }
      }
    }

    return playersTotal;
  }
}
