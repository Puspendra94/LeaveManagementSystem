package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.model.ShowUserBean;
import bca.leave.util.ConnProvider;
import java.sql.*;
import java.util.*;

public class ShowUserDAO {
    private static Logger log = null;
    public static List<ShowUserBean> getValue() throws SQLException
    {
        log = Logger.getLogger(ShowUserDAO.class.getName());
        List<ShowUserBean> b = new ArrayList<ShowUserBean>();
        try(
                Connection conn = ConnProvider.getConn();
                PreparedStatement ps = conn.prepareStatement("select * from employee");
                ResultSet rs = ps.executeQuery();
            ){
            while(rs.next())
            {
                ShowUserBean bb = new ShowUserBean();
                bb.setId(rs.getInt("uid"));
                bb.setUname(rs.getString("uname"));
                bb.setPass(rs.getString("pass"));
                bb.setName(rs.getString("name"));
                bb.setDep(rs.getString("department"));
                bb.setRole(rs.getString("role"));
                bb.setCleave(rs.getInt("cleave"));
                bb.setAleave(rs.getInt("aleave"));
                bb.setSleave(rs.getInt("sleave"));
                bb.setBcleave(rs.getInt("bcleave"));
                bb.setBaleave(rs.getInt("baleave"));
                bb.setBaleave(rs.getInt("baleave"));
                bb.setRegdate(rs.getString("regdate"));
                bb.setRegtime(rs.getString("regtime"));
                b.add(bb);
            }
            
            ConnProvider.closeResultSet(rs);
            ConnProvider.closePreStatement(ps);
            ConnProvider.freeConncetion(conn);
        }
        catch(Exception e)
        {
            log.fatal(e);
            System.err.println("The problem in ShowUserDAO is "+e.getCause()+"\n"+e.getMessage());
        }
        return b;
    }
    
}
