package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import bca.leave.model.UpdateGroupBean;
import java.sql.*;

public class UpdateGroupDAO {
    private static Logger log = null;
    public static int UpdateGroup(UpdateGroupBean b)
    {
        log = Logger.getLogger(UpdateGroupDAO.class.getName());
        int status=0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("UPDATE `group` SET `cleave`=?,`aleave`=?,`sleave`=? WHERE `gname`=?");
            ps.setInt(1, b.getCl());
            ps.setInt(2, b.getAl());
            ps.setInt(3, b.getSl());
            ps.setString(4, b.getGname());
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in UpdateGroupDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
    public static int UpdateEmployee(UpdateGroupBean b)
    {
        log = Logger.getLogger(UpdateGroupDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("update employee set role=?,bcleave=?-(cleave-bcleave),cleave=?,baleave=?-(aleave-baleave),aleave=?,bsleave=?-(sleave-bsleave),sleave=? where role=?");
            ps.setString(1, b.getGname());
            ps.setInt(2, b.getCl());
            ps.setInt(3, b.getCl());
            ps.setInt(4, b.getAl());
            ps.setInt(5, b.getAl());
            ps.setInt(6, b.getSl());
            ps.setInt(7, b.getSl());
            ps.setString(8, b.getGname());
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in UpdateEmployee of UpdateGroupDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
