package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.DepartmentBean;
import bca.leave.DAO.DepartmentDAO;

public class DepartmentServlet extends HttpServlet {

    private static Logger log = null;
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        HttpSession session = req.getSession(false);
        
        log = Logger.getLogger(DepartmentServlet.class.getName());
        String uname = (String)session.getAttribute("uname");
        
      DepartmentBean db=new DepartmentBean();
      db.setId(req.getParameter("id"));
      db.setDid(req.getParameter("did")); 
      db.setDname(req.getParameter("dname"));
      db.setHod(req.getParameter("hod")); 
      
      
      if(DepartmentDAO.Validate(db)>0)
      {
          log.info(req.getParameter("dname")+" department has been deleted by "+uname);
          session.setAttribute("cmsg", true);
          session.setAttribute("msg", "Department has been added sucessfully");
          res.sendRedirect("ShowDepServlet");
      }
      else
      {
          session.setAttribute("msg", "You have some problem while adding new department<br>Please try again.");
          session.setAttribute("emsg", true);
          res.sendRedirect("IdServlet");
      }
      
        
    }

}
