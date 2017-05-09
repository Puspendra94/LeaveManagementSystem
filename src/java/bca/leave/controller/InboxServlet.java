package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import bca.leave.model.InboxBean;
import bca.leave.DAO.InboxDAO;

public class InboxServlet extends HttpServlet {

    private static Logger log = null;
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
       HttpSession ses = req.getSession(false);
       log = Logger.getLogger(InboxServlet.class.getName());
       
       if(ses.equals("")||ses.getAttribute("uname").equals(""))
       {
           res.sendRedirect("index.html");
       }
       else
       {
           
        try
        {
            String uname = (String)ses.getAttribute("name");
            if(uname.equals(" "))
            {
                req.getRequestDispatcher("index.html").include(req, res);
            }
            else
            {
                List<InboxBean> b=InboxDAO.getData(uname);
            req.setAttribute("b", b);
            req.getRequestDispatcher("pages/Inbox.jsp").forward(req, res);
            }
        }
        catch(Exception e)
        {
            log.fatal("Error occured");
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
