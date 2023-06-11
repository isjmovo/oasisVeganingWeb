package com.ll.exam.UserService.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
  @NotEmpty(message = "이름은 필수 항목입니다.")
  private String name;

  @Size(min = 3, max = 25, message = "사용자 ID는 3자 이상, 25자 이하로 입력해주세요.")
  @NotEmpty(message = "사용자 ID는 필수 항목입니다.")
  private String username;

  @NotEmpty(message = "비밀번호는 필수 항목입니다.")
  private String password1;

  @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
  private String password2;

  @Email(message = "올바른 이메일 형식으로 입력해주세요.")
  private String email;

  @NotEmpty(message = "전화번호는 필수 항목입니다.")
  private String phone;

  private String address;

  private String allergy;
}