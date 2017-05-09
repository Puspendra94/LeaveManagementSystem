package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import java.sql.*;

public class TrashDAO {
    private static Logger log= null;
    public static int TrashUpdate(int id,String action)
    {
        log = Logger.getLogger(TrashDAO.class.getName());
        int status=0;
        String msg;
        Connection conn=ConnProvider.getConn();
        try
        {
            PreparedStatement ps;
           if(action.equals("Restore"))
            {
                ps=conn.prepareStatement("update message set trash=? where msgid=?");
                ps.setString(1, "no");
                ps.setInt(2, id); 
            }
           else
            {
                ps=conn.prepareStatement("delete from message where msgid=?");
                ps.setInt(1, id); 
            }
                
           status=ps.executeUpdate();
           
           ConnProvider.closePreStatement(ps);
           ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in TrashDAo.restore is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
   
    
}
