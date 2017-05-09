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

public class UserFilter implements Filter {
    
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
        
        HttpSession ses = req.getSession(false);
        log = Logger.getLogger(UserFilter.class.getName());
       try
       {
            if(ses == null)
        {
            log.error("Session is null");
            res.sendRedirect("/LeaveManagement/index.html");
        }
        else if(ses.getAttribute("uname") == null)
        {
            log.error("Session exit");
            ses.invalidate();
            res.sendRedirect("/LeaveManagement/index.html");
        }
        else
        {
            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            res.setHeader("Pragma", "no-cache");
            res.setDateHeader("Expires", 0);
            
            chain.doFilter(request, response);
        }
       }
       catch(Exception e)
       {
           log.fatal("Invalid Login!!!");
           System.err.println("The problem in UserFilter is :"+e.getCause()+"\n"+e.getMessage());
       }
    }

    @Override
    public void destroy() {
        
    }

   
}
