package com.ll.exam.oasisVeganingWeb.user;

import com.ll.exam.oasisVeganingWeb.Community.myPost.MyPost;
import com.ll.exam.oasisVeganingWeb.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SiteUser create(String name, String username, String password, String allergies) throws SignupUsernameDuplicatedException {
    SiteUser user = new SiteUser();
    user.setName(name);
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setAllergies(allergies);
    user.setType("");

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

  public List<MyPost> getUserPosts(String username) {
    SiteUser user = userRepository.findByUsername(username)
        .orElseThrow(() -> new DataNotFoundException("사용자를 찾을 수 없습니다."));

    return user.getMyPosts();
  }
}