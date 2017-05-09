package bca.leave.controller;

import org.apache.log4j.*;

import bca.leave.DAO.ApplyDAO;
import bca.leave.model.ApplyBean;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import bca.leave.DAO.GetBalanceLeaveDAO;
import bca.leave.model.GetBalanceLeaveBean;
 
public class ApplyServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
       HttpSession ses = req.getSession(false);
       log = Logger.getLogger(ApplyServlet.class.getName());
       
        String uname = (String)ses.getAttribute("uname");
        
        if(ses.equals("")||uname.equals(""))
        {
            res.sendRedirect("index.html");
        }
        else
        {
            GetBalanceLeaveBean bb = new GetBalanceLeaveBean();
            bb.setUname(uname);
            
            
        
        try
        {
            if(GetBalanceLeaveDAO.getBal(bb))
            {
                 
                req.setAttribute("bb", bb);
                
            }
            
            
            List<ApplyBean> b = ApplyDAO.getValue();
            req.setAttribute("b", b);
            req.getRequestDispatcher("pages/ApplyLeave.jsp").forward(req, res);
        }
        catch(Exception e)
        {
            log.fatal("Leave Application is not showing for "+uname);
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
