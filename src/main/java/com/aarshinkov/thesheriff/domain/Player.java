package com.aarshinkov.thesheriff.domain;

import java.io.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Player implements Serializable
{
  private String player;
  private Boolean isUsed;
}
