package bca.leave.DAO;
import org.apache.log4j.*;

import bca.leave.model.ViewGroupBean;
import java.util.*;
import java.sql.*;
import bca.leave.util.ConnProvider;

public class ViewGroupDAO {
    private static Logger log = null;
    public static List<ViewGroupBean> getGroup() throws SQLException
            {
               List<ViewGroupBean> b = new ArrayList<ViewGroupBean>();
               try(
                 
                       Connection conn = ConnProvider.getConn();
                       PreparedStatement ps = conn.prepareStatement("SELECT * FROM `group`");
                       ResultSet rs = ps.executeQuery();
                       
                 ){
                   while(rs.next())
                   {
                       ViewGroupBean bb = new ViewGroupBean();
                       bb.setGname(rs.getString("gname"));
                       bb.setCl(rs.getInt("cleave"));
                       bb.setAl(rs.getInt("aleave"));
                       bb.setSl(rs.getInt("sleave"));
                       b.add(bb);
                   }
                   
                   ConnProvider.closeResultSet(rs);
                   ConnProvider.closePreStatement(ps);
                   ConnProvider.freeConncetion(conn);
               }
               catch(Exception e)
               {
                   log.fatal(e);
                   System.err.println("The problem in ViewGroupDAO is "+e.getCause()+"\n"+e.getMessage());
               }
               return b;
            }
    
}
