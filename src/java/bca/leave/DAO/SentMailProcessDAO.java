package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import java.sql.*;

public class SentMailProcessDAO {
 
    private static Logger log = null;
    
    public static int Delete(int id)
    {
        log = Logger.getLogger(SentMailProcessDAO.class.getName());
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
             System.err.println("The problem in SentMailProcessDAO Cancel is :"+e.getCause()+"\n"+e.getMessage()); 
        }
        return status;
    }
    
    public static int Cancel(int id)
    {
        log = Logger.getLogger(SentMailProcessDAO.class.getName());
        int status=0;
        try
        {
            Connection conn=ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("UPDATE `message` SET `status`=?,`cancel`=? WHERE msgid=?");
            ps.setString(1, "Canceled");
            ps.setString(2, "yes");
//            ps.setString(2, "Canceled");
            ps.setInt(3, id); 
            
            status=ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in SentMailProcessDAO Cancel is :"+e.getCause()+"\n"+e.getMessage()); 
        }
        return status;
    }
    
}
