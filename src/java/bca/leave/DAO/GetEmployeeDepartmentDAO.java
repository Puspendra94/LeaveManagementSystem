package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.model.DeleteDepartmentBean;
import bca.leave.util.ConnProvider;
import java.sql.*;

public class GetEmployeeDepartmentDAO {
    private static Logger log = null;
    public static boolean getNo(DeleteDepartmentBean b)
    {
        log = Logger.getLogger(GetEmployeeDepartmentDAO.class.getName());
        boolean status = false;
        try
        {
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("select count(*) from employee where department=?");
            ps.setString(1, b.getDname()); 
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                b.setNo(rs.getInt("count(*)"));
                status = true;
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in GetEmployeeDepartmentDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
