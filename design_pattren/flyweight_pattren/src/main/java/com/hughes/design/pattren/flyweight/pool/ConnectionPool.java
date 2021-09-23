package com.hughes.design.pattren.flyweight.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;


public class ConnectionPool {

    private final Vector<Connection> pool;

    private String url = "jdbc:mysql://localhost:3306/shun_demo";
    private String username = "root";
    private String password = "root";
    private String driverClassName = "com.mysql.jdbc.Driver";
    private int poolSize = 10;

    public ConnectionPool() {
        pool = new Vector<Connection>(poolSize);

        try{
            Class.forName(driverClassName);
            for (int i = 0; i < poolSize; i++) {
                Connection conn = DriverManager.getConnection(url,username,password);
                pool.add(conn);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public synchronized Connection getConnection(){
        if(pool.size() > 0){
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        }
        return null;
    }

    public synchronized void release(Connection conn){
        pool.add(conn);
    }
}
