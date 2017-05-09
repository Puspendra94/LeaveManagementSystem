package bca.leave.DAO;
import org.apache.log4j.*;

import java.util.*;
import java.sql.*;
import bca.leave.model.ApplyBean;
import bca.leave.util.ConnProvider;

public class ApplyDAO {
   private static Logger log = null;
    public static List<ApplyBean> getValue() throws SQLException
    {
        log = Logger.getLogger(ApplyDAO.class.getName());
        List<ApplyBean> b= new ArrayList<ApplyBean>();
        try
            (
                Connection conn = ConnProvider.getConn();
                PreparedStatement ps = conn.prepareStatement("select name,uname from employee");
                ResultSet rs = ps.executeQuery();
             ){
            while(rs.next())
            {
                ApplyBean ab = new ApplyBean();
                ab.setName(rs.getString("name"));
                ab.setUname(rs.getString("uname"));
                b.add(ab);
            }
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
            
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in ApplyDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return b;
    }
    
}
