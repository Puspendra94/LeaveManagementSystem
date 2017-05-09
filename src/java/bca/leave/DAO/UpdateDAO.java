package bca.leave.DAO;
import org.apache.log4j.*;

import java.sql.*;
import bca.leave.util.ConnProvider;
import bca.leave.model.UserViewBean;
import java.security.MessageDigest;

public class UpdateDAO {
    private static Logger log= null;
    public static int Update(UserViewBean b)
    {
        PreparedStatement ps = null;
        log = Logger.getLogger(UpdateDAO.class.getName());
        String pass = b.getPass();
        String gpass = null;
        
        int status=0;
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
            gpass = sb.toString();
            
            Connection conn=ConnProvider.getConn();
            if(pass.isEmpty()== true)
            {
                ps=conn.prepareStatement("update employee set uname=?,name=?,pass=?,department=?,role=?,cleave=?,aleave=?,sleave=?,bcleave=?,baleave=?,bsleave=? where uid=?");
                ps.setString(1, b.getUname());
                ps.setString(2, b.getName());
                ps.setString(3, b.getPpass());
                ps.setString(4, b.getDep());
                ps.setString(5, b.getRole());
                ps.setInt(6, b.getCleave());
                ps.setInt(7, b.getAleave());
                ps.setInt(8, b.getSleave());
                ps.setInt(9, b.getBcleave());
                ps.setInt(10, b.getBaleave());
                ps.setInt(11, b.getBsleave());
                ps.setString(12, b.getId());
            }
            else
            {
                ps=conn.prepareStatement("update employee set uname=?,pass=?,name=?,department=?,role=?,cleave=?,aleave=?,sleave=?,bcleave=?,baleave=?,bsleave=? where uid=?");
                ps.setString(1, b.getUname());
                ps.setString(2, gpass);
                ps.setString(3, b.getName());
                ps.setString(4, b.getDep());
                ps.setString(5, b.getRole());
                ps.setInt(6, b.getCleave());
                ps.setInt(7, b.getAleave());
                ps.setInt(8, b.getSleave());
                ps.setInt(9, b.getBcleave());
                ps.setInt(10, b.getBaleave());
                ps.setInt(11, b.getBsleave());
                ps.setString(12, b.getId());
            }
            status=ps.executeUpdate();
            
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem is in UpdateDAO is "+e.getCause()+"\n"+e.getMessage());
        }
        return status;
    }
    
}
