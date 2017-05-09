package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import java.sql.*;
import bca.leave.model.GroupBean;

public class DeleteGroupDAO {
    private static Logger log = null;
    public static int DeleteGroup(GroupBean b)
    {
        log = Logger.getLogger(DeleteGroupDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM `group` WHERE gname=?");
            ps.setString(1, b.getName());
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in DeleteGroupDAo is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
    public static int Update(String gname)
    {
        log = Logger.getLogger(DeleteGroupDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("update employee set role='Default',bcleave=12-(cleave-bcleave),cleave=12,baleave=12-(aleave-baleave),aleave=12,bsleave=12-(sleave-bsleave),sleave=12 where role=?");
            ps.setString(1, gname); 
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception j)
        {
            log.fatal(j);
            System.err.println("The problem in UpdateEmployeeGroupDAO is :"+j.getCause()+"\n"+j.getMessage());
        }
        return status;
    }
    
    
}
