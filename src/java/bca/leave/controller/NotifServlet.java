package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.NotifApplyBean;
import java.util.*;
import bca.leave.DAO.ShowNotifDAO;
import bca.leave.DAO.ReadNotifDAO;

public class NotifServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        log = Logger.getLogger(NotifServlet.class.getName());
        HttpSession ses = req.getSession(false);
        
        if(ses.equals("")||ses.getAttribute("uname").equals(""))
        {
            res.sendRedirect("index.html");
        }
        else
        {
            ses.setAttribute("ac", true);
        
        String name = (String)ses.getAttribute("name");
        NotifApplyBean b = new NotifApplyBean();
        
        if(ReadNotifDAO.Read(name)>0)
        {
            
        try
        {
            List<NotifApplyBean> bb = ShowNotifDAO.getNotif(name);
            req.setAttribute("bb", bb);
            req.getRequestDispatcher("pages/notif.jsp").forward(req, res);
        }
        catch(Exception e)
        {
            log.fatal(e);
        }
        }
        
        else
        {
            try
        {
            List<NotifApplyBean> bb = ShowNotifDAO.getNotif(name);
            req.setAttribute("bb", bb);
            req.getRequestDispatcher("pages/notif.jsp").forward(req, res);
        }
        catch(Exception e)
        {
            log.fatal(e);
        }
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
