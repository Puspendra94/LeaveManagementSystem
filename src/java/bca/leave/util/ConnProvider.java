package bca.leave.util;
import org.apache.log4j.*;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnProvider {
    
   
    private static Logger log = null;
    private static InitialContext ic = null;
    private static DataSource ds = null;

    
    private static void initMethod() {

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/leave");
        } 
        catch (Exception e) {
            log.fatal(e);
            System.err.println("the problem is " + e.getCause() + "\n" + e.getMessage());
        }
    }

        public static Connection getConn() {

        Connection con = null;

        if (ic == null || ds == null) {
            initMethod();
        }

        try {
            con = ds.getConnection();
        } 
        catch (SQLException ex) {
            log.fatal(ex);
            System.err.println("the problem is " + ex.getCause() + "\n" + ex.getMessage());
        }

        return con;

    }

        public static void freeConncetion(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } 
        catch (Exception ex) {
            log.fatal(ex);
            System.err.println("the problem is " + ex.getCause() + "\n" + ex.getMessage());
        }
    }

    
        public static void closeResultSet(ResultSet res) {
        try {
            if (res != null) {
                res.close();
            }
        }
        catch (Exception ex) {
            log.fatal(ex);
            System.err.println("the problem is " + ex.getCause() + "\n" + ex.getMessage());
        }
    }

        public static void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        }
        catch (Exception ex) {
            log.fatal(ex);
            System.err.println("the problem is " + ex.getCause() + "\n" + ex.getMessage());
        }
    }

        public static void closePreStatement(PreparedStatement pst) {
        try {
            if (pst != null) {
                pst.close();
            }
        } 
        catch (Exception ex) {
            log.fatal(ex);
            System.err.println("the problem is " + ex.getCause() + "\n" + ex.getMessage());
        }
    }


}
