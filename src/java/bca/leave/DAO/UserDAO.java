package bca.leave.DAO;
import org.apache.log4j.*;
import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.UserBean;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserDAO {
    private static Logger log = null;
    public static int status=0;
    
    public static int Validate(UserBean ub) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        log = Logger.getLogger(UserDAO.class.getName());
        String pass = ub.getPass();
        String gensecurepass = null;
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
            PreparedStatement ps=conn.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, ub.getId()); 
            ps.setString(2, ub.getUname());
            ps.setString(3, gensecurepass);
            ps.setString(4, ub.getName());
            ps.setString(5, ub.getDep());
            ps.setString(6, ub.getRole());
            ps.setInt(7, ub.getCl());
            ps.setInt(8, ub.getCl());
            ps.setInt(9, ub.getAl());
            ps.setInt(10, ub.getAl());
            ps.setInt(11, ub.getSl());
            ps.setInt(12, ub.getSl());
            ps.setInt(13, 0);
            ps.setInt(14, 0);
            ps.setInt(15, 0);
            ps.setInt(16, 0);
            ps.setInt(17, ub.getYe());
            ps.setString(18, ub.getDate()); 
            ps.setString(19, ub.getTime());
            
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
