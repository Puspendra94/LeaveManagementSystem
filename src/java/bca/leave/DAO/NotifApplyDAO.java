package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.model.NotifApplyBean;
import java.sql.*;
import bca.leave.util.ConnProvider;

public class NotifApplyDAO {
    private static Logger log = null;
    public static int Notify(NotifApplyBean b)
    {
        log = Logger.getLogger(NotifApplyDAO.class.getName());
        int status = 0;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `notif`(`msg`,`to`,`fwdby`, `ondate`, `fromdate`, `todate`, `button`, `nstatus`, `ncheck`) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, b.getMsg());
            ps.setString(2, b.getTo());
            ps.setString(3, b.getFwdby());
            ps.setString(4, b.getOndate());
            ps.setString(5, b.getFod());
            ps.setString(6, b.getTod());
            ps.setString(7, b.getButton());
            
            ps.setString(8, b.getNstatus());
            ps.setString(9, b.getCheck());
            
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in NotifApplyDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
}
