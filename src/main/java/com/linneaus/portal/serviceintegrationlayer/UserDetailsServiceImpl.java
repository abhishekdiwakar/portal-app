package com.linneaus.portal.serviceintegrationlayer;

import com.linneaus.portal.domain.Role;
import com.linneaus.portal.domain.User;
import com.linneaus.portal.repository.UserRepo;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), "",
        createGrantedAuthorities(user.getRole()));
  }

  private Set<GrantedAuthority> createGrantedAuthorities(Role role) {
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
    return grantedAuthorities;
  }
}
