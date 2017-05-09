package bca.leave.controller;

import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.DAO.AdminRegisterDAO;
import bca.leave.model.AdminRegisterBean;
import bca.leave.util.PasswordValidator;

public class AdminRegisterServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        log = Logger.getLogger(AdminRegisterServlet.class.getName());
        
        String uname = req.getParameter("uname");
        String pass = req.getParameter("pass");
        String cpass = req.getParameter("cpass");
        
        log.info(req.getParameter("uname")+" has been atempt to register himself as an administrator");
        String value = PasswordValidator.validatePassword(pass);
        
        if(!pass.equals(cpass))
        {
            req.setAttribute("emsg", true);
            req.setAttribute("msg", "Your passwords are not matched.");
            req.getRequestDispatcher("install.jsp").include(req, res);
        }
        else if(value.isEmpty() == false)
        {
            req.setAttribute("emsg", true);
            req.setAttribute("msg", value);
            req.getRequestDispatcher("install.jsp").include(req, res);
        }
        else
        {
            AdminRegisterBean b = new AdminRegisterBean();
            
            b.setUname(uname);
            b.setPass(pass);
            
            int status = AdminRegisterDAO.Validate(b);
            
            if(status > 0)
            {
                log.info(req.getParameter("uname")+" has been registered as an administrator");
                out.println("<font color=\"green\">Congratulation <br>You sucessfully registered.</font>");
                req.getRequestDispatcher("AdminHitCounter").forward(req, res);
               
            }
            else if(status == 0)
            {
                out.println("<font color=\"red\">User has registered before</font>");
                req.getRequestDispatcher("index.jsp").include(req, res);
            }
            else
            {
                log.error("Error is occuered while "+req.getParameter("uname")+" is attempt to registered as an administrator");
                out.println("<font color=\"red\">Error in database operation has occured</font>");
                req.getRequestDispatcher("install.jsp").include(req, res);
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
