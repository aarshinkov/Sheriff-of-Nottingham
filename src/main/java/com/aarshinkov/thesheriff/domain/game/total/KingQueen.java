package com.aarshinkov.thesheriff.domain.game.total;

import java.io.Serializable;
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
public class KingQueen implements Serializable {

  private LegalCardType legalCardType;
  private Integer kingPrice;
  private Integer queenPrice;

}
