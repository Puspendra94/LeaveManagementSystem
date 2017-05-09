package bca.leave.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.UserViewBean;
import bca.leave.DAO.UserViewDAO;
import java.util.*;
import bca.leave.model.SelDep;
import bca.leave.DAO.UidDAO;

public class ViewUserServler extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out =res.getWriter();
        HttpSession session = req.getSession(false);
        
        UserViewBean b = new UserViewBean();
        String view = req.getParameter("view");
        String action = req.getParameter("action");
        b.setView(view);
        if(action.equals("Delete"))
        {
            req.getRequestDispatcher("DeleteServlet").forward(req, res);
        }
        else
        {
            if(view.equals(""))
        {
            req.getRequestDispatcher("ShowUserServlet").forward(req, res);
        }
        else
        {
            if(UserViewDAO.View(b))
        {
            
            req.setAttribute("id", b.getId());
            req.setAttribute("uname", b.getUname());
            req.setAttribute("pass", b.getPass());
            req.setAttribute("name", b.getName());
            req.setAttribute("dep", b.getDep());
            
            try
            {
                List<SelDep> group = UidDAO.getGroup();
                req.setAttribute("group", group);
            }
            catch(Exception e)
            {
                
            }

            req.setAttribute("role", b.getRole());
            req.setAttribute("cleave", b.getCleave());
            req.setAttribute("aleave", b.getAleave());
            req.setAttribute("sleave", b.getSleave());
            req.setAttribute("bc", b.getBcleave());
            req.setAttribute("ba", b.getBaleave());
            req.setAttribute("bs", b.getBsleave());
            req.setAttribute("date", b.getDate());
            req.setAttribute("time", b.getTime());
            
            req.getRequestDispatcher("ViewUserProfile.jsp").forward(req, res);
        }
        else
        {
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "Required user not found.");
            req.getRequestDispatcher("ShowUserServlet").include(req, res);
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
