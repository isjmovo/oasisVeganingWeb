package com.ll.exam.oasisVeganingWeb.security;

import com.ll.exam.oasisVeganingWeb.user.SiteUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class SiteUserContext extends User implements OAuth2User {
  private final Long id;
  private final String email;
  private final  String profileImgUrl;

  private Map<String, Object> attributes;
  private String userNameAttributeName;

  public SiteUserContext(SiteUser siteUser, List<GrantedAuthority> authorities) {
    super(siteUser.getUsername(), siteUser.getPassword(), authorities);
    this.id = siteUser.getId();
    this.email = siteUser.getEmail();
    this.profileImgUrl = getProfileImgUrl();
  }

  public SiteUserContext(SiteUser siteUser, List<GrantedAuthority> authorities, Map<String, Object> attributes, String userNameAttributeName) {
    this(siteUser, authorities);
    this.attributes = attributes;
    this.userNameAttributeName = userNameAttributeName;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return this.attributes;
  }

  @Override
  public Set<GrantedAuthority> getAuthorities() {
    return super.getAuthorities().stream().collect(Collectors.toSet());
  }

  @Override
  public String getName() {
    return this.getAttribute(this.userNameAttributeName).toString();
  }
}