package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.model.EditAppliedLeaveBean;
import bca.leave.util.ConnProvider;
import java.sql.*;

public class EditAppliedLeaveDAO {
    private static Logger log = null;
    public static boolean Edit(EditAppliedLeaveBean b)
    {
        log = Logger.getLogger(EditAppliedLeaveDAO.class.getName());
        boolean status = false;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("select * from message where msgid=?");
            ps.setInt(1, b.getId());
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                b.setFrom(rs.getString("from"));
                b.setLtype(rs.getString("ltype"));
                b.setFod(rs.getString("fromdate"));
                b.setTod(rs.getString("todate"));
                b.setDays(rs.getInt("days"));
                b.setTo(rs.getString("to"));
                b.setFwdby(rs.getString("fwdby"));
                b.setMsg(rs.getString("msg"));
                b.setCl(rs.getInt("cl"));
                b.setAl(rs.getInt("al"));
                b.setSl(rs.getInt("sl"));
                b.setDl(rs.getInt("dl"));
                b.setLwp(rs.getInt("lwp"));
                b.setSpl(rs.getInt("spl"));
                b.setAo(rs.getInt("ao"));
                
                status = true;
                
                ConnProvider.closeResultSet(rs);
                ConnProvider.closePreStatement(ps);
                ConnProvider.freeConncetion(conn);
            }
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in EditAppliedLeaveDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
