package com.aarshinkov.thesheriff.base;

import com.aarshinkov.thesheriff.domain.Hero;
import com.aarshinkov.thesheriff.memory.objects.Player;
import com.aarshinkov.thesheriff.services.MemoryService;
import java.util.List;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class Base {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private MemoryService memoryService;

  public List<Hero> getFreeHeroes() {
    return memoryService.getFreeHeroes();
  }

  public List<Player> getPlayers() {
    return memoryService.getPlayers();
  }
}
