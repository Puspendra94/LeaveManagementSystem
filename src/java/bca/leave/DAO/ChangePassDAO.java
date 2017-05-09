package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.model.ChangePassBean;
import bca.leave.util.ConnProvider;
import java.security.MessageDigest;
import java.sql.*;

public class ChangePassDAO {
    private static Logger log = null;
    
    public static int ChangePass(ChangePassBean cb)
    {
        log = Logger.getLogger(ChangePassDAO.class.getName());
        String npass=cb.getNpass();
        String pass = cb.getOpass();
        String gnpass = null;
        String gpass = null;
       
        int status=0;
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
            
            
            Connection conn=ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("update employee set pass=? where pass=? and uname=?");
            ps.setString(1, gnpass);
            ps.setString(2, gpass);
            ps.setString(3, cb.getUname());
            
            status=ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem is from ChangepassDAO :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
