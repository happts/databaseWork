package entity;

import java.sql.Date;

/**
 * TEACHER表对应的实体类
 * @author 王浩然
 */

public class Student {
    private String sno=null;
    private String sname=null;
    private String ssex=null;
    private String sclass=null;
    private Date sbirthday=null;

    public void setSno(String sno) {
        this.sno = sno;
    }//对属性进行set操作，以下类似

    public String getSno() {
        return sno;
    }//对属性进行get操作，以下类似

    public void setSname(String sname){
        this.sname=sname;
    }

    public String getSname() {
        return sname;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSbirthday(Date sbirthday) {
        this.sbirthday = sbirthday;
    }

    public Date getSbirthday() {
        return this.sbirthday;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
