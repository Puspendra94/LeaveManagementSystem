package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminLogOutServlet extends HttpServlet {

    private static Logger log = null;
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        
        log = Logger.getLogger(AdminLogOutServlet.class.getName());
        
        HttpSession session = req.getSession(false);
        
        try
        {
            log.info((String)session.getAttribute("uname")+" attempt to logout");
            session.invalidate();
            res.sendRedirect("../Login.jsp");
        
        log.info("LogOut sucessful");
        
        }
        catch(Exception e)
        {
            log.warn((String)session.getAttribute("uname")+" is not logged out");
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
