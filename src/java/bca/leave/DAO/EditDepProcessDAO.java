package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import bca.leave.model.EditDepProcessBean;
import java.sql.*;

public class EditDepProcessDAO {
    private static Logger log = null;
    public static int Update(EditDepProcessBean b)
    {
        log = Logger.getLogger(EditDepProcessDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("update department set dname=?,hod=? where did=?");
            ps.setString(1, b.getDname());
            ps.setString(2, b.getHod());
            ps.setString(3, b.getId());
            
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The Problem in EditDepProcessDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
