package com.ll.exam.oasisVeganingWeb.user;

public class SignupPhoneDuplicatedException extends RuntimeException {
  public SignupPhoneDuplicatedException(String message) {
    super(message);
  }
}