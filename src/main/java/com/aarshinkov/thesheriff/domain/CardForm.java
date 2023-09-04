package com.aarshinkov.thesheriff.domain;

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
public class CardForm implements Serializable {

  private Integer currentMoney;
  private List<CardContainer> cards;
}
