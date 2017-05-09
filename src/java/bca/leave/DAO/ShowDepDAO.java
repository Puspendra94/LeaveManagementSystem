package bca.leave.DAO;

import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import bca.leave.model.ShowDepBean;
import java.util.*;
import java.sql.*;

public class ShowDepDAO {
    private static Logger log = null;
    public static List<ShowDepBean> getDep() throws SQLException
    {
        log= Logger.getLogger(ShowDepDAO.class.getName());
        List<ShowDepBean> b = new ArrayList<ShowDepBean>();
        try(
                Connection conn = ConnProvider.getConn();
                PreparedStatement ps = conn.prepareStatement("select * from department");
                ResultSet rs = ps.executeQuery();
            ){
            
            while(rs.next())
            {
                ShowDepBean bb = new ShowDepBean();
                bb.setDid(rs.getString("did"));
                bb.setDname(rs.getString("dname"));
                bb.setHod(rs.getString("hod"));
                b.add(bb); 
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in ShowDepDAO is "+e.getCause()+"\n"+e.getMessage());
        }
        return b;
        
    }
    
}
