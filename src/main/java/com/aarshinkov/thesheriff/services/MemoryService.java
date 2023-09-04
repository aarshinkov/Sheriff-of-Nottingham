package com.aarshinkov.thesheriff.services;

import com.aarshinkov.thesheriff.domain.Hero;
import com.aarshinkov.thesheriff.memory.objects.Player;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface MemoryService {

  void storeObjectToMemory(String key, Object value);

  <T> T getObjectFromMemory(String key, Class<T> castClass);

  <T> List<T> getListObjectFromMemory(String key, Class<T> castClass);

  Map<String, Object> getStoredObjects();

  Boolean clearMemory();

  List<Hero> getHeroes();

  List<Hero> getFreeHeroes();

  Boolean markHeroAsUsed(String hero);

  List<Player> getPlayers();

}
