package book.manager.service.impl;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.service.impl
 * @Classname UserAuthServiceImpl
 * @Description TODO
 * @date 2022/4/24 15:02
 */
public class UserAuthServiceImpl implements UserDetailsService {

  @Resource
  UserMapper mapper;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    AuthUser user = mapper.getPasswordByUsername(s);
    if (user==null) {
      throw new UsernameNotFoundException("login error,username or password error");
    }
    return User
            .withUsername(user.getName())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
  }

}
