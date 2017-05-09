package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.model.NotifApplyBean;
import java.sql.*;
import java.util.*;
import bca.leave.util.ConnProvider;

public class ShowNotifDAO {
    private static Logger log = null;
    public static List<NotifApplyBean> getNotif(String name) throws SQLException
    {
        log = Logger.getLogger(ShowNotifDAO.class.getName());
        List<NotifApplyBean> bb = new ArrayList<NotifApplyBean>();
        
        try(
                Connection conn = ConnProvider.getConn();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM `notif`where notif.to='"+name+"' and fwdby=\"NONE\" and status=\"pending\" or fwdby='"+name+"' and status=\"pending\" or notif.to='"+name+"' and status!=\"pending\" order by(id) desc");
                ResultSet rs = ps.executeQuery()
           
           )
        {
            while(rs.next())
            {
                NotifApplyBean nab = new NotifApplyBean();
                nab.setId(rs.getInt("id"));
                nab.setMsg(rs.getString("msg"));
                nab.setTo(rs.getString("to"));
                nab.setFwdby(rs.getString("fwdby"));
                nab.setOndate(rs.getString("ondate"));
                nab.setFod(rs.getString("fromdate"));
                nab.setTod(rs.getString("todate"));
                nab.setButton(rs.getString("button"));
                
                bb.add(nab);
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in ShowNotifDAO is: "+e.getCause()+"\n"+e.getMessage());
        }
        return bb;
    }
    
}
