package bca.leave.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bca.leave.DAO.InboxProcessDAO;
import bca.leave.util.Date_Time;
import bca.leave.DAO.UpdateNotifDAO;
import bca.leave.model.UpdateNotifBean;
import bca.leave.DAO.UpdateBalanceLeaveDAO;
import bca.leave.model.UpdateBalanceLeaveBean;
import bca.leave.DAO.ForwardNotifDAO;
import bca.leave.model.NotifApplyBean;

public class InboxProcessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        HttpSession ses=req.getSession(false);
        String uname=(String)ses.getAttribute("name");
        String msg="";
        
        String i=req.getParameter("cb");
        int id=Integer.parseInt(i);
        String action=req.getParameter("action");
        String ltype = req.getParameter("ltype"+i);
        String to = req.getParameter("to"+i);
        String from = req.getParameter("from"+i);
        String fwdby = req.getParameter("fwdby"+i);
        String fod = req.getParameter("fod"+i);
        String tod = req.getParameter("tod"+i);
        String d = req.getParameter("day"+i);
        int day = Integer.parseInt(d);
        String c = req.getParameter("cl"+i);
        int cl = Integer.parseInt(c);
        String a = req.getParameter("al"+i);
        int al = Integer.parseInt(a);
        String s = req.getParameter("sl"+i);
        int sl = Integer.parseInt(s);
        int dl = Integer.parseInt(req.getParameter("dl"+i));
        int lwp = Integer.parseInt(req.getParameter("lwp"+i));
        int spl = Integer.parseInt(req.getParameter("spl"+i));
        int ao = Integer.parseInt(req.getParameter("ao"+i));
        
        String date = Date_Time.getDate();
        
        String m=null;
        
        switch (action) {
            case "Approve":
                        switch (ltype)
                        {
                            case "Casual Leave":
                                cl = cl-day;
                                break;
                            case "Annual Leave":
                                al = al-day;
                                break;
                            case "Sick/Medical Leave":
                                sl = sl-day;
                                break;
                            case "Duty Leave":
                                dl=dl+day;
                                
                                break;
                            case "Leave Without Pay":
                                lwp = lwp+day;
                                
                                break;
                            case "Special Leave":
                                spl = spl+day;
                                
                                break;
                            case "Any Other":
                                ao = ao+day;
                                
                                break;
                            default :
                                break;
                        }
                m="Approved";
                msg = "Your "+ltype+" application has been approved by "+uname;
                break;
            case "Reject":
                m="Rejected";
                msg = "Your "+ltype+" application has been rejected by "+uname;
                break;
            case "Forward":
                m="Forwarded";
                msg = "Your "+ltype+" application has been forwarded by "+uname+" to "+to;
                break;
            case "Delete":
                if(InboxProcessDAO.Delete(id)>0)
                {
                    ses.setAttribute("msg", "Deleted");
                    ses.setAttribute("cmsg", true);
                    req.getRequestDispatcher("InboxServlet").forward(req, res);
                }   break;
        }
        
        if(InboxProcessDAO.Update(id, m)>0)
        {
           UpdateBalanceLeaveBean v = new UpdateBalanceLeaveBean();
           v.setFrom(from);
           v.setCl(cl);
           v.setAl(al);
           v.setSl(sl);
           v.setDl(dl);
           v.setLwp(lwp);
           v.setSpl(spl);
           v.setAo(ao);
           
           if(UpdateBalanceLeaveDAO.Update(v)>0)
           {
                UpdateNotifBean b = new UpdateNotifBean();
                b.setMsg(msg);
                b.setOndate(date);
                b.setTo(from);
                b.setFwdby(req.getParameter("fwdby"+i)); 
                b.setFod(fod);
                b.setTod(tod);
                b.setBut("");
                b.setStatus(m); 
                b.setNstatus("unread");
                b.setNcheck("other");
                ses.setAttribute("msg", m);
                ses.setAttribute("cmsg", true);
                if(action.equals("Forward"))
                {
                    if(UpdateNotifDAO.UpdateNotif(b)>0)
                {
                    NotifApplyBean nab = new NotifApplyBean();
                    nab.setMsg("You have a new Forwarded "+ltype+" application from "+from+" to "+to+" Forwarded By "+uname);
                    nab.setOndate(date);
                    nab.setFod(fod);
                    nab.setTod(tod);
                    nab.setButton("<input type=\"button\" value=\"View Inbox\"/>");
                    nab.setNstatus("unread");
                    nab.setCheck("inbox");
                    nab.setTo(to);
                    nab.setFwdby(fwdby); 
                    if(ForwardNotifDAO.Notify(nab)>0)
                    {
                        res.sendRedirect("InboxServlet");
                    }
                }
                else
                {
                    res.sendRedirect("InboxServlet");
                }
                }
                else
                {
                    if(UpdateNotifDAO.UpdateNotif(b)>0)
                {
                    res.sendRedirect("InboxServlet");
                }
                else
                {
                    res.sendRedirect("InboxServlet");
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
