package com.eaton.db.mysql;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * Created by yitgeng on 11/2/2017.
 * JDBC Utils Class
 */
public class JDBCUtils {
    private static Connection con ;
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    static{
        try{
            readConfig();
            Class.forName(driverClass);
            con = DriverManager.getConnection(url, username, password);
        }catch(Exception ex){
            throw new RuntimeException("database connection failure!");
        }
    }

    private static void readConfig()throws Exception{
        InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("conf/mysql.properties");
        Properties pro = new Properties();
        pro.load(in);
        driverClass=pro.getProperty("driverClass");
        url = pro.getProperty("url");
        username = pro.getProperty("username");
        password = pro.getProperty("password");
    }

    public static Connection getConnection(){
        return con;
    }

    public static void close(Connection con, Statement stat){

        if(stat!=null){
            try{
                stat.close();
            }catch(SQLException ex){}
        }

        if(con!=null){
            try{
                con.close();
            }catch(SQLException ex){}
        }

    }

    public static void close(Connection con, Statement stat, ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException ex){}
        }

        if(stat!=null){
            try{
                stat.close();
            }catch(SQLException ex){}
        }

        if(con!=null){
            try{
                con.close();
            }catch(SQLException ex){}
        }

    }

}
