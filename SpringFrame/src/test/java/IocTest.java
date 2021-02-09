import lglearn.dao.AccountDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTest {

    @Test
    public void testIoc(){
        //java se 下xml推荐使用
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("绝对路径");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao);
        AccountDao accountDao2 = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao2);
    }

}
