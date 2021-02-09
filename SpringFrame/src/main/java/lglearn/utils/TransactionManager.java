package lglearn.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("transactionManager")
public class TransactionManager {

    @Autowired
    private ConnectionUtil connectionUtil;

    public void beginTransaction() throws SQLException {
        connectionUtil.getCurrentThreadCon().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connectionUtil.getCurrentThreadCon().commit();
    }

    public void close() throws SQLException {
        connectionUtil.getCurrentThreadCon().close();
    }

    public void rollBack() throws SQLException {
        connectionUtil.getCurrentThreadCon().rollback();
    }

}
