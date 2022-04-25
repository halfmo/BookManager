package book.manager.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletContext;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.initializer
 * @Classname SecurityInitializer
 * @Description TODO
 * @date 2022/4/7 18:38
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
  //不用重写任何内容
  //这里实际上会自动注册一个Filter，SpringSecurity底层就是依靠N个过滤器实现的，我们之后再探讨

  //将以下的过滤器置顶.可以使用中文进行登录
  @Override
  protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
    servletContext.addFilter("characterEncodingFilter",new CharacterEncodingFilter("UTF-8",true))
            .addMappingForUrlPatterns(null,false,"/*");
  }
}
