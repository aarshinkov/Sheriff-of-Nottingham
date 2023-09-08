package com.aarshinkov.thesheriff.domain.game.total;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public enum LegalCardType {

  APPLES(0, "Apples"),
  CHEESE(1, "Cheese"),
  BREAD(2, "Bread"),
  CHICKEN(3, "Chicken");

  private final Integer number;
  private final String name;

  LegalCardType(Integer number, String name) {
    this.number = number;
    this.name = name;
  }

  public Integer getNumber() {
    return number;
  }

  public String getName() {
    return name;
  }
}
