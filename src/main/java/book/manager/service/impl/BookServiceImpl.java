package book.manager.service.impl;

import book.manager.entity.Book;
import book.manager.mapper.BookMapper;
import book.manager.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.service.impl
 * @Classname BookServiceImpl
 * @Description TODO
 * @date 2022/4/26 21:41
 */

@Service
public class BookServiceImpl implements BookService {

  @Resource
  BookMapper mapper;



  @Override
  public List<Book> getAllBook() {
    return mapper.allBook();
  }

  @Override
  public void deleteBook(int bid) {
    mapper.deleteBook(bid);
  }

  @Override
  public void addBook(String title, String desc, double price) {
    mapper.addBook(title, desc, price);
  }
}
