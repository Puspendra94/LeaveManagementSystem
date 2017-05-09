package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.ApplyLeaveBean;

public class ApplyLeaveDAO {

    private static Logger log = null;
    public static int Validate(ApplyLeaveBean b)
    {
        log = Logger.getLogger(ApplyLeaveDAO.class.getName());
        int status=0;
        try
        {
            
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("insert into message(`from`, `ltype`, `fromdate`, `todate`, `days`, `to`, `fwdby`,`msg`,`cl`, `al`, `sl`, `dl`, `lwp`, `spl`, `ao`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, b.getUname());
            ps.setString(2, b.getType());
            ps.setString(3, b.getFod());
            ps.setString(4, b.getTod());
            ps.setInt(5, b.getDay());
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
