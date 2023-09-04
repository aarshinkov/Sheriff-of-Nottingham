package com.aarshinkov.thesheriff.domain;

import java.io.*;
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
public class Card implements Serializable {

  private String name;
  private String description;
  private Integer price;
  private Integer fine;
  private Boolean isContraband = true;
  private Boolean hasEffect = false;
}
