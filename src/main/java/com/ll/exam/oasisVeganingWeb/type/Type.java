package com.ll.exam.oasisVeganingWeb.type;

public enum Type {
  A("비건"),
  B("락토"),
  C("오보"),
  D("락토 오보"),
  E("페스코"),
  F("폴로"),
  UNDEFINED("플렉시테리언");

  private final String description;

  Type(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}