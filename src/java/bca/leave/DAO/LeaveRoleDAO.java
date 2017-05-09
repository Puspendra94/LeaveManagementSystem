package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.LeaveRoleBean;

public class LeaveRoleDAO {
    private static Logger log = null;
    public static boolean Valid(LeaveRoleBean b)
    {
        log = Logger.getLogger(LeaveRoleDAO.class.getName());
        boolean status=false;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("SELECT cleave,aleave,sleave FROM `group` WHERE gname=? ");
            ps.setString(1, b.getRole());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                
                b.setCl(rs.getInt("cleave"));
                b.setAl(rs.getInt("aleave"));
                b.setSl(rs.getInt("sleave"));
                
                status = true; 
            }
            
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in LeaveRoleDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
