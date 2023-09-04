package com.aarshinkov.thesheriff.memory.objects;

import org.slf4j.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class AvailableHeroes {

  private final Logger log = LoggerFactory.getLogger(getClass());

//  private List<Hero> heroes;
//
//  @PostConstruct
//  public void initPlayers() {
//
//    log.debug("Initialize available players");
//    heroes = new ArrayList<>();
//    heroes.add(new Hero("red", false));
//    heroes.add(new Hero("blue", false));
//    heroes.add(new Hero("green", false));
//    heroes.add(new Hero("yellow", false));
//    heroes.add(new Hero("purple", false));
//  }
//
//  public List<Hero> getAllHeroes() {
//    return heroes;
//  }
//
//  public List<Hero> getFreeHeroes() {
//
//    List<Hero> freeHeroes = new ArrayList<>();
//
//    for (Hero hero : heroes) {
//      if (!hero.getIsUsed()) {
//        freeHeroes.add(hero);
//      }
//    }
//    return freeHeroes;
//  }
//
//  public boolean markHeroAsUsed(String hero) {
//
//    for (int i = 0; i < heroes.size(); i++) {
//      if (hero.equalsIgnoreCase(heroes.get(i).getHero())) {
//        heroes.get(i).setIsUsed(true);
//        return true;
//      }
//    }
//
//    return false;
//  }
}
