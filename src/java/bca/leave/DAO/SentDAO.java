package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import java.util.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.SentBean;

public class SentDAO {
    private static Logger log = null;
    public static List<SentBean> getValue(String from) throws SQLException
    {
        log = Logger.getLogger(SentDAO.class.getName());
        List<SentBean> b = new ArrayList<SentBean>();
        try(
               Connection conn=ConnProvider.getConn();
                PreparedStatement ps = conn.prepareStatement("SELECT * from message where message.from=\""+from+"\" and message.trash=\"no\" order by(msgid) desc");
                ResultSet rs = ps.executeQuery();
           ){
            
            while(rs.next())
            {
                SentBean bb = new SentBean();
                bb.setId(rs.getInt(1));
                bb.setTo(rs.getString("to"));
                bb.setFwdby(rs.getString("fwdby"));
                bb.setLtype(rs.getString("ltype"));
                bb.setFdate(rs.getString("fromdate"));
                bb.setTdate(rs.getString("todate"));
                bb.setDay(rs.getInt("days"));
                bb.setMsg(rs.getString("msg"));
                bb.setStatus(rs.getString("status"));
                b.add(bb);
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in SentDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return b;
    }
    
}
