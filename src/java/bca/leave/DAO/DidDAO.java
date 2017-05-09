package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;

public class DidDAO {
    
    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;

    private static int lastID;
    private static Logger log = null;
    
    public static int getLastID() { 
        
        String sql = "select max(dnum) from department";
        log = Logger.getLogger(DidDAO.class.getName());
        
        try {
            conn = ConnProvider.getConn();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                lastID = rs.getInt(1);
            } 
            else {
                lastID = 0;
            }
            
           ConnProvider.closeResultSet(rs);
           ConnProvider.closeStatement(st);
           ConnProvider.freeConncetion(conn);
        } 
        catch (SQLException e) {
            log.fatal(e);
            System.err.println("the problem is " + e.getCause() + "\n" + e.getMessage());
        } 
        return lastID;
    }
    
    

    
}
