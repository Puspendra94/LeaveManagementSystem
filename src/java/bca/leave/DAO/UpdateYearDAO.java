package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import java.sql.*;

public class UpdateYearDAO {
    private static Logger log = null;
    public static int Update(int y)
    {
        log = Logger.getLogger(UpdateYearDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("update employee set bcleave=cleave,baleave=aleave,bsleave=sleave,dutyleave=0,lwpay=0,spl=0,anyother=0,cyear=?");
            ps.setInt(1, y);
            
            PreparedStatement pss = conn.prepareStatement("update admin set cyear=?");
            pss.setInt(1, y);
            status = ps.executeUpdate();
            status = pss.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.closePreStatement(pss);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in UpdateYearDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
