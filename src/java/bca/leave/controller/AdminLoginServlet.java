package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.AdminLoginBean;
import bca.leave.DAO.AdminLoginDAO;
import bca.leave.DAO.UpdateYearDAO;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AdminLoginServlet extends HttpServlet {

    private static Logger log = null;
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        
        log = Logger.getLogger(AdminLoginServlet.class.getName());
        
        PrintWriter out=res.getWriter();
        Calendar cal=new GregorianCalendar();
        int y = cal.get(Calendar.YEAR);
        
        AdminLoginBean ab=new AdminLoginBean();
        
        ab.setName(req.getParameter("uname"));
        ab.setPass(req.getParameter("pass"));
        
        log.info(req.getParameter("uname")+" attempt to login");
        
        try
        {

                if(AdminLoginDAO.Validate(ab))
                {
                    log.info(req.getParameter("uname")+" has been logged in");

                    int year = ab.getYear();
                    if(y>year)
                        {
                            if(UpdateYearDAO.Update(y)>0)
                            {
                                log.info("Year has been updated");
                            }
                            
                        }
                    HttpSession session=req.getSession(true);
                    session.setAttribute("ab", ab);
                    session.setAttribute("uname", req.getParameter("uname")); 
                    session.setAttribute("msg", "");
                    session.setAttribute("cmsg", false);
                    session.setAttribute("emsg", false);
                    res.sendRedirect("pages/AdminLoggedIn.jsp");
                }
                else
                {
                    log.warn(req.getParameter("uname")+" is not sucessfully logged in");
                    req.getRequestDispatcher("index.jsp").include(req, res); 
                    out.println("<br><br><br>User name or password may be incorrect...");
                }

        }
        catch(Exception e)
        {
            log.fatal("Error is occured while "+req.getParameter("uname")+" is trying to log in");
           req.getRequestDispatcher("index.jsp").include(req, res);
           out.println("<br><br><br>Error has occured in the databse operation!");
        }
        }
    }


