package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.ChangePassBean;
import bca.leave.DAO.ChangePassDAO;
import bca.leave.util.PasswordValidator;

public class ChangePasswordServlet extends HttpServlet {

    private static Logger log = null;
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        log = Logger.getLogger(ChangePasswordServlet.class.getName());
        
        HttpSession ses = req.getSession(false);
        
        String opass=req.getParameter("opass");
        String npass=req.getParameter("npass");
        String cnpass=req.getParameter("cnpass");
        
        String value = PasswordValidator.validatePassword(npass);
        ChangePassBean cb=new ChangePassBean();
        cb.setOpass(opass);
        cb.setNpass(npass); 
        cb.setUname((String)ses.getAttribute("uname"));
        
        if(value.isEmpty() == false)
        {
             ses.setAttribute("emsg", true);
             ses.setAttribute("msg", value);
             req.getRequestDispatcher("pages/EditProfile.jsp").include(req, res); 
        }
        else if(npass.equals(cnpass))
        {
             if(ChangePassDAO.ChangePass(cb)>0)
             {
                 log.info((String)ses.getAttribute("uname")+" has been changed his password sucessfully");
                 ses.setAttribute("cmsg", true);
                 ses.setAttribute("msg", "Your Password has been successfuly changed");
                 req.getRequestDispatcher("pages/Message.jsp").include(req, res);
             }
             else
             {
                 
                 ses.setAttribute("emsg", true);
                 ses.setAttribute("msg", "Opsss...\nYour old password should be wrong");
                 req.getRequestDispatcher("pages/EditProfile.jsp").include(req, res);
             }
        }
        
        else
        {
            ses.setAttribute("emsg", true);
            ses.setAttribute("msg", "Your passwords are not mached.");
            req.getRequestDispatcher("pages/EditProfile.jsp").include(req, res);
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
