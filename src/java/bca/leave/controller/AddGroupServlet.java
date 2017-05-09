package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.AddGroupBean;
import bca.leave.DAO.AddGroupDAO;

public class AddGroupServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        
        log = Logger.getLogger(AddGroupDAO.class.getName());
        
        AddGroupBean b = new AddGroupBean();
        b.setName(req.getParameter("gname"));
        String cl=req.getParameter("cl");
        String al=req.getParameter("al");
        String sl = req.getParameter("sl");
        int cll=Integer.parseInt(cl);
        int all=Integer.parseInt(al);
        int sll=Integer.parseInt(sl);
        b.setCl(cll);
        b.setAl(all);
        b.setSl(sll); 
        
        int result = AddGroupDAO.Validate(b);
        
        if(result == 1)
        {
            log.info(req.getParameter("gname")+" group has been added sucessfully");
            session.setAttribute("cmsg", true);
            session.setAttribute("msg", "Group has been added sucessfully.");
            res.sendRedirect("AdminLoggedIn.jsp");
        }
        else if(result == 0)
        {
            
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "Group has been not added sucessfully.");
        }
        else
        {
            log.error(req.getParameter("gname")+" hroup has been not added");
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "Error has occured in the databse operation!");
            req.getRequestDispatcher("AddGroup.jsp").include(req, res);
            
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
