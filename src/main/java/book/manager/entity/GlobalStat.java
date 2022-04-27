package book.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.entity
 * @Classname GlobalStat
 * @Description TODO
 * @date 2022/4/27 21:33
 */

@Data
@AllArgsConstructor
public class GlobalStat {

  int userCount;
  int bookCount;
  int borrowCount;
}
