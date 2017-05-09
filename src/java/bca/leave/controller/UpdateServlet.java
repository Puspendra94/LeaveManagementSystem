package bca.leave.controller;
import org.apache.log4j.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.model.UserViewBean;
import bca.leave.DAO.UpdateDAO;
import bca.leave.DAO.LeaveRoleDAO;
import bca.leave.model.LeaveRoleBean;

public class UpdateServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        UserViewBean b=new UserViewBean();
       HttpSession session = req.getSession(false);
       
       log = Logger.getLogger(UpdateServlet.class.getName());
        String pass=req.getParameter("pass");
        
        b.setId(req.getParameter("id"));
        b.setUname(req.getParameter("uname"));
        b.setName(req.getParameter("name"));
        b.setDep(req.getParameter("dep"));

        String r1 = req.getParameter("r1");
        String r2 = req.getParameter("r2");
        int cl = Integer.valueOf(req.getParameter("cleave"));
        int al = Integer.valueOf(req.getParameter("aleave"));
        int sl = Integer.valueOf(req.getParameter("sleave"));
        int c = Integer.valueOf(req.getParameter("bc"));
        int a = Integer.valueOf(req.getParameter("ba"));
        int s = Integer.valueOf(req.getParameter("bs"));
        
        int ncl = cl-c;
        int nal = al-a;
        int nsl = sl-s;
        
        
        if(r1.equals(r2))
        {
            b.setCleave(Integer.parseInt(req.getParameter("cleave")));
            b.setAleave(Integer.parseInt(req.getParameter("aleave")));
            b.setSleave(Integer.parseInt(req.getParameter("sleave")));
            
            int cc = Integer.valueOf(req.getParameter("cl"));
            int aa = Integer.valueOf(req.getParameter("al"));
            int ss = Integer.valueOf(req.getParameter("sl"));
            
            b.setBcleave(cl-(cc-c));
            b.setBaleave(al-(aa-a));
            b.setBsleave(sl-(ss-s));
        }
        else
        {
            LeaveRoleBean bb = new LeaveRoleBean();
            bb.setRole(r1);
            
            if(LeaveRoleDAO.Valid(bb))
            {
                b.setCleave(bb.getCl());
                b.setAleave(bb.getAl());
                b.setSleave(bb.getSl());
                
                int cc = bb.getCl();
                int aa = bb.getAl();
                int ss = bb.getSl();
                
                b.setBcleave(cc-ncl);
                b.setBaleave(aa-nal);
                b.setBsleave(ss-nsl);
            }
        }
        
        b.setRole(r1);
//        if(pass.equals("")) 
//        {
//             b.setPass(req.getParameter("ppass"));   
//        }
//        else
//        {
//            b.setPass(pass); 
//        }
        b.setPass(pass);
        b.setPpass(req.getParameter("ppass"));
        
        if(UpdateDAO.Update(b)>0) 
        {
            log.info("User information of "+req.getParameter("uname")+" has been updated by "+(String)session.getAttribute("uname"));
            session.setAttribute("cmsg", true);
            session.setAttribute("msg", "User information has been updated sucessfully.");
            res.sendRedirect("AdminLoggedIn.jsp");
        }
        else
        {
            session.setAttribute("emsg", true);
            session.setAttribute("msg", "Some problem occured while updating user");
            req.getRequestDispatcher("ShowUserServlet").include(req, res); 
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
