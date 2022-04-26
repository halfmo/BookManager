package book.manager.service;

import book.manager.entity.AuthUser;

import javax.servlet.http.HttpSession;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.service
 * @Classname AuthService
 * @Description TODO
 * @date 2022/4/21 17:54
 */
public interface AuthService {
  void register(String name,String sex,String grade,String password);

  AuthUser findUser(HttpSession session);
}
