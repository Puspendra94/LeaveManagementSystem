package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import java.sql.*;
import bca.leave.model.GetBalanceLeaveBean;

public class GetBalanceLeaveDAO {
    private static Logger log = null;
    public static boolean getBal(GetBalanceLeaveBean b)
    {
        log = Logger.getLogger(GetBalanceLeaveDAO.class.getName());
        
        boolean status=false;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps =conn.prepareStatement("select bcleave,baleave,bsleave,dutyleave,lwpay,spl,anyother from employee where uname=?");
            ps.setString(1, b.getUname()); 
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                b.setCl(rs.getInt("bcleave"));
                System.err.println("Clevae is: "+rs.getInt("bcleave"));
                b.setAl(rs.getInt("baleave")); 
                b.setSl(rs.getInt("bsleave"));
                b.setDl(rs.getInt("dutyleave"));
                b.setLwp(rs.getInt("lwpay"));
                b.setSpl(rs.getInt("spl"));
                b.setAo(rs.getInt("anyother"));
                status = true;
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in GetBalanceLeaveDAO is: "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
