package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import bca.leave.model.ViewUpdateGroupBean;
import java.sql.*;

public class ViewUpdateGroupDAO {
    private static Logger log =null;
    public static boolean getGroup(ViewUpdateGroupBean b)
    {
        log = Logger.getLogger(ViewUpdateGroupDAO.class.getName());
        boolean status=false;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group` WHERE gname=?");
            ps.setString(1, b.getGname());
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                b.setCl(rs.getInt("cleave"));
                b.setAl(rs.getInt("aleave"));
                b.setSl(rs.getInt("sleave"));
                status=true;
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in ViewUpdateGroupDAo is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
