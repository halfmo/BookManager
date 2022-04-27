package book.manager.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.entity
 * @Classname BorrowDetails
 * @Description TODO
 * @date 2022/4/27 17:31
 */
@Data
public class BorrowDetails {
  int id;
  String book_title;
  String user_name;
  Date time;
}
