package com.aarshinkov.thesheriff.base;

import com.aarshinkov.thesheriff.domain.*;
import com.aarshinkov.thesheriff.utils.*;
import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class Base
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private Memory memory;

  public List<Player> getNotUsedPlayers()
  {
    return memory.getNotUsedPlayers();
  }

  public boolean markPlayerAsUsed(String player)
  {
    return memory.markPlayerAsUsed(player);
  }
}
