package book.manager.mapper;

import book.manager.entity.AuthUser;
import org.apache.ibatis.annotations.*;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.mapper
 * @Classname UserMapper
 * @Description TODO
 * @date 2022/4/8 10:39
 */

@Mapper
public interface UserMapper {
  @Select("select * from users where name = #{username}")
  AuthUser getPasswordByUsername(String username);

  //Options 声明是自动生成的主键
//  @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
//  @Insert("insert into users(name, role, password) values(#{username}, #{role},#{password})")
//  int registerUser(AuthUser user);

  @Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
  @Insert("insert into users(name, role, password) values(#{name}, #{role}, #{password})")
  int registerUser(AuthUser user);

  @Insert("insert into student(uid, name, grade, sex) value(#{uid}, #{name},#{grade},#{sex})")
  int addStudentInfo(@Param("uid") int uid, @Param("name") String name, @Param("grade")String grade, @Param("sex")String sex);

}
