package com.aarshinkov.thesheriff.domain.game.total;

import com.aarshinkov.thesheriff.memory.objects.Player;
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
public class PlayerTotal implements Serializable {

  private Player player;
  private Integer cashMoney;
  private Integer cardMoney;
  private Integer kingAndQueenMoney;

  public void addKingAndQueenMoney(Integer money) {

    if (kingAndQueenMoney == null || kingAndQueenMoney < 0) {
      kingAndQueenMoney = 0;
    }
    
    kingAndQueenMoney += money;
  }

  public Integer getTotal() {

    if (cashMoney == null || cashMoney < 0) {
      cashMoney = 0;
    }

    if (cardMoney == null || cashMoney < 0) {
      cardMoney = 0;
    }

    if (kingAndQueenMoney == null || kingAndQueenMoney < 0) {
      kingAndQueenMoney = 0;
    }

    return cashMoney + cardMoney + kingAndQueenMoney;
  }
}
