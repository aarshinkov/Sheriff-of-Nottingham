package com.aarshinkov.thesheriff.services;

import com.aarshinkov.thesheriff.domain.CardContainer;
import com.aarshinkov.thesheriff.domain.game.*;
import com.aarshinkov.thesheriff.domain.game.total.*;
import java.util.*;
import lombok.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

  @Override
  public TotalResult calculateTotalResult(List<PlayerResult> playersResult) {

    TotalResult totalResult = new TotalResult();

    List<PlayerTotal> playersTotal = new ArrayList<>();

    for (PlayerResult playerResult : playersResult) {
      PlayerTotal playerTotal = new PlayerTotal();
      playerTotal.setPlayer(playerResult.getPlayer());
      playerTotal.setCashMoney(playerResult.getCurrentMoney());
      playerTotal.setCardMoney(calculateCardsMoney(playerResult.getLegalCards(), playerResult.getBaseContrabandCards(), playerResult.getGoldContrabandCards(), playerResult.getKingContrabandCards()));
      playersTotal.add(playerTotal);
    }

    totalResult.setPlayersTotal(playersTotal);

    List<PlayerTotal> winners = new ArrayList<>();
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

      if (cardCount >= kingsCardsCount) {
        kingsCardsCount = cardCount;
      }
    }

    // Queens Max
    for (List<CardContainer> playerCards : playersCards) {
      Integer cardCount = playerCards.get(legalCardType.getNumber()).getCount();

      if (cardCount >= queensCardsCount && cardCount < kingsCardsCount) {
        queensCardsCount = cardCount;
      }
    }

    // Determining players
    for (int i = 0; i < playersCards.length; i++) {
      Integer cardCount = playersCards[i].get(legalCardType.getNumber()).getCount();

      if (cardCount == kingsCardsCount) {
        kingPlayersIndexes.add(i);
      }

      if (cardCount == queensCardsCount) {
        queenPlayersIndexes.add(i);
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
}
