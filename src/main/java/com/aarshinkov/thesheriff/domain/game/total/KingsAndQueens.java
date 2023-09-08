package com.aarshinkov.thesheriff.domain.game.total;

import java.io.Serializable;
import java.util.List;
import lombok.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KingsAndQueens implements Serializable {
  
  private Integer kingCardCount;
  private List<Integer> kingPlayersIndexes;
  private Integer queenCardCount;
  private List<Integer> queenPlayersIndexes;

}
