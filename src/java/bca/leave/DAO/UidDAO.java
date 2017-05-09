package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.SelDep;
import java.util.*;

public class UidDAO {
    private static Logger log = null;
    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;

    private static int lastID;
    private static String dep;
    private static String group;
    
    public static int getLastID() { 
        log = Logger.getLogger(UidDAO.class.getName());
        String sql = "select max(uid) from employee";
        
        
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
    
    public static List<SelDep> getDep() throws SQLException
    {
        log = Logger.getLogger(UidDAO.class.getName());
        List<SelDep> b=new ArrayList<SelDep>();
        String sql = "select dname from department";
        
        
        try(
                Connection conn = ConnProvider.getConn();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ) {
            

           while(rs.next())
           {
               SelDep sd = new SelDep();
               sd.setDep(rs.getString("dname"));
               b.add(sd);
               
           }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closeStatement(st);
            ConnProvider.freeConncetion(conn);
        } 
        catch (SQLException e) {
            log.fatal(e);
            System.err.println("the problem is " + e.getCause() + "\n" + e.getMessage());
        } 
        return b;
    }
    
    public static List<SelDep> getGroup() throws SQLException
    {
        log = Logger.getLogger(UidDAO.class.getName());
        List<SelDep> b=new ArrayList<SelDep>();
        String sql = "SELECT  `gname` FROM  `group` ";
        
        
        try(
                Connection conn = ConnProvider.getConn();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                ) {
            

           while(rs.next())
           {
               SelDep s = new SelDep();
               s.setGroup(rs.getString(1));
               b.add(s);
               
           }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closeStatement(st);
            ConnProvider.freeConncetion(conn);
        } 
        catch (SQLException e) {
            log.fatal(e);
            System.err.println("the problem is " + e.getCause() + "\n" + e.getMessage());
        } 
        return b;
    }
}
