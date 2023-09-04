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
public class Hero implements Serializable {

  private String hero;
  private Boolean isUsed;
}
