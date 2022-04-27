package book.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.entity
 * @Classname Book
 * @Description TODO
 * @date 2022/4/26 20:40
 */

@Data
@AllArgsConstructor
public class Book {
  int bid;
  String title;
  String desc;
  double price;
}
