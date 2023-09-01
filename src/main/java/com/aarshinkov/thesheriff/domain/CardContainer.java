package com.aarshinkov.thesheriff.domain;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardContainer implements Serializable
{
  private Integer count;
  private Card card;
}
