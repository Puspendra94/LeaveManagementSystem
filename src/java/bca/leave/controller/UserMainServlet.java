package bca.leave.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.util.PasswordValidator;

public class UserMainServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        HttpSession session = req.getSession(false);
        
        String action=req.getParameter("action");
        String pass=req.getParameter("pass");
        String cpass=req.getParameter("cpass");
        
        String value = PasswordValidator.validatePassword(pass);
        
       if(pass.isEmpty() == false && value.isEmpty() == false)
       {
           System.err.println("I get in");
           session.setAttribute("emsg", true);
           session.setAttribute("msg", value);
           req.getRequestDispatcher("ShowUserServlet").forward(req, res);
       }
        if(pass.equals(cpass))
        {
            if(action.equals("Update"))
            {
                req.getRequestDispatcher("UpdateServlet").forward(req, res);
            }
            else
            {
                
                req.getRequestDispatcher("DeleteServlet").forward(req, res);
            }
        }
        else
        {
            req.getRequestDispatcher("ViewUser.jsp").include(req, res);
            out.println("<br><br><font color=\"red\">Passwords not mached.</font>");
         
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
