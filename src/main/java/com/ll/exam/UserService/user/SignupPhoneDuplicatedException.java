package com.ll.exam.UserService.user;

public class SignupPhoneDuplicatedException extends RuntimeException {
  public SignupPhoneDuplicatedException(String message) {
    super(message);
  }
}