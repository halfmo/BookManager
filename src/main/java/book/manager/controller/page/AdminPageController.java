package book.manager.controller.page;

import book.manager.service.AuthService;
import book.manager.service.BookService;
import book.manager.service.impl.UserAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.controller.page
 * @Classname AdminPageController
 * @Description TODO
 * @date 2022/4/25 16:58
 */
@Controller
@RequestMapping("/page/admin")
public class AdminPageController {

  @Resource
  AuthService service;

  @Resource
  BookService bookService;

  @RequestMapping("/index")
  public String index(HttpSession session, Model model){
    model.addAttribute("user", service.findUser(session));
    return "/admin/index";
  }

  @RequestMapping("/book")
  public String book(HttpSession session, Model model){
    model.addAttribute("user", service.findUser(session));
    model.addAttribute("bookList",bookService.getAllBook());
    return "/admin/book";
  }

  @RequestMapping("/add-book")
  public String addBook(HttpSession session, Model model){
    model.addAttribute("user", service.findUser(session));
    return "/admin/add-book";
  }
}
