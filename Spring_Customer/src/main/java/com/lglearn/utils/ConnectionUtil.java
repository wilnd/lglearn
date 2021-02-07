package com.lglearn.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionUtil {
    //当前线程绑定
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static synchronized Connection getCurrentThreadCon() {
        if (threadLocal.get() == null) {
            try {
                Connection connection = DruidUtils.getInstance().getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return threadLocal.get();
    }

}
