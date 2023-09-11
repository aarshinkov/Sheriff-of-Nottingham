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
public class CardContainer implements Serializable {

  private Integer count;
  private Card card;

  public Integer getCount() {
    return this.count == null ? 0 : this.count;
  }
}
