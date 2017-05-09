package bca.leave.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import bca.leave.DAO.ViewGroupDAO;
import bca.leave.model.ViewGroupBean;

public class ViewGroupServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = res.getWriter();
        
       HttpSession session = req.getSession(false);
       
       if(session.getAttribute("uname").equals(""))
       {
           res.sendRedirect("../index.jsp");
       }
       else
       {
            try
        {
            List<ViewGroupBean> b = ViewGroupDAO.getGroup();
            req.setAttribute("b", b);
            
            req.getRequestDispatcher("viewgroup.jsp").forward(req, res);
        }
        catch(Exception e)
        {
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "Some problem occured");
            System.err.println("The problem in ViewGroupServlet is "+e.getCause()+"\n"+e.getMessage());
            res.sendRedirect("AdminLoggedIn.jsp");
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
