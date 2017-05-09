package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.UpdateGroupBean;
import bca.leave.DAO.UpdateGroupDAO;

public class UpdateGroupServlet extends HttpServlet {

    private static Logger log = null;
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false);
        PrintWriter out = res.getWriter();
        
        String uname = (String)session.getAttribute("uname");
        log = Logger.getLogger(UpdateGroupServlet.class.getName());
        
        String gname = req.getParameter("gname");
        int cl = Integer.parseInt(req.getParameter("cl"));
        int al = Integer.parseInt(req.getParameter("al"));
        int sl = Integer.parseInt(req.getParameter("sl"));
        
        UpdateGroupBean b = new UpdateGroupBean();
        b.setGname(gname);
        b.setCl(cl);
        b.setAl(al);
        b.setSl(sl);
        
        try
        {
            if(UpdateGroupDAO.UpdateGroup(b)>0)
        {
            if(UpdateGroupDAO.UpdateEmployee(b)>0)
            {
                session.setAttribute("cmsg", true);
                session.setAttribute("msg", "Group has been updated sucessfully");
                req.getRequestDispatcher("ViewGroupServlet").forward(req, res);
            }
            else
            {
                session.setAttribute("cmsg", true);
                session.setAttribute("msg", "Group has been updated sucessfully");
                res.sendRedirect("ViewGroupServlet");
            }
            log.info(req.getParameter("gname")+" group has been updated by "+uname);
        }
        }
        catch(Exception e)
        {
            log.fatal("problem occured");
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "Some problem occured");
            System.err.println("The problem in UpdateGroupServlet is "+e.getCause()+"\n"+e.getMessage());
            res.sendRedirect("ViewGroupServlet"); 
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
