package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.model.UserViewBean;
import java.sql.*;
import bca.leave.util.ConnProvider;

public class UserViewDAO {
    private static Logger log = null;
    public static boolean View(UserViewBean b)
    {
        log = Logger.getLogger(UserViewDAO.class.getName());
        boolean status=false;
        try
        {
            Connection conn=ConnProvider.getConn();
            PreparedStatement ps=conn.prepareStatement("select * from employee where uid=? or uname=? or name=?");
            ps.setString(1, b.getView());
            ps.setString(2, b.getView());
            ps.setString(3, b.getView()); 
            ResultSet rs=ps.executeQuery();
            
            if(rs.next())
            {
                b.setId(rs.getString("uid")); 
                b.setUname(rs.getString("uname"));
                b.setPass(rs.getString("pass"));
                b.setName(rs.getString("name")); 
                b.setDep(rs.getString("department"));
                b.setRole(rs.getString("role"));
                b.setCleave(rs.getInt("cleave")); 
                b.setAleave(rs.getInt("aleave"));
                b.setSleave(rs.getInt("sleave"));
                b.setBcleave(rs.getInt("bcleave"));
                b.setBaleave(rs.getInt("baleave"));
                b.setBsleave(rs.getInt("bsleave"));
                b.setDate(rs.getString("regdate"));
                b.setTime(rs.getString("regtime")); 
                status=true;
            }
            
            ConnProvider.closeResultSet(rs);
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
