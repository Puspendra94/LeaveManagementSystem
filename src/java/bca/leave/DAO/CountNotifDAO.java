package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import bca.leave.model.CountNotifBean;
import java.sql.*;

public class CountNotifDAO {
    private static Logger log = null;
    public static boolean getNo(CountNotifBean b)
    {
        log = Logger.getLogger(CountNotifDAO.class.getName());
        boolean status = false;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("select count(*) from message where status = ? and fwdby=? and trash=? or status = ? and message.to =? and trash=? and fwdby = ? or status = ? and message.to=? and trash=?");
            ps.setString(1, "pending");
            ps.setString(2, b.getName());
            ps.setString(3, "no");
            ps.setString(4, "pending");
            ps.setString(5, b.getName());
            ps.setString(6, "no");
            ps.setString(7, "NONE");
            ps.setString(8, "Forwarded");
            ps.setString(9, b.getName());
            ps.setString(10, "no");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                 
                b.setP(rs.getInt("count(*)")); 
                System.err.println("This is from notifDAO "+rs.getInt("count(*)")); 
                
            }
            PreparedStatement pss = conn.prepareStatement("SELECT count(*) from notif where ncheck='other' and nstatus='unread' and notif.to='"+b.getName()+"' and fwdby='NONE' or ncheck='other' and nstatus='unread' and fwdby='"+b.getName()+"' and status='pending' or ncheck='other' and nstatus='unread' and notif.to='"+b.getName()+"' and status!='pending'");
            ResultSet rss = pss.executeQuery();
            while(rss.next())
            {
                b.setQ(rss.getInt("count(*)"));
                System.err.println("This is from notifDAO "+rss.getInt("count(*)"));
            }
            
            status = true;
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.closeResultSet(rss);
            ConnProvider.closePreStatement(pss);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in CountNotifDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
