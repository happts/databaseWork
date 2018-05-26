package entity;

public class Sc {
    private String sno;
    private String cno;
    private Integer grade=null;

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSno() {
        return sno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCno() {
        return cno;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }
}
