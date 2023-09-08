package com.aarshinkov.thesheriff.domain.game;

import java.io.Serializable;
import java.util.List;
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
public class GameForm implements Serializable {

  private List<PlayerResult> playersResult;

}
