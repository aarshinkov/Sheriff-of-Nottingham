package com.aarshinkov.thesheriff.domain.game;

import com.aarshinkov.thesheriff.domain.game.total.PlayerTotal;
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
public class TotalResult implements Serializable {

  private List<PlayerTotal> playersTotal;
  private List<PlayerTotal> winners;
}
