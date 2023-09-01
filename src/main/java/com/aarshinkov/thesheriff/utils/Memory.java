package com.aarshinkov.thesheriff.utils;

import com.aarshinkov.thesheriff.domain.*;
import java.util.*;
import javax.annotation.*;
import org.slf4j.*;

public class Memory
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  private List<Player> players;

  @PostConstruct
  public void initPlayers()
  {
    log.debug("Initialize available players");
    players = new ArrayList<>();
    players.add(new Player("red", false));
    players.add(new Player("blue", false));
    players.add(new Player("green", false));
    players.add(new Player("yellow", false));
    players.add(new Player("purple", false));
  }

  public List<Player> getAllPlayers()
  {
    return players;
  }

  public List<Player> getNotUsedPlayers()
  {
    List<Player> playersNotUsed = new ArrayList<>();

    for (Player player : players)
    {
      if (!player.getIsUsed())
      {
        playersNotUsed.add(player);
      }
    }
    return playersNotUsed;
  }

  public boolean markPlayerAsUsed(String player)
  {
    for (int i = 0; i < players.size(); i++)
    {
      if (player.equalsIgnoreCase(players.get(i).getPlayer()))
      {
        players.get(i).setIsUsed(true);
        return true;
      }
    }

    return false;
  }
}
