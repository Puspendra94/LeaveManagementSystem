package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import java.util.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.InboxBean;

public class InboxDAO {
    private static Logger log = null;
    public static List<InboxBean> getData(String uname) throws SQLException
    {
        log = Logger.getLogger(InboxDAO.class.getName());
        List<InboxBean> b = new ArrayList<InboxBean>();
        try(
              Connection conn=ConnProvider.getConn();
                PreparedStatement ps=conn.prepareStatement("SELECT * from message where message.to=\""+uname+"\" and message.status!=\"pending\" and message.trash=\"no\" and message.cancel=\"no\" or message.fwdby=\""+uname+"\" and message.trash=\"no\" and message.cancel=\"no\" or message.to=\""+uname+"\" and message.fwdby=\"\" and message.trash=\"no\" and message.cancel=\"no\" or message.to=\""+uname+"\" and message.fwdby=\"NONE\" and message.trash=\"no\" and message.cancel=\"no\" order by(msgid) desc");
                ResultSet rs=ps.executeQuery();
                
           ){
            while(rs.next())
            {
                InboxBean ib = new InboxBean();
                ib.setId(rs.getInt(1));
                ib.setFrom(rs.getString("from"));
                ib.setFwdby(rs.getString("fwdby"));
                ib.setMsg(rs.getString("msg"));
                ib.setStatus(rs.getString("status"));
                ib.setLtype(rs.getString("ltype"));
                ib.setFdate(rs.getString("fromdate"));
                ib.setTdate(rs.getString("todate"));
                ib.setDay(rs.getInt("days"));
                ib.setTo(rs.getString("to"));
                ib.setCl(rs.getInt("cl"));
                ib.setAl(rs.getInt("al"));
                ib.setSl(rs.getInt("sl"));
                ib.setDl(rs.getInt("dl"));
                ib.setLwp(rs.getInt("lwp"));
                ib.setSpl(rs.getInt("spl"));
                ib.setAo(rs.getInt("ao"));
             
                
                b.add(ib);
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in InboxDAO is "+e.getCause()+"\n"+e.getMessage());
        }
        
        return b;
    }
    
}
