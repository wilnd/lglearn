package com.lglearn.persistent.lesson1;

import com.google.common.collect.Lists;
import com.lglearn.persistent.lesson1.model.User;

import java.sql.*;
import java.util.List;

public class LearnJdbc {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //问题1 jdbc的配置信息存在硬编码 可以提取到项目配置文件
            //问题2 每次请求都开启新的连接,实际是比较耗费资源的 采用连接池
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lglearn?characterEncoding=utf-8", "root", "123456");
            //问题3 sql语句,设置参数,获取结果集均存在硬编码问题 提取到专门的sql配置文件
            String sql = "select * from user where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "tom");
            resultSet = preparedStatement.executeQuery();

            List<User> users = Lists.newArrayList();
            while (resultSet.next()) {
                User user = new User();
                //问题4 需要手动封装返回结果集   使用反射,内省技术
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                users.add(user);
            }
            users.forEach(user -> System.out.println(user.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
