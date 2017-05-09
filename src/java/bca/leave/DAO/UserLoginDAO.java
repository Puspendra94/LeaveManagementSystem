package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.UserLoginBean;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserLoginDAO {
    private static Logger log = null;
    public static boolean Validate(UserLoginBean b) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        log = Logger.getLogger(UserLoginDAO.class.getName());
        String pass = b.getPass();
        String gensecurepass = null;
        boolean status=false;
        
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            gensecurepass = sb.toString();
            
            
            Connection conn=ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("select * from employee where binary pass=? and uid=? or binary pass=? and uname=?");
            ps.setString(1, gensecurepass);
            ps.setString(2, b.getView());
            ps.setString(3, gensecurepass); 
            ps.setString(4, b.getView());
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                b.setId(rs.getInt("uid"));
                b.setUname(rs.getString("uname"));
                b.setName(rs.getString("name"));
                b.setDep(rs.getString("department"));
                b.setRole(rs.getString("role")); 
                b.setYear(rs.getInt("cyear"));
                status=true;
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The Problem in UserLoginDAO is "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
     
    
}
