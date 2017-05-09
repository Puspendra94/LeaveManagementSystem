package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.model.DeleteDepartmentBean;
import bca.leave.util.ConnProvider;
import java.sql.*;

public class DeleteDepartmentDAO {
    private static Logger log = null;
    public static int delete(DeleteDepartmentBean b)
    {
        log = Logger.getLogger(DeleteDepartmentDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("delete from department where did=?");
            ps.setString(1, b.getDid());
            
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in DeleteDepartmentDAO is "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
