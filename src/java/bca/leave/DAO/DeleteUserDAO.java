package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.UserViewBean;

public class DeleteUserDAO {
    private static Logger log= null;
    public static int Delete(UserViewBean b)
    {
        log = Logger.getLogger(DeleteUserDAO.class.getName());
        int status=0;
        
        try
        {
            Connection conn=ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("delete from employee where uid=?");
            ps.setString(1, b.getId());
            status=ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem from Delete user DaO is "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
