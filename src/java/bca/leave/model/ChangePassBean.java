
package bca.leave.model;

/**
 *
 * @author Sony
 */
public class ChangePassBean {
    
    String opass,npass,uname;
    
    /**
     *
     * @param opass
     */
    public void setOpass(String opass)
    {
        this.opass=opass;
    }
    
    /**
     *
     * @return
     */
    public String getOpass()
    {
        return opass;
    }
    
    /**
     *
     * @param npass
     */
    public void setNpass(String npass)
    {
        this.npass=npass;
    }
    
    /**
     *
     * @return
     */
    public String getNpass()
    {
        return npass;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }
    
    
    
}
