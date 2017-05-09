package bca.leave.controller;

import com.google.gson.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.DAO.AutoNotifCountDAO;

public class AutoNotifCountServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = res.getWriter();
        HttpSession ses = req.getSession(false);
        String uname = (String)ses.getAttribute("name");
        
       int fileNames = AutoNotifCountDAO.get(uname);

            // database related error so don't send json data and let the browser to show the alert box
//            if (fileNames == 0) {
//                return;
//            }
           ses.setAttribute("ac", false);
           
 
            String searchList = new Gson().toJson(fileNames);

            res.setContentType("application/json");
            int a = Integer.parseInt(searchList);
            ses.setAttribute("a", a);

            out.write(searchList);
    
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
