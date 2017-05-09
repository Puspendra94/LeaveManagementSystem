package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.ChangeAdminPasswordBean;
import java.security.MessageDigest;

public class ChangeAdminPasswordDAO {
    private static Logger log = null;
    public static int change(ChangeAdminPasswordBean b)
    {
        log = Logger.getLogger(ChangeAdminPasswordDAO.class.getName());
        int status=0;
        
        String pass = b.getOp();
        String npass = b.getNp();
        String gnpass = null;
        String gpass = null;
                
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            MessageDigest omd = MessageDigest.getInstance("SHA-384");
             md.update(pass.getBytes());
            omd.update(npass.getBytes());
             byte[] bytes = md.digest();
            byte[] obytes = omd.digest();
             StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            StringBuilder osb = new StringBuilder();
            for(int j=0; j< obytes.length ;j++)
            {
                osb.append(Integer.toString((obytes[j] & 0xff) + 0x100, 16).substring(1));
            }
             gpass = sb.toString();
            gnpass = osb.toString();
            
            
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("update admin set pass=? where pass=? and uname=?");
            ps.setString(1, gnpass);
            ps.setString(2, gpass);
            ps.setString(3, b.getUname());
            
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in ChangeAdminPasswordDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
