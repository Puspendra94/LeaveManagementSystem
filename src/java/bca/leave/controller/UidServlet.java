package bca.leave.controller;

import org.apache.log4j.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.DAO.UidDAO;
import java.util.*;
import bca.leave.model.SelDep;

public class UidServlet extends HttpServlet {

    private static Logger log = null;
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        
        log = Logger.getLogger(UidServlet.class.getName());
        HttpSession session = req.getSession(false);
        
        if(session.getAttribute("uname").equals(""))
        {
            res.sendRedirect("../index.jsp");
        }
        else
        {
            
        int id=UidDAO.getLastID();
        try
        {
            List<SelDep> department=UidDAO.getDep();
            
            req.setAttribute("dep", department); 
            
        }
        catch(Exception e)
        {
            log.fatal(e);
        }
        try
        {
            List<SelDep> group=UidDAO.getGroup();
            req.setAttribute("group", group); 
        }
        catch(Exception e)
        {
            log.fatal(e);
        }
        
        req.setAttribute("id", ++id); 
        req.getRequestDispatcher("AddUser.jsp").forward(req, res);
        
        }
    }

}
