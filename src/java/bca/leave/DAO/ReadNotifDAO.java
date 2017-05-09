package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import java.sql.*;

public class ReadNotifDAO {
    private static Logger log = null;
    public static int Read(String name)
    {
        log = Logger.getLogger(ReadNotifDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("update notif set nstatus='read' where nstatus='unread' and notif.to='"+name+"' or nstatus='unread' and notif.fwdby='"+name+"'");
            
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in ReadNotifDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
