package bca.leave.model;

/**
 *
 * @author Sony
 */
public class TrashBean {
    
    int id;
    String from,fwdby,msg,status,to,ltype,fdate,tdate;
    int day;
    
    /**
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id=id;
    }

    /**
     *
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @param from
     */
    public void setFrom(String from)
    {
        this.from=from;
    }

    /**
     *
     * @return
     */
    public String getFrom()
    {
        return from;
    }

    /**
     *
     * @param fwdby
     */
    public void stFwdby(String fwdby)
    {
        this.fwdby=fwdby;
    }

    /**
     *
     * @return
     */
    public String getFwdby()
    {
        return fwdby;
    }

    /**
     *
     * @param msg
     */
    public void setMsg(String msg)
    {
        this.msg=msg;
    }

    /**
     *
     * @return
     */
    public String getMsg()
    {
        return msg;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status)
    {
        this.status=status;
    }

    /**
     *
     * @return
     */
    public String getStatus()
    {
        return status;
    }

    /**
     *
     * @param to
     */
    public void setTo(String to)
    {
        this.to = to;
    }

    /**
     *
     * @return
     */
    public String getTo()
    {
        return to;
    }

    /**
     *
     * @param ltype
     */
    public void setLtype(String ltype)
    {
        this.ltype = ltype;
    }

    /**
     *
     * @return
     */
    public String getLtype()
    {
        return ltype;
    }

    /**
     *
     * @param fdate
     */
    public void setFdate(String fdate)
    {
        this.fdate=fdate;
    }

    /**
     *
     * @return
     */
    public String getFdate()
    {
        return fdate;
    }

    /**
     *
     * @param tdate
     */
    public void setTdate(String tdate)
    {
        this.tdate=tdate;
    }

    /**
     *
     * @return
     */
    public String getTdate()
    {
        return tdate;
    }

    /**
     *
     * @param day
     */
    public void setDay(int day)
    {
        this.day=day;
    }

    /**
     *
     * @return
     */
    public int getDay()
    {
        return day;
    }
    
}
