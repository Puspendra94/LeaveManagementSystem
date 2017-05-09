package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.util.ConnProvider;
import java.sql.*;
import bca.leave.model.AdminRegisterBean;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AdminRegisterDAO {
    private static Logger log = null;
    
    public static int Validate(AdminRegisterBean b)
    {
        log = Logger.getLogger(AdminRegisterDAO.class.getName());
        int status = 0;
        try
        {
            String pass = b.getPass();
            String gpass = null;
            
             MessageDigest md = MessageDigest.getInstance("SHA-384");
             md.update(pass.getBytes());
             byte[] bytes = md.digest();
             StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
             gpass = sb.toString();
            
            
            Calendar cal=new GregorianCalendar();
            int y = cal.get(Calendar.YEAR);
            
            Connection conn = ConnProvider.getConn();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `admin`(`uname`, `pass`, `cyear`) VALUES (?,?,?)");
            ps.setString(1, b.getUname());
            ps.setString(2, gpass); 
            ps.setInt(3, y);
            
            status = ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in AdminRegisterDAO is :"+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
