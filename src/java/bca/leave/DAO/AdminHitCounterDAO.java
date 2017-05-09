package bca.leave.DAO;
import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.AdminHitCounterBean;
import org.apache.log4j.*;


public class AdminHitCounterDAO {
    private static Logger log = null;
    public static boolean getHit(AdminHitCounterBean b)
    {
        log = Logger.getLogger(AdminHitCounterDAO.class.getName());
        boolean status = false;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("select count(*) from admin");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                b.setHits(rs.getInt("count(*)"));
                status = true;
            }
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
        }
        return status;
    }
    
}
