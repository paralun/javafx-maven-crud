package com.paralun.app.utility;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.paralun.app.service.BarangDao;
import com.paralun.app.service.impl.BarangDaoImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUtil {
    
    private static Connection connection;
    private static BarangDao barangDao;
    
    public static Connection getConnection() {
        if(connection == null){
            try {
                MysqlDataSource source = new MysqlDataSource();
                source.setURL("jdbc:mysql://localhost:3306/javafx_db");
                source.setUser("root");
                source.setPassword("");
                connection = source.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }

    public static BarangDao getBarangDao() {
        if(barangDao == null){
            barangDao = new BarangDaoImpl(getConnection());
        }
        return barangDao;
    }
}
