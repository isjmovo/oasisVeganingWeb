package com.ll.exam.UserService.user;

public class SignupEmailDuplicatedException extends RuntimeException {
  public SignupEmailDuplicatedException(String message) {
    super(message);
  }
}