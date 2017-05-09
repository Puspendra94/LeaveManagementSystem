package bca.leave.controller;
import org.apache.log4j.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.UserViewBean;
import bca.leave.DAO.DeleteUserDAO; 

public class DeleteServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        HttpSession session = req.getSession(false);
        String uname = (String)session.getAttribute("uname");
        
        log = Logger.getLogger(DeleteServlet.class.getName());
        
        UserViewBean b=new UserViewBean();
        b.setId(req.getParameter("view")); 
        
        if(DeleteUserDAO.Delete(b)>0)
        {
            log.info("User ID "+req.getParameter("view")+" has been deleted by "+uname);
            session.setAttribute("cmsg", true);
            session.setAttribute("msg", "User has been deleted sucessfully.");
            res.sendRedirect("ShowUserServlet");
        }
        else
        {
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "Sorry!!!Some problem occured during process<br>Please try again.");
             res.sendRedirect("ShowUserServlet");
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
