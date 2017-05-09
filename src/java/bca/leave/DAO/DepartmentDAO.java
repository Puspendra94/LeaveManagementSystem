package bca.leave.DAO;
import org.apache.log4j.*;
import bca.leave.model.DepartmentBean;
import java.sql.*;
import bca.leave.util.ConnProvider;

public class DepartmentDAO {
    private static Logger log = null;
    public static int Validate(DepartmentBean db)
    {
        log = Logger.getLogger(DepartmentDAO.class.getName());
        int status=0;
        
        try
        {
            Connection conn=ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("insert into department values(?,?,?,?)");
            ps.setString(1, db.getId()); 
            ps.setString(2, db.getDid());
            ps.setString(3, db.getDname());
            ps.setString(4, db.getHod()); 
            
            status=ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem is "+e.getCause()+"\n"+e.getMessage());
        }
        
        return status;
    }
    
}
