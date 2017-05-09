package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import bca.leave.model.UpdateBalanceLeaveBean;
import java.sql.*;

public class UpdateBalanceLeaveDAO {
    private static Logger log= null;
    public static int Update(UpdateBalanceLeaveBean b)
    {
        log = Logger.getLogger(UpdateBalanceLeaveDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("update employee set bcleave=?,baleave=?,bsleave=?,dutyleave=?,lwpay=?,spl=?,anyother=? where name=?");
            ps.setInt(1, b.getCl());
            ps.setInt(2, b.getAl());
            ps.setInt(3, b.getSl());
            ps.setInt(4, b.getDl());
            ps.setInt(5, b.getLwp());
            ps.setInt(6, b.getSpl());
            ps.setInt(7, b.getAo());
            
            ps.setString(8, b.getFrom());
            
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in UpdateBalanceLeaveDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
    
}
