package entity;

/**
 * Create by PstereoM on 2018/5/6
   张仲昊
 **/
public class Teach {
    private String cno;//课程遍号
    private String tno;//教师编号
    private String id;//主编号

    public void setId(String id) {
        this.id = id;
    }//设置主编号

    public String getId() {
        return id;
    }//返回主编号，下同

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCno() {
        return cno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTno() {
        return tno;
    }
}

