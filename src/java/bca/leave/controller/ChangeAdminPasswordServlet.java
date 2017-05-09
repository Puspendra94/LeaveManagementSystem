package bca.leave.controller;

import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.DAO.ChangeAdminPasswordDAO;
import bca.leave.model.ChangeAdminPasswordBean;
import bca.leave.util.PasswordValidator;

public class ChangeAdminPasswordServlet extends HttpServlet {

    private static Logger log = null;
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        log = Logger.getLogger(ChangeAdminPasswordServlet.class.getName());
        String npass = req.getParameter("p2");
        String cnpass = req.getParameter("p3");
        
        String value = PasswordValidator.validatePassword(npass);
        ChangeAdminPasswordBean b = new ChangeAdminPasswordBean();
        
        b.setUname((String)session.getAttribute("uname"));
        b.setOp(req.getParameter("p1"));
        b.setNp(req.getParameter("p2"));
        log.info((String)session.getAttribute("uname")+" attempt to change the administrator password");
        if(!npass.equals(cnpass))
        {
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "New password and confirm password must be same");
            req.getRequestDispatcher("ChangePassword.jsp").forward(req, res);
        }
        else if(value.isEmpty() == false)
        {
            session.setAttribute("emsg", true);
            session.setAttribute("msg", value);
            req.getRequestDispatcher("ChangePassword.jsp").forward(req, res);
        }
        else
        {
            
                if(ChangeAdminPasswordDAO.change(b)>0)
                {
                    log.info((String)session.getAttribute("uname")+" has changed the administrator password sucessfully");
                    session.setAttribute("cmsg", true);
                    session.setAttribute("msg", "Admin Password has been sucessfully changed...");
                    req.getRequestDispatcher("AdminLoggedIn.jsp").forward(req, res);
                }
                else
                {
                    
                    session.setAttribute("emsg", true);
                    session.setAttribute("msg", "Your old password may be incorrect");
                    req.getRequestDispatcher("ChangePassword.jsp").forward(req, res);
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
