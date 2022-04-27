package book.manager.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.entity
 * @Classname Borrow
 * @Description TODO
 * @date 2022/4/27 16:31
 */

@Data
public class Borrow {
  int id;
  int bid;
  int sid;
  Date date;
}
