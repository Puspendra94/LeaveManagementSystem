package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import bca.leave.model.AddGroupBean;
import java.sql.*;

public class AddGroupDAO {
    private static Logger log = null;
    public static int Validate(AddGroupBean b)
    {
        log = Logger.getLogger(AddGroupDAO.class.getName());
        int status=0;
        
        try
        {
            
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `group`(`gname`, `cleave`, `aleave`, `sleave`) VALUES (?,?,?,?)");
            ps.setString(1, b.getName());
            ps.setInt(2, b.getCl());
            ps.setInt(3, b.getAl());
            ps.setInt(4, b.getSl());
            
            
            int c = b.getCl();
            String n = b.getName();
            //System.err.println("Add G "+c);
            //System.out.println("Add Gn "+n);
            
            
            
            status = ps.executeUpdate();
           
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
            
        }
        catch(Exception e)
        {
            log.fatal(e); 
            System.err.println("The problem in AddGroupDAO is "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
        
    }
    
}
