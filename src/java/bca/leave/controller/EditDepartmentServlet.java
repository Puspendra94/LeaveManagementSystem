package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.DeleteDepartmentBean;
import bca.leave.DAO.GetEmployeeDepartmentDAO;
import bca.leave.DAO.DeleteDepartmentDAO;

public class EditDepartmentServlet extends HttpServlet {

    private static Logger log = null;
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        HttpSession session = req.getSession(false);
        String uname = (String)session.getAttribute("uname");
        log = Logger.getLogger(EditDepartmentServlet.class.getName());
        
        String id = req.getParameter("id");
        String dname = req.getParameter("dname"+id);
        String hod = req.getParameter("hod"+id);
        req.setAttribute("id", id);
        req.setAttribute("dname", dname);
        req.setAttribute("hod", hod);
        String action = req.getParameter("action");
        
        if(action.equals("Edit"))
        {
            req.getRequestDispatcher("EditDepartment.jsp").forward(req, res);
        }
        else
        {
            DeleteDepartmentBean b = new DeleteDepartmentBean();
            b.setDname(dname);
            b.setDid(id);
            if(GetEmployeeDepartmentDAO.getNo(b))
            {
                int no = b.getNo();
                
                if(no>0)
                {
                    session.setAttribute("emsg", true);
                    session.setAttribute("msg", "This department has some user(s) ");
                    req.getRequestDispatcher("ShowDepServlet").forward(req, res);
                }
                else
                {
                    if(DeleteDepartmentDAO.delete(b)>0)
                    {
                        log.info(req.getParameter("dname")+" has been deleted by "+(String)session.getAttribute("uname"));
                        session.setAttribute("cmsg", true);
                        session.setAttribute("msg", "Department has been deleted sucessfully.");
                        req.getRequestDispatcher("ShowDepServlet").forward(req, res);
                    }
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
