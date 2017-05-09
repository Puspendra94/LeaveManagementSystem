package bca.leave.model;

/**
 *
 * @author Sony
 */
public class AddGroupBean {
    
    String gname;
    int cl,al,sl;
    
    /**
     *
     * @param gname
     */
    public void setName(String gname)
    {
        this.gname=gname;
    }
    
    /**
     *
     * @return
     */
    public String getName()
    {
        return gname;
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
