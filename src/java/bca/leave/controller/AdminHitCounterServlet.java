package bca.leave.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.DAO.AdminHitCounterDAO;
import bca.leave.model.AdminHitCounterBean;
import org.apache.log4j.*;


public class AdminHitCounterServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
       PrintWriter out = res.getWriter();
        
       int hit = 0;
       log = Logger.getLogger(AdminHitCounterServlet.class.getName());
       AdminHitCounterBean b = new AdminHitCounterBean();
       
       
       if(AdminHitCounterDAO.getHit(b))
       {
           hit = b.getHits();

               if(hit == 0)
               {
                   res.sendRedirect("/LeaveManagement/admin/install.jsp");
               }
               else
               {
                   res.sendRedirect("/LeaveManagement/admin/Login.jsp");
               }
       }
       else 
       {

           log.fatal("Error in database operation occured");
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
