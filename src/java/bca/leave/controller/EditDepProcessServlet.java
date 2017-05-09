package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.DAO.EditDepProcessDAO;
import bca.leave.model.EditDepProcessBean;

public class EditDepProcessServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false);
        PrintWriter out = res.getWriter();
        EditDepProcessBean b = new EditDepProcessBean();
        
        String uname = (String)session.getAttribute("uname");
        log = Logger.getLogger(EditDepProcessServlet.class.getName());
        
        b.setId(req.getParameter("id"));
        b.setDname(req.getParameter("dname"));
        b.setHod(req.getParameter("hod"));
        
        if(EditDepProcessDAO.Update(b)>0)
        {
            log.info(req.getParameter("dname")+" has been updated by "+uname);
            session.setAttribute("emsg", false);
            session.setAttribute("cmsg", true);
            session.setAttribute("msg", "Department has been Updated sucessfully.");
            req.getRequestDispatcher("ShowDepServlet").forward(req, res);
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
