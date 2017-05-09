package bca.leave.util;
import java.util.*;

/**
 *
 * @author Sony
 */
public class Date_Time {
    
    /**
     *
     */
    public static String date=null;

    /**
     *
     */
    public static String time=null;
    
    /**
     *
     * @return
     */
    public static String getDate()
    {
        
        
        Calendar cal=new GregorianCalendar();
        int year=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int mon=m+1;
        
        date=day+"/"+mon+"/"+year;
        //Date d=new java.sql.Date(day, mon+1, year);
        return date;
    }
    
    /**
     *
     * @return
     */
    public static String getTime()
    {
        Calendar cal=new GregorianCalendar();
        int hr=cal.get(Calendar.HOUR);
        int min=cal.get(Calendar.MINUTE);
        int sec=cal.get(Calendar.SECOND);
        
        time=hr+" : "+min+" : "+sec;
        
        return time;
    }
    
}
