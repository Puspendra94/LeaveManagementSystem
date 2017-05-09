package bca.leave.DAO;

import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.EditAppliedLeaveBean;
import org.apache.log4j.Logger;

public class UpdateAppliedLeaveDAO {
    private static Logger log = null;
    
    public static int Validate(EditAppliedLeaveBean b)
    {
        log = Logger.getLogger(ApplyLeaveDAO.class.getName());
        int status=0;
        try
        {
            
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("UPDATE `message` SET `from`=?,`ltype`=?,`fromdate`=?,`todate`=?,`days`=?,`to`=?,`fwdby`=?,`msg`=?,`cl`=?,`al`=?,`sl`=?,`dl`=?,`lwp`=?,`spl`=?,`ao`=?,`status`=?,`cancel`=?,`trash`=? WHERE `msgid`=?");
            ps.setString(1, b.getUname());
            ps.setString(2, b.getLtype());
            ps.setString(3, b.getFod());
            ps.setString(4, b.getTod());
            ps.setInt(5, b.getDays());
            ps.setString(6, b.getTo());
            ps.setString(7, b.getFwdby());
            ps.setString(8, b.getMsg());
            ps.setInt(9, b.getCl());
            ps.setInt(10, b.getAl());
            ps.setInt(11, b.getSl()); 
            ps.setInt(12, b.getDl());
            ps.setInt(13, b.getLwp());
            ps.setInt(14, b.getSpl());
            ps.setInt(15, b.getAo());
            ps.setString(16, "pending");
            ps.setString(17, "no");
            ps.setString(18, "no");
            ps.setInt(19, b.getId());
            
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in ApplyLeaveDAO is : "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
}
