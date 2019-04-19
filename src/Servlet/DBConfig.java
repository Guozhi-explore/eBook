package Servlet;

import java.sql.*;

public class DBConfig {
    static  {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");     //加载MYSQL JDBC驱动程序
            //Class.forName("org.gjt.mm.mysql.Driver");
        }
        catch (Exception e) {
            System.out.print("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ebook","root","");
            //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
            Statement stmt = connect.createStatement();
            return connect;
        }
        catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
        return null;
    }
}