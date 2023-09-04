package com.aarshinkov.thesheriff.services;

import com.aarshinkov.thesheriff.domain.Hero;
import com.aarshinkov.thesheriff.memory.Memory;
import com.aarshinkov.thesheriff.memory.objects.Player;
import static com.aarshinkov.thesheriff.utils.AppConstants.HEROES_KEY;
import static com.aarshinkov.thesheriff.utils.AppConstants.PLAYERS_KEY;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private final Memory memory;

  @Override
  public void storeObjectToMemory(String key, Object value) {
    memory.storeObjectToMemory(key, value);
  }

  @Override
  public <T> T getObjectFromMemory(String key, Class<T> castClass) {
    return memory.getObjectFromMemory(key, castClass);
  }

  @Override
  public <T> List<T> getListObjectFromMemory(String key, Class<T> castClass) {
    return memory.getListObjectFromMemory(key, castClass);
  }

  @Override
  public Map<String, Object> getStoredObjects() {
    return memory.getStoredObjects();
  }

  @Override
  public Boolean clearMemory() {
    return memory.clearMemory();
  }

  @Override
  public List<Hero> getHeroes() {
    List<Hero> heroes = memory.getListObjectFromMemory(HEROES_KEY, Hero.class);
    return heroes != null ? heroes : new ArrayList<>();
  }

  @Override
  public List<Hero> getFreeHeroes() {

    List<Hero> heroes = getHeroes();

    List<Hero> freeHeroes = new ArrayList<>();

    for (Hero hero : heroes) {
      if (!hero.getIsUsed()) {
        freeHeroes.add(hero);
      }
    }

    return freeHeroes;
  }

  @Override
  public Boolean markHeroAsUsed(String hero) {

    List<Hero> heroes = getHeroes();

    for (Hero storedHero : heroes) {

      if (storedHero.getHero().equalsIgnoreCase(hero)) {

        storedHero.setIsUsed(Boolean.TRUE);

        log.debug(Arrays.toString(heroes.toArray()));

        memory.storeObjectToMemory(HEROES_KEY, heroes);

        return true;
      }
    }

    return false;
  }

  @Override
  public List<Player> getPlayers() {
    return memory.getListObjectFromMemory(PLAYERS_KEY, Player.class);
  }
}
