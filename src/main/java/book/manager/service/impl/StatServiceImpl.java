package book.manager.service.impl;

import book.manager.entity.GlobalStat;
import book.manager.mapper.BookMapper;
import book.manager.mapper.UserMapper;
import book.manager.service.BookService;
import book.manager.service.StatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.service.impl
 * @Classname StatServiceImpl
 * @Description TODO
 * @date 2022/4/27 21:39
 */

@Service
public class StatServiceImpl implements StatService {

  @Resource
  UserMapper userMapper;
  @Resource
  BookMapper bookMapper;

  @Override
  public GlobalStat getGlobalStat() {
    return new GlobalStat(userMapper.getStudentCount(),
            bookMapper.getBookCount(),
            bookMapper.getBorrowCount());
  }
}
