package book.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.entity
 * @Classname AuthUser
 * @Description TODO
 * @date 2022/4/9 10:01
 */

@Data
@AllArgsConstructor
public class AuthUser {
  int id;
  String name;
  String password;
  String role;
}
