package com.aarshinkov.thesheriff.utils;

import com.aarshinkov.thesheriff.domain.game.total.KingQueen;
import com.aarshinkov.thesheriff.domain.game.total.LegalCardType;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class GameConstants {

  private final Integer minimumPlayers = 3;
  private final Integer maximumPlayers = 5;

  public static final KingQueen APPLES_KING_QUEEN = new KingQueen(LegalCardType.APPLES, 20, 10);
  public static final KingQueen CHEESE_KING_QUEEN = new KingQueen(LegalCardType.CHEESE, 15, 10);
  public static final KingQueen BREAD_KING_QUEEN = new KingQueen(LegalCardType.BREAD, 15, 10);
  public static final KingQueen CHICKEN_KING_QUEEN = new KingQueen(LegalCardType.CHICKEN, 10, 5);

  public Integer getMinimumPlayers() {
    return minimumPlayers;
  }

  public Integer getMaximumPlayers() {
    return maximumPlayers;
  }
}
