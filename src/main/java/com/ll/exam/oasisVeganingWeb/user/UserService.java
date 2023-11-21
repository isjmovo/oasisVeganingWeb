package com.ll.exam.oasisVeganingWeb.user;

import com.ll.exam.oasisVeganingWeb.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

//  public SiteUser create(String name, String username, String password, String allergy) throws SignupUsernameDuplicatedException {
  public SiteUser create(String name, String username, String password) throws SignupUsernameDuplicatedException {
    SiteUser user = new SiteUser();
    user.setName(name);
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
//    user.setAllergy(allergy);

    try {
      userRepository.save(user);
    } catch (DataIntegrityViolationException e) {
      if (userRepository.existsByUsername(username)) {
        throw new SignupUsernameDuplicatedException("이미 사용 중인 아이디입니다.");
      }
    }

    return user;
  }

  public SiteUser getUser(String username) {
    return userRepository.findByUsername(username).orElseThrow(() -> new DataNotFoundException("siteuser not found"));
  }
}