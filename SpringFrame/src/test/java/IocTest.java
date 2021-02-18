import lglearn.SpringConfig;
import lglearn.dao.AccountDao;
import lglearn.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTest {

    @Test
    public void testIoc() {
        //java se 下xml推荐使用
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("绝对路径");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao);
        AccountDao accountDao2 = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao2);
    }

    @Test
    public void testAnnotation() throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
//        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
//        System.out.println(accountDao);
//        AccountDao accountDao2 = (AccountDao) applicationContext.getBean("accountDao");
//        System.out.println(accountDao2);
        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("6029621011000", "6029621011001", 10);
    }

    @Test
    public void testAop() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("6029621011000", "6029621011001", 100);
    }

}
