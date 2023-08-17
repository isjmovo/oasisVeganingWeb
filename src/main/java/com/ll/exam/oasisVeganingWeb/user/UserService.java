package com.ll.exam.oasisVeganingWeb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SiteUser create(String name, String username, String email, String password, String phone, String address, String allergy) throws SignupUsernameDuplicatedException, SignupPhoneDuplicatedException {
    SiteUser user = new SiteUser();
    user.setName(name);
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    user.setPhone(phone);
    user.setAddress(address);
    user.setAllergy(allergy);

    try {
      userRepository.save(user);
    } catch (DataIntegrityViolationException e) {
      if (userRepository.existsByUsername(username)) {
        throw new SignupUsernameDuplicatedException("이미 사용 중인 아이디입니다.");
      } else {
        throw new SignupPhoneDuplicatedException("이미 사용 중인 전화번호입니다.");
      }
    }

    return user;
  }
}