package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import java.sql.*;

public class DeleteNotifDAO {
    private static Logger log = null;
    public static int Delete(int id)
    {
        log = Logger.getLogger(DeleteNotifDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("delete from notif where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in DeleteNotifDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
