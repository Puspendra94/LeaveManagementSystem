package bca.leave.controller;

import org.apache.log4j.*;
import bca.leave.DAO.NotifApplyDAO;
import bca.leave.util.Date_Time;
import bca.leave.model.EditAppliedLeaveBean;
import bca.leave.DAO.UpdateAppliedLeaveDAO;
import bca.leave.model.NotifApplyBean;
import bca.leave.util.DateValidator;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateAppliedLeaveServlet extends HttpServlet {

    private static Logger log = null;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        
        log = Logger.getLogger(UpdateAppliedLeaveServlet.class.getName());
        HttpSession ses = req.getSession(false);
        String uname=(String)ses.getAttribute("name");
        
        String fod = req.getParameter("fod");
        String tod = req.getParameter("tod");
        
        String date = Date_Time.getDate();
        String d = req.getParameter("day");
        int day = Integer.parseInt(d);
        String c = req.getParameter("cl");
        
        int cl = Integer.parseInt(c);
        String a = req.getParameter("al");
        int al = Integer.parseInt(a);
        String s = req.getParameter("sl");
        int sl = Integer.parseInt(s); 
        
        int dl = Integer.valueOf(req.getParameter("dl"));
        int lwp = Integer.valueOf(req.getParameter("lwp"));
        int spl = Integer.valueOf(req.getParameter("spl"));
        int ao = Integer.valueOf(req.getParameter("ao"));
        
        int id = Integer.valueOf(req.getParameter("id"));
        
        String type = req.getParameter("type");
        
        String ff = req.getParameter("fwdby");
        String fwd="";
        
        String tt=req.getParameter("to");
        
        if(tt.equals("") || tt.trim().equals("") || uname.trim().equals("") || fod.trim().equals("") || tod.trim().equals("") || req.getParameter("area2").equals("") || req.getParameter("area2").trim().equals(""))
        {
            ses.setAttribute("msg", "You have to enter all the mandatory fields in the application");
            ses.setAttribute("emsg", true);
            req.getRequestDispatcher("/SentServlet").forward(req, res);
        }
        else if(DateValidator.validateDate(fod.trim()) == false || DateValidator.validateDate(tod.trim()) == false)
        {
            ses.setAttribute("msg", "Please enter a Valid date with the given form");
            ses.setAttribute("emsg", true);
            req.getRequestDispatcher("/SentServlet").forward(req, res); 
        }
        else if(type.equals("Casual Leave") && day > cl)
        { 
            ses.setAttribute("msg", "You have not entered sufficient Casual Leave<br>Please try again");
            ses.setAttribute("emsg", true);
            req.getRequestDispatcher("/SentServlet").forward(req, res);
        }
        else if(type.equals("Annual Leave") && day > al)
        {
            ses.setAttribute("msg", "You have not entered sufficient Annual Leave<br>Please try again");
            ses.setAttribute("emsg", true);
            req.getRequestDispatcher("/SentServlet").forward(req, res);
        }
        else if(type.equals("Sick/Medical Leave") && day > sl)
        {
            ses.setAttribute("msg", "You have not entered sufficient Sick/Medical Leave<br>Please try again");
            ses.setAttribute("emsg", true);
            req.getRequestDispatcher("/SentServlet").forward(req, res);
        }
        else
        {
            if(ff.equals(""))
            {
                fwd = "NONE";
            }
            else
            {
                fwd = ff;
            }
            EditAppliedLeaveBean b=new EditAppliedLeaveBean();
            b.setUname(uname);
            b.setLtype(type);
            b.setFod(req.getParameter("fod"));
            b.setTod(req.getParameter("tod"));
            b.setDays(day);
            b.setTo(tt);
            b.setFwdby(fwd);
            b.setCl(cl);
            b.setAl(al);
            b.setSl(sl);
            b.setDl(dl);
            b.setLwp(lwp);
            b.setSpl(spl);
            b.setAo(ao);
            b.setMsg(req.getParameter("area2"));
            b.setId(id);
            
            int status = UpdateAppliedLeaveDAO.Validate(b);
            
            if(status > 0)
            {
                log.info(req.getParameter("uname")+" send an application to "+req.getParameter("to")+" forwarded by "+req.getParameter("fwdby"));
                NotifApplyBean nab = new NotifApplyBean();
                nab.setMsg("You have a pending "+req.getParameter("type")+" application from "+uname+" to "+req.getParameter("to"));
                nab.setOndate(date);
                nab.setFod(req.getParameter("fod"));
                nab.setTod(req.getParameter("tod"));
                nab.setButton("<input type=\"button\" value=\"View Inbox\"/>");
                nab.setNstatus("unread");
                nab.setCheck("inbox");
                nab.setTo(tt);
                
                nab.setFwdby(fwd); 
                
                if(NotifApplyDAO.Notify(nab)>0)
                {
                    ses.setAttribute("msg", "Your "+type+" Application has been successfully Updated.");
                    ses.setAttribute("cmsg", true); 
                    req.getRequestDispatcher("pages/Message.jsp").forward(req, res);
                }
            }
            else if(status == 0)
            {
                ses.setAttribute("emsg", true);
                ses.setAttribute("msg", "Your "+type+" application has not been sucessfully Updated.");
                req.getRequestDispatcher("SentServlet").forward(req, res);
            }
            else
            {
                log.error(req.getParameter("uname")+" has been failed to update an application");
                ses.setAttribute("msg", "Error has occured in the databse operation!");
                ses.setAttribute("emsg", true);
                req.getRequestDispatcher("SentServlet").forward(req, res);
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
