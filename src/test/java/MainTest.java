import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author half_m
 * @version 1.0
 * @Package PACKAGE_NAME
 * @Classname MainTest
 * @Description TODO
 * @date 2022/4/8 10:21
 */
public class MainTest {
  @Test
  public void test(){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println(encoder.encode("123456"));
  }
}
