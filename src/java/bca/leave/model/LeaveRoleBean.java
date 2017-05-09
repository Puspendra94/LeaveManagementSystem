package bca.leave.model;

/**
 *
 * @author Sony
 */
public class LeaveRoleBean {
    
    String role;
    int cl,al,sl;
    
    /**
     *
     * @param role
     */
    public void setRole(String role)
    {
        this.role=role;
    }

    /**
     *
     * @return
     */
    public String getRole()
    {
        return role;
    }
    
    /**
     *
     * @param cl
     */
    public void setCl(int cl)
    {
        this.cl=cl;
        
    }

    /**
     *
     * @return
     */
    public int getCl()
    {
        return cl;
    }
    
    /**
     *
     * @param al
     */
    public void setAl(int al)
    {
        this.al=al;
        
    }

    /**
     *
     * @return
     */
    public int getAl()
    {
        return al;
    }
    
    /**
     *
     * @param sl
     */
    public void setSl(int sl)
    {
        this.sl=sl;
        
    }

    /**
     *
     * @return
     */
    public int getSl()
    {
        return sl;
    }
    
}
