package bca.leave.model;

/**
 *
 * @author Sony
 */
public class AdminLoginBean {
    
    String uname,pass;
    int year;
    
    /**
     *
     * @param uname
     */
    public void setName(String uname)
    {
        this.uname=uname;
    }
    
    /**
     *
     * @return
     */
    public String getName()
    {
        return uname;
    }
    
    /**
     *
     * @param pass
     */
    public void setPass(String pass)
    {
        this.pass=pass;
    }
    
    /**
     *
     * @return
     */
    public String getPass()
    {
        return pass;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }
    
    
    
}
