package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.DAO.DeleteGroupDAO;
import bca.leave.model.GroupBean;
 
public class DeleteGroupServlet extends HttpServlet {
 
    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false); 
        log = Logger.getLogger(DeleteGroupServlet.class.getName());
        
        String gname = req.getParameter("view");
        GroupBean b = new GroupBean();
        b.setName(gname);
        log.info((String)session.getAttribute("uname")+" attemp to delete "+gname+" group");
        if(DeleteGroupDAO.DeleteGroup(b)>0)
        {
            try
            {
               if(DeleteGroupDAO.Update(gname)>0)
            {
               
                session.setAttribute("cmsg", true);
                session.setAttribute("msg", "Group has been deleted sucessfully.");
                req.getRequestDispatcher("ViewGroupServlet").forward(req, res);
            }
               else
               {
                   
                   session.setAttribute("cmsg", true);
                session.setAttribute("msg", "Group has been deleted sucessfully.");
                res.sendRedirect("ViewGroupServlet");
               }
                log.info((String)session.getAttribute("uname")+" has been deleted "+gname+" group");
            }
            catch(Exception e)
            {
                log.fatal((String)session.getAttribute("uname")+" is unable to delete "+gname+" group");
                session.setAttribute("emsg", true);
                session.setAttribute("msg", "Some problems occured");
                res.sendRedirect("ViewGroupServlet");
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
