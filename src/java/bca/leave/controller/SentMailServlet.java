package bca.leave.controller;

import org.apache.log4j.*;
import bca.leave.DAO.ApplyDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.DAO.SentMailProcessDAO;
import bca.leave.model.EditAppliedLeaveBean;
import bca.leave.DAO.EditAppliedLeaveDAO;
import bca.leave.model.ApplyBean;
import java.util.List;

public class SentMailServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        log = Logger.getLogger(SentMailServlet.class.getName());
        String i = req.getParameter("cb");
        int id = Integer.parseInt(i);
        HttpSession ses = req.getSession(false);
        String action = req.getParameter("action");
        try
        {
            switch (action) {
                case "Edit":
                    EditAppliedLeaveBean b = new EditAppliedLeaveBean();
                    b.setId(id);
                    if(EditAppliedLeaveDAO.Edit(b))
                    {
                        List<ApplyBean> bc = ApplyDAO.getValue();
                        req.setAttribute("bc", bc);
                        req.setAttribute("b", b);
                        req.getRequestDispatcher("pages/EditAppliedLeave.jsp").forward(req, res);
            
                    }
                    break;
                case "Cancel":
                    if(SentMailProcessDAO.Cancel(id)>0)
                    {
                        res.sendRedirect("SentServlet");
                    }       break;
                case "Delete":
                    if(SentMailProcessDAO.Delete(id)>0)
                    {
                        res.sendRedirect("SentServlet");
                    }       break;
                
            }
        }
        catch(Exception e)
        {
            log.fatal(e);
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
