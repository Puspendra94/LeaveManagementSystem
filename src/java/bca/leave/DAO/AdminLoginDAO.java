package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.model.AdminLoginBean;
import bca.leave.util.ConnProvider;
import java.security.MessageDigest;

public class AdminLoginDAO {
    private static Logger log = null;
    public static boolean Validate(AdminLoginBean ab)
    {
        log = Logger.getLogger(AdminLoginDAO.class.getName());
        boolean valid=false;
        try
        {
            String pass = ab.getPass();
            String genpass = null;
            
             MessageDigest md = MessageDigest.getInstance("SHA-384");
             md.update(pass.getBytes());
             byte[] bytes = md.digest();
             StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
             genpass = sb.toString();
            
            Connection conn=ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("select * from admin where uname=? and binary pass=?");
            ps.setString(1, ab.getName()); 
            ps.setString(2, genpass); 
            
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                ab.setYear(rs.getInt("cyear"));
                valid = true;
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem is :"+e.getCause()+"\n"+e.getMessage());
        }
        
        return valid;
    }
    
}
