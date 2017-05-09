package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;

public class AutoNotifCountDAO {
    
     private static Logger log = null;
    public static int get(String uname)
    {
        log = Logger.getLogger(AutoNotifCountDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("SELECT count(*) from notif where  nstatus=? and notif.to=? and fwdby=? or  nstatus=? and fwdby=? and status=? or nstatus=? and notif.to=? and status!=?");
            ps.setString(1, "unread");
            ps.setString(2, uname);
            ps.setString(3, "NONE");
            ps.setString(4, "unread");
            ps.setString(5, uname);
            ps.setString(6, "pending");
            ps.setString(7, "unread");
            ps.setString(8, uname);
            ps.setString(9, "pending");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                status = rs.getInt("count(*)");
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in DAO2 is: "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
