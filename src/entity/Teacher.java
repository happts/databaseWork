package entity;

import java.sql.Date;

public class Teacher {
    private String tno;
    private String tname;
    private String tsex;
    private Date tbirthday=null;
    private String tdepart;
    private String ttitle;

    public void setTno(String tno){
        this.tno=tno;
    }

    public String getTno(){
        return tno;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTname() {
        return tname;
    }

    public void setTbirthday(Date tbirthday) {
        this.tbirthday = tbirthday;
    }

    public Date getTbirthday() {
        return tbirthday;
    }

    public void setTdepart(String tdepart) {
        this.tdepart = tdepart;
    }

    public String getTdepart() {
        return tdepart;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }

    public String getTsex() {
        return tsex;
    }

    public void setTtitle(String ttitle) {
        this.ttitle = ttitle;
    }

    public String getTtitle() {
        return ttitle;
    }
}
