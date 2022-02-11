import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.utils.ConnectionUtilsSelf;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTest {

    @Test
    public void testIoc(){
        // 读取classpath下的xml文件来启动容器（xml模式下的javase应用推荐使用）
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans.xml");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        AccountDao accountDao1 = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println("accountDao" + accountDao);
        System.out.println("accountDao1" + accountDao1);
        ConnectionUtilsSelf connectionUtils = (ConnectionUtilsSelf) applicationContext.getBean("connectionUtils");
        System.out.println(connectionUtils);
    }
}
