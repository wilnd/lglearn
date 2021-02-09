package lglearn.utils;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component("connectionUtil")
public class ConnectionUtil {

    @Autowired
    private DruidDataSource druidDataSource;

    //当前线程绑定
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public synchronized Connection getCurrentThreadCon() {
        if (threadLocal.get() == null) {
            try {
                Connection connection = druidDataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return threadLocal.get();
    }

}
