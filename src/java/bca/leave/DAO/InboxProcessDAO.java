package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;

public class InboxProcessDAO {
    private static Logger log = null;
    public static int Update(int id,String action)
    {
        log = Logger.getLogger(InboxProcessDAO.class.getName());
        int status=0;
        try
        {
            Connection conn=ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("update message set status=? where msgid=?");
            ps.setString(1, action);
            ps.setInt(2, id);
            
            status=ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
            
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem is from InboxProcessDAO "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
    public static int Delete(int id)
    {
        log = Logger.getLogger(InboxProcessDAO.class.getName());
        int status=0;
        try
        {
            Connection conn=ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("update message set trash=? where msgid=?");
            ps.setString(1, "yes");
            ps.setInt(2, id); 
            
            status=ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem is "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
    
    
}
