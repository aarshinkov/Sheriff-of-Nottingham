package com.aarshinkov.thesheriff.domain;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Card implements Serializable
{
  private String name;
  private String description;
  private Integer price;
  private Integer fine;
  private Boolean isContraband = true;
  private Boolean hasEffect = false;
}
