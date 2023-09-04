package com.aarshinkov.thesheriff.memory;

import com.aarshinkov.thesheriff.domain.Hero;
import static com.aarshinkov.thesheriff.utils.AppConstants.HEROES_KEY;
import java.util.*;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class Memory {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private Map<String, Object> memory;

  @PostConstruct
  public void memoryInit() {
    
    log.debug("Initializing game memory");
    memory = new HashMap<>();

    initializeHeroes();
  }

  public void storeObjectToMemory(String key, Object value) {
    memory.put(key, value);
  }

  public <T> T getObjectFromMemory(String key, Class<T> castClass) {
    return (T) memory.get(key);
  }

  public <T> List<T> getListObjectFromMemory(String key, Class<T> castClass) {
    return (List<T>) memory.get(key);
  }

  public Map<String, Object> getStoredObjects() {
    return memory;
  }

  public Boolean clearMemory() {
    
    log.debug("Clearing game memory");

    try {

      memory.clear();
      memoryInit();
      
      return true;

    } catch (Exception e) {
      log.error("Unable to clear memory");
      return false;
    }
  }

  private void initializeHeroes() {
    
    log.debug("Initializing heroes");
    List<Hero> heroes = new ArrayList<>();
    heroes.add(new Hero("red", false));
    heroes.add(new Hero("blue", false));
    heroes.add(new Hero("green", false));
    heroes.add(new Hero("yellow", false));
    heroes.add(new Hero("purple", false));

    memory.put(HEROES_KEY, heroes);
  }
}
