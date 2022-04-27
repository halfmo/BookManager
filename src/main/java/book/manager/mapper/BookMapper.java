package book.manager.mapper;

import book.manager.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.mapper
 * @Classname BookMapper
 * @Description TODO
 * @date 2022/4/26 20:37
 */

@Mapper
public interface BookMapper {

  @Select("select * from book")
  List<Book> allBook();

  @Delete("delete  from book where bid = #{bid}")
  void deleteBook(int bid);

  @Insert("insert into book(title,`desc`,price) value (#{title}, #{desc},#{price})")
  void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price);

}
