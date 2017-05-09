package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.UserLoginBean;
import bca.leave.DAO.UserLoginDAO;
import bca.leave.DAO.CountNotifDAO;
import bca.leave.model.CountNotifBean;
import java.util.Calendar;
import java.util.GregorianCalendar;
import bca.leave.DAO.UpdateYearDAO;
 
public class UserLoginServlet extends HttpServlet {
 
    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        
        log = Logger.getLogger(UserLoginServlet.class.getName());
        
        Calendar cal=new GregorianCalendar();
        int y = cal.get(Calendar.YEAR);
        
        UserLoginBean b=new UserLoginBean();
        
        b.setView(req.getParameter("login"));
        b.setPass(req.getParameter("pass"));
        
        log.info(req.getParameter("login")+" attempt to login");
        
        try {
            boolean result = UserLoginDAO.Validate(b);
            if(result)
            {
                
                log.info(req.getParameter("login")+" has been logged in");
                HttpSession ses=req.getSession(true);
                ses.setAttribute("b", b);
                ses.setAttribute("id", b.getId());
                ses.setAttribute("uname", b.getUname());
                ses.setAttribute("name", b.getName());
                ses.setAttribute("dep", b.getDep());
                ses.setAttribute("role", b.getRole());
                
                int year = b.getYear();
                try
                {

                    if(y>year)
                    {
                        if(UpdateYearDAO.Update(y)>0)
                        {
                            log.info("Year has been updated");
                        }
                    }
                    
                    CountNotifBean bb = new CountNotifBean();
                    bb.setName(b.getName());
                    if(CountNotifDAO.getNo(bb))
                    {
                        
                        int a = bb.getP();
                        int bbb = bb.getQ();
                        int c = a+bbb;
                        ses.setAttribute("a", c);
                        ses.setAttribute("ac", false);
                    }
                }
                catch(Exception e)
                {
                    System.err.println("The problem in UserLoginServlet is :"+e.getCause()+"\n"+e.getMessage());
                }
                req.getRequestDispatcher("pages/UserLoggedIn.jsp").forward(req, res);
            }
            else if(!result)
            {
                
                log.warn("Invalid login attempts by "+req.getParameter("login"));
                out.println("<center><font color=\"red\">Invalid Login!!!<br>User name or password may be incorrect<font></center>");
                req.getRequestDispatcher("index.html").include(req, res);
            }
            else
            {
                out.println("<center><font text=\"red\">Error has occured in the databse operation!</font></center>");
                req.getRequestDispatcher("index.html").include(req, res);
                
                
            }
        } catch (Exception ex) {
            log.fatal("Error has been occured "+ex);
            System.out.println(ex);
        } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
