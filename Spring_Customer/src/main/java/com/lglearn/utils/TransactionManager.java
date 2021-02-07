package com.lglearn.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionManager {

    private static volatile TransactionManager transactionManager;

    public static synchronized TransactionManager getInstance() {
        if (transactionManager == null) {
            transactionManager = new TransactionManager();
        }
        return transactionManager;
    }

    public void beginTransaction() throws SQLException {
        ConnectionUtil.getCurrentThreadCon().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        ConnectionUtil.getCurrentThreadCon().commit();
    }

    public void close() throws SQLException {
        ConnectionUtil.getCurrentThreadCon().close();
    }

    public void rollBack() throws SQLException {
        ConnectionUtil.getCurrentThreadCon().rollback();
    }

}
