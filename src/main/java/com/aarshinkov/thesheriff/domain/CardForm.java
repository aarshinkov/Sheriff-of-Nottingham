package com.aarshinkov.thesheriff.domain;

import java.io.*;
import java.util.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardForm implements Serializable
{
  private Integer currentMoney;
  private List<CardContainer> cards;
}
