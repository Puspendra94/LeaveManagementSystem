package bca.leave.util;
import org.apache.log4j.*;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
public class AdminFilter implements Filter {
    
     private FilterConfig fc;
     private static Logger log = null;

    public void init(FilterConfig fc) throws ServletException
    {
        this.fc = fc;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);
        log = Logger.getLogger(AdminFilter.class.getName());
        
        if(session == null)
        {
            log.fatal("Session is null");
            //req.getRequestDispatcher("/admin/Login.jsp").forward(request, response);
            res.sendRedirect("/LeaveManagement/admin/Login.jsp");
        }
        else if(session.getAttribute("uname") == null)
        {
            log.fatal("Session exit");
            session.invalidate();
            //req.getRequestDispatcher("/admin/Login.jsp").forward(request, response);
            res.sendRedirect("/LeaveManagement/admin/Login.jsp");
        }
        else
        {
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            res.setHeader("Pragma", "no-cache");
            res.setDateHeader("Expires", 0);
            
            chain.doFilter(request, response);
        }
    }
 
    @Override
    public void destroy() {
        
    }

   
}
