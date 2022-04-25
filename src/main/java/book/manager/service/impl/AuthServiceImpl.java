package book.manager.service.impl;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.service.impl
 * @Classname AuthServiceImpl
 * @Description TODO
 * @date 2022/4/24 14:32
 */
@Service
public class AuthServiceImpl implements AuthService {

  @Resource
  UserMapper mapper;

  @Transactional
  @Override
  public void register(String name, String sex, String grade, String password) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    AuthUser user = new AuthUser(0,name,encoder.encode(password),"user");
    if (mapper.registerUser(user)<= 0) {
      throw new RuntimeException("add error");
    }
    if (mapper.addStudentInfo(user.getId(),name,grade,sex) <= 0) {
      throw new RuntimeException("infomation insert error");
    }
  }
}
