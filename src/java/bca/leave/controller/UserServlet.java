package bca.leave.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.util.Date_Time;
import bca.leave.model.UserBean;
import bca.leave.DAO.UserDAO; 
import bca.leave.DAO.LeaveRoleDAO;
import bca.leave.model.LeaveRoleBean;
import java.util.Calendar;
import java.util.GregorianCalendar;
import bca.leave.util.PasswordValidator;
 
public class UserServlet extends HttpServlet {
 
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        
        HttpSession session = req.getSession(false);
        
        String id=req.getParameter("uid");
        String uname=req.getParameter("uname");
        String pass=req.getParameter("pass");
        String cpass=req.getParameter("cpass");
        String name=req.getParameter("name");
        String dep=req.getParameter("dep");
        String role=req.getParameter("role");
        String date=Date_Time.getDate();
        String time=Date_Time.getTime();
        int result = 0;
        UserBean ub=new UserBean();
        LeaveRoleBean b=new LeaveRoleBean();
        b.setRole(role);
        if(LeaveRoleDAO.Valid(b))
        {
           ub.setCl(b.getCl());
           ub.setAl(b.getAl());
           ub.setSl(b.getSl()); 
        }
        
        String value = PasswordValidator.validatePassword(pass);
        
        if(id==null || id.trim()=="" || uname==null || uname.trim()=="" || pass==null || pass.trim()=="" || cpass==null || cpass.trim()==""  || name==null || name.trim()=="" || dep==null || dep.trim()==""  || role==null || role.trim()=="" )
        {
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "All fields are manadatary<br>Please fill all the given fiels");
            req.getRequestDispatcher("UidServlet").include(req, res);
        }
       
        else if(!pass.equals(cpass))
        {
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "Passwords are not matched<br>Please enter the same password in confirm password as password field");
            req.getRequestDispatcher("UidServlet").forward(req, res);
        }
        
        else if(value.isEmpty() == false)
        {
            session.setAttribute("emsg", true);
            session.setAttribute("msg", value);
            req.getRequestDispatcher("UidServlet").forward(req, res);
            
        }
        
        else
        {
            ub.setId(id);
            ub.setUname(uname);
            ub.setPass(pass);
            ub.setName(name);
            ub.setDep(dep);
            ub.setRole(role);
            ub.setDate(date);
            ub.setTime(time);
            Calendar cal=new GregorianCalendar();
            int year=cal.get(Calendar.YEAR);
            ub.setYe(year);
            try {
                
                result = UserDAO.Validate(ub);
                if(result>0)
                {
                    session.setAttribute("cmsg", true);
                    session.setAttribute("msg", "User added sucessfully");
                    res.sendRedirect("AdminLoggedIn.jsp");
                }
                
                else
                { 
                    session.setAttribute("emsg", true);
                    session.setAttribute("msg", "Error has occured in the databse operation!");
                    req.getRequestDispatcher("UidServlet").forward(req, res);
                }
            } catch (Exception ex) {
                System.out.println(ex);
                session.setAttribute("emsg", true);
                session.setAttribute("msg", name+" is a registered user");
                req.getRequestDispatcher("UidServlet").forward(req, res);
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
