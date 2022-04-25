package book.manager.controller.page;


import book.manager.mapper.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.controller
 * @Classname AuthPageController
 * @Description TODO
 * @date 2022/4/7 18:14
 */

@Controller
@RequestMapping("/page/auth")
public class AuthPageController {

  @Mapper
  UserMapper mapper;

  //访问时是一定登录成功@SessionAttribute(value = "user",required = false) AuthUser user ,
//  @RequestMapping("/index")
//  public String index(HttpSession session, Model model){
//    AuthUser user = (AuthUser) session.getAttribute("user");
//    if (user == null) {
//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//      user = mapper.getPasswordByUsername(authentication.getName());
//      session.setAttribute("user",user);
//    }
//    model.addAttribute("user",user);
//    return "admin/index";
//  }

  @RequestMapping("/login")
  public String login(){
    return "login";
  }

  @RequestMapping("/register")
  public String register(){
    return "register";
  }
}
