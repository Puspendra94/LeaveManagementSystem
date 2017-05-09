package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import java.util.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.TrashBean;

public class ShowTrashDAO {
    private static Logger log = null;
    public static List<TrashBean> getValue(String from) throws SQLException
    {
        log = Logger.getLogger(ShowTrashDAO.class.getName());
        List<TrashBean> b = new ArrayList<TrashBean>();
        try(
                Connection conn=ConnProvider.getConn();
                PreparedStatement ps = conn.prepareStatement("SELECT * from message where message.to=\""+from+"\" and message.trash=\"yes\" or message.from=\""+from+"\" and message.trash=\"yes\" or message.fwdby=\""+from+"\" and message.trash=\"yes\" or message.to=\""+from+"\" and message.fwdby=\"xxxx\" and message.trash=\"yes\" order by(msgid) desc");
                ResultSet rs = ps.executeQuery();
           ){
            while(rs.next())
            {
                TrashBean tb = new TrashBean();
                tb.setId(rs.getInt(1));
                tb.setFrom(rs.getString("from"));
                tb.setTo(rs.getString("to"));
                tb.setLtype(rs.getString("ltype"));
                tb.setFdate(rs.getString("fromdate"));
                tb.setTdate(rs.getString("todate"));
                tb.setDay(rs.getInt("days"));
                tb.setMsg(rs.getString("msg"));
                tb.setStatus(rs.getString("status"));
                b.add(tb);
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
            
        }catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in ShowTrashDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        
        return b;
    }
    
}
