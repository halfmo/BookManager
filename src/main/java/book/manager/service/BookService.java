package book.manager.service;

import book.manager.entity.Book;
import book.manager.entity.BorrowDetails;

import java.util.List;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.service.impl
 * @Classname BookService
 * @Description TODO
 * @date 2022/4/26 21:40
 */
public interface BookService {
  List<Book> getAllBook();
  List<Book> getAllBookWithOutBorrow();
  List<Book> getAllBorrowedBookById(int id);
  void deleteBook(int bid);
  void addBook(String title, String desc, double price);
  void borrowBook(int bid, int id);
  void returnBook(int bid, int id);
  List<BorrowDetails> getBorrowDetailsList();
}
