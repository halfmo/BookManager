package book.manager.config;


import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.impl.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author half_m
 * @version 1.0
 * @Package book.manager.config
 * @Classname SecurityConfiguration
 * @Description TODO
 * @date 2022/4/7 18:39
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  /**
   * 继承WebSecurityConfigurerAdapter，之后会进行配置
   */

  @PostConstruct
  public void init() {
    SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
  }

  @Resource
  UserMapper mapper;
  @Resource
  UserAuthService service;

  @Resource
  PersistentTokenRepository repository;

  //使用数据库来记录Token信息，来实现remember me 功能
  @Bean
  public PersistentTokenRepository jdbcRepository(@Autowired DataSource dataSource) {
    JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();  //使用基于JDBC的实现
    repository.setDataSource(dataSource);   //配置数据源
    //repository.setCreateTableOnStartup(true);   //启动时自动创建用于存储Token的表（建议第一次启动之后删除该行）
    return repository;
  }

  //  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//            .authorizeRequests()   //首先需要配置哪些请求会被拦截，哪些请求必须具有什么角色才能访问
//            .antMatchers("/static/**","/page/auth/**","/api/auth/**").permitAll()    //静态资源，使用permitAll来运行任何人访问（注意一定要放在前面）
//            .antMatchers("/page/user/**").hasRole("user")
//            .antMatchers("/page/admin/**").hasRole("admin")
////            .antMatchers("/index").hasAnyAuthority("page:index")   //index页面可以由user或admin访问
//            .anyRequest().hasAnyRole("user","admin")   //除了上面以外的所有内容，只能是admin访问
//            .and()
//            .formLogin()       //配置Form表单登陆
//            .loginPage("/page/auth/login")       //登陆页面地址（GET）
//            .loginProcessingUrl("/api/auth/login")    //form表单提交地址（POST）
////            .defaultSuccessUrl("/index", true)    //登陆成功后跳转的页面，也可以通过Handler实现高度自定义
//            .successHandler(this::onAuthenticationSuccess)
////            .permitAll()    //登陆页面也需要允许所有人访问
//            .and()
//            .logout()
//            .logoutUrl("/api/auth/logout")    //退出登陆的请求地址
//            .logoutSuccessUrl("/login")  //退出后重定向的地址
//            .and()
//            .csrf().disable()
//            .rememberMe()
//            //开启记住我功能
//            .rememberMeParameter("remember")  //登陆请求表单中需要携带的参数，如果携带，那么本次登陆会被记住
////            .tokenRepository(new InMemoryTokenRepositoryImpl());  //这里使用的是直接在内存中保存的TokenRepository实现
//            .tokenRepository(repository)
//            .tokenValiditySeconds(60 * 60 * 24 * 7);  //Token的有效时间（秒）默认为14天
//  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/static/**", "/page/auth/**", "/api/auth/**").permitAll()
            .antMatchers("/page/user/**", "/api/user/**").hasRole("user")
            .antMatchers("/page/admin/**", "/api/admin/**").hasRole("admin")
            .anyRequest().hasAnyRole("user", "admin")
            .and()
            .formLogin()
            .loginPage("/page/auth/login")
            .loginProcessingUrl("/api/auth/login")
            .successHandler(this::onAuthenticationSuccess)
            .and()
            .logout()
            .logoutUrl("/api/auth/logout")
            .logoutSuccessUrl("/login")
            .and()
            .csrf().disable()
            .rememberMe()
            .rememberMeParameter("remember")
            .tokenValiditySeconds(60 * 60 * 24 * 7)
            .tokenRepository(repository);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(service)
            .passwordEncoder(new BCryptPasswordEncoder());
//    auth
//            .userDetailsService(service)   //使用自定义的Service实现类进行验证
//            .passwordEncoder(new BCryptPasswordEncoder());   //依然使用BCryptPasswordEncoder
  }


  //认证成功后会调用以下方法
  private void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
    HttpSession session = httpServletRequest.getSession();
    AuthUser user = mapper.getPasswordByUsername(authentication.getName());
    session.setAttribute("user", user);
    if (user.getRole().equals("admin")) {
      httpServletResponse.sendRedirect("/bookManager/page/admin/index");
    }else {
      httpServletResponse.sendRedirect("/bookManager/page/user/index");
    }

  }
}