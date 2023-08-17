package com.ll.exam.oasisVeganingWeb.user;

public class SignupUsernameDuplicatedException extends RuntimeException {
  public SignupUsernameDuplicatedException(String message) {
    super(message);
  }
}