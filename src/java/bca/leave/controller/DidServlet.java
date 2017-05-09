
package bca.leave.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import bca.leave.DAO.DidDAO;

public class DidServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        HttpSession session = req.getSession(false);
        
        if(session.getAttribute("uname").equals(""))
        {
            res.sendRedirect("../index.jsp");
        }
        else
        {
            Calendar cal=new GregorianCalendar();
        int year=cal.get(Calendar.YEAR);
        
        String du="DU";
        
        int did=DidDAO.getLastID();
        int id=did+1;
        
        String result=du+"/"+year+"/"+id;
        
        session.setAttribute("msg", "");
        session.setAttribute("emsg", false);
        session.setAttribute("cmsg", false);
        req.setAttribute("id", id); 
        req.setAttribute("result", result);
        req.getRequestDispatcher("AddDepartment.jsp").forward(req, res); 
        
        }
    }

}
