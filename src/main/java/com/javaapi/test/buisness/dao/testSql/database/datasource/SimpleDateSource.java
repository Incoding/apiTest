package com.javaapi.test.buisness.dao.testSql.database.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;
/**
 * http://lavasoft.blog.51cto.com/62575/265073/
 *
 */
public class SimpleDateSource implements DataSource { 
//    private static Log log = LogFactory.getLog(SimpleDateSource.class); 
    private static final String dirverClassName = "com.mysql.jdbc.Driver"; 
    private static final String url = "jdbc:mysql://127.0.0.1:3306/testdb"; 
    private static final String user = "root"; 
    private static final String pswd = "leizhimin"; 
    //连接池 
    private static LinkedList<Connection> pool = (LinkedList<Connection>) Collections.synchronizedList(new LinkedList<Connection>()); 
    private static SimpleDateSource instance = new SimpleDateSource(); 

    static { 
            try { 
                    Class.forName(dirverClassName); 
            } catch (ClassNotFoundException e) { 
//                    log.error("找不到驱动类！", e); 
            } 
    } 

	/*
	 * sss
	 */
    private SimpleDateSource() { 
    } 

    /** 
     * 获取数据源单例 
     * 
     * @return 数据源单例 
     */ 
    public SimpleDateSource instance() { 
            if (instance == null) instance = new SimpleDateSource(); 
            return instance; 
    } 

    /** 
     * 获取一个数据库连接 
     * 
     * @return 一个数据库连接 
     * @throws SQLException 
     */ 
    @Override
    public Connection getConnection() throws SQLException { 
            synchronized (pool) { 
                    if (pool.size() > 0) return pool.removeFirst(); 
                    else return makeConnection(); 
            } 
    } 

    /** 
     * 连接归池 
     * 
     * @param conn 
     */ 
    public static void freeConnection(Connection conn) { 
            pool.addLast(conn); 
    } 

    private Connection makeConnection() throws SQLException { 
            return DriverManager.getConnection(url, user, pswd); 
    } 
    @Override
    public Connection getConnection(String username, String password) throws SQLException { 
            return DriverManager.getConnection(url, username, password); 
    } 

    public PrintWriter getLogWriter() throws SQLException { 
            return null; 
    } 

    public void setLogWriter(PrintWriter out) throws SQLException { 

    } 

    public void setLoginTimeout(int seconds) throws SQLException { 

    } 

    public int getLoginTimeout() throws SQLException { 
            return 0; 
    } 

    public <T> T unwrap(Class<T> iface) throws SQLException { 
            return null; 
    } 

    public boolean isWrapperFor(Class<?> iface) throws SQLException { 
            return false; 
    }

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	} 
}
