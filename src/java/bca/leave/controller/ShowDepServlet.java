package bca.leave.controller;

import org.apache.log4j.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import bca.leave.model.ShowDepBean;
import bca.leave.DAO.ShowDepDAO;

public class ShowDepServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        
        log = Logger.getLogger(ShowDepServlet.class.getName());
        if(session.getAttribute("uname").equals(""))
        {
            
        }
        else
        {
            try
        {
            List<ShowDepBean> b = ShowDepDAO.getDep();
            req.setAttribute("b", b); 
            
            req.getRequestDispatcher("ViewDepartment.jsp").forward(req, res);
        }
        catch(Exception e)
        {
            log.fatal(e);
        }
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
