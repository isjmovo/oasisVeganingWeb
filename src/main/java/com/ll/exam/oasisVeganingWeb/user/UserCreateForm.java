package com.ll.exam.oasisVeganingWeb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
  @NotEmpty(message = "이름은 필수 항목입니다.")
  private String name;

  @NotEmpty(message = "사용자 ID는 필수 항목입니다.")
  @Email(message = "올바른 이메일 형식으로 입력해주세요.")
  private String username;

  @NotEmpty(message = "비밀번호는 필수 항목입니다.")
  private String password1;

  @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
  private String password2;

//  private String allergy;
}