package com.aarshinkov.thesheriff.utils;

import org.springframework.stereotype.Component;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class GameConstants {
  
  private final Integer minimumPlayers = 3; 
  private final Integer maximumPlayers = 5;

  public Integer getMinimumPlayers() {
    return minimumPlayers;
  }

  public Integer getMaximumPlayers() {
    return maximumPlayers;
  }
}
