package book.manager.controller.api;

import book.manager.entity.AuthUser;
import book.manager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.annotation.Resource;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.controller.api
 * @Classname UserApiController
 * @Description TODO
 * @date 2022/4/27 15:52
 */

@Controller
@RequestMapping("/api/user")
public class UserApiController {

  @Resource
  BookService service;

  @RequestMapping(value = "/borrow-book", method = RequestMethod.GET)
  public String borrowBook(@RequestParam("id") int bid,
                           @SessionAttribute("user") AuthUser user){
    service.borrowBook(bid, user.getId());
    return "redirect:/page/user/book";
  }

  @RequestMapping(value = "/return-book", method = RequestMethod.GET)
  public String returnBook(@RequestParam("id") int bid,
                           @SessionAttribute("user")AuthUser user){
    service.returnBook(bid, user.getId());
    return "redirect:/page/user/book";
  }
}
