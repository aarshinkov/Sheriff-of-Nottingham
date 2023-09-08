package com.aarshinkov.thesheriff.domain.game;

import com.aarshinkov.thesheriff.domain.CardContainer;
import com.aarshinkov.thesheriff.memory.objects.Player;
import java.io.*;
import java.util.*;
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
public class PlayerResult implements Serializable {

  private Player player;
  private Integer currentMoney;
  private List<CardContainer> legalCards;
  private List<CardContainer> baseContrabandCards;
  private List<CardContainer> goldContrabandCards;
  private List<CardContainer> kingContrabandCards;
}
