package domain;

import java.util.ArrayList;

/**
 *苗建伟 1030616335
 * 添加大作业一所有sql语句
 **/
public class domainHome {
    private ArrayList<String> sql1=new ArrayList<>();

    public void setSql1(ArrayList<String> sql1) {
        this.sql1 = sql1;
    }

    public ArrayList<String> getSql1() {
        return sql1;
    }

    public String getSql1(int index){
        return sql1.get(index);
    }

    /**
     * 大作业一的所有要求
     * @return arrylist集合
     */
    public ArrayList<String> addData(){
        ArrayList<String> sql1=new ArrayList<>();
        this.sql1.add("SELECT SNAME,SSEX,SCLASS FROM STUDENT");//1.	能查询学生姓名、性别和班级信息
        this.sql1.add("SELECT DISTINCT TDEPART FROM TEACHER");//2.	能查询教师所有的单位。
        this.sql1.add("SELECT * FROM STUDENT");//3.能查询学生的所有信息。
        this.sql1.add("SELECT * FROM STUDENT,SCORE  WHERE STUDENT.SNO=SCORE.SNO AND GRADE>? AND GRADE<?");//4.能查询出成绩在60到80的学生的信息。
        this.sql1.add("SELECT STUDENT.* FROM STUDENT,SCORE WHERE STUDENT.SNO=SCORE.SNO AND GRADE=?");//5.能查询某个成绩的学生的信息。
        this.sql1.add("SELECT * FROM STUDENT WHERE SCLASS=? OR SSEX=?");//6、 能按照班级或性别来查询学生信息（比如班级为“95031”，性别为女）。
        this.sql1.add("SELECT * FROM ");//7、 能以某个字段的升序，同时以某个字段的降序来查询某张表（任选某个表）
        this.sql1.add("SELECT SCLASS ,COUNT(*) AS NUM_PEOPLE FROM STUDENT WHERE SCLASS=? GROUP BY SCLASS");//8、 能统计某个班的人数。
        this.sql1.add("SELECT SNO,CNO FROM SCORE WHERE GRADE=(SELECT  MAX(GRADE) FROM SCORE)");//9、 能查询最高分的学生学号和课程号。
        this.sql1.add("SELECT CNO,AVG(GRADE) FROM SCORE GROUP BY CNO");//10、能查询每门课的平均成绩。
        this.sql1.add("SELECT CNO,AVG(GRADE) FROM SCORE GROUP BY CNO HAVING COUNT(*)>? AND CNO LIKE ?");//11、能查询至少有5名学生选修的并以3开头的课程的平均分数。
        this.sql1.add("SELECT SCLASS,AVG(GRADE) FROM STUDENT,SCORE WHERE STUDENT.SNO=SCORE.SNO AND SCLASS=? GROUP BY SCLASS");//12、 能查询某个班学生的平均分。
        this.sql1.add("SELECT SNO,CNO,RANK FROM SCORE,GRADE_RANK WHERE GRADE>=LOW AND GRADE<=UP");//13、假设使用如下命令建立了一个grade表,现查询所有同学的Sno、Cno和rank列。
        this.sql1.add("SELECT * FROM SCORE WHERE CNO=? AND GRADE> ANY (SELECT GRADE FROM SCORE WHERE SNO=?)");//14、 能查询选修某个课程的成绩高于某个学号同学成绩的所有同学的记录。
        this.sql1.add("SELECT * FROM STUDENT WHERE SBIRTHDAY IN (SELECT SBIRTHDAY FROM STUDENT WHERE SNO=?)");//15、能查询跟学号为8的同学同年出生的所有学生的学生信息。
        this.sql1.add("SELECT DISTINCT SNAME, GRADE FROM COURSE,TEACHER,SCORE,STUDENT,TEACH WHERE SCORE.CNO=COURSE.CNO AND TEACH.TNO=TEACHER.TNO AND COURSE.CNO=TEACH.CNO AND TNAME=? AND SCORE.SNO=STUDENT.SNO");//16、能查询某位教师任课的学生成绩。
        this.sql1.add("SELECT TNAME,COUNT(SNO) FROM TEACHER,TEACH,SCORE WHERE SCORE.CNO=TEACH.CNO AND TEACH.TNO=TEACHER.TNO GROUP BY TEACH.CNO  HAVING COUNT(*)>?");//17、能查询选修某课程的同学人数多于5人的教师姓名
        this.sql1.add("SELECT DISTINCT CNO FROM SCORE WHERE GRADE > ?");//18、能查询存在有某个分数（比如85分）以上成绩的课程号.
        this.sql1.add("SELECT*  FROM SCORE WHERE CNO IN(SELECT  CNO FROM TEACH WHERE TNO IN(SELECT TNO FROM TEACHER WHERE TDEPART=?))");//19、能查询出某个系教师所教课程的成绩表。
        this.sql1.add("SELECT * FROM SCORE WHERE CNO=? AND GRADE>ANY(SELECT GRADE FROM SCORE WHERE CNO=?) ORDER BY GRADE DESC");//20、能查询某个选修编号的课程且成绩至少高于另一个选修编号课程的同学的学号、课程号以及成绩，并按成绩从高到低次序排序。
        this.sql1.add("SELECT SNAME NAME,SSEX SEX,SBIRTHDAY BIRTHDAY FROM STUDENT WHERE SSEX=? UNION SELECT TNAME,TSEX,TBIRTHDAY FROM TEACHER WHERE TSEX=?");//21、能查询所有“女”教师和“女”同学的姓名、性别和出生年月.
        this.sql1.add("SELECT * FROM SCORE, (SELECT CNO,AVG(GRADE)A_G FROM SCORE GROUP BY CNO)A_GRADE WHERE SCORE.CNO=A_GRADE.CNO AND SCORE.GRADE<A_GRADE.A_G");//22、 能查询成绩比该课程平均成绩低的同学的成绩表。
        this.sql1.add("SELECT TNAME, TDEPART, TTITLE FROM TEACHER");//23、能查询所有任课教师的姓名、部门和职称.
        this.sql1.add("SELECT TNAME,TDEPART FROM TEACHER WHERE TNO NOT IN(SELECT TNO FROM TEACH WHERE CNO IN(SELECT CNO FROM SCORE))");//24 、能查询所有未讲课的教师的姓名和部门.
        this.sql1.add("SELECT * FROM STUDENT WHERE SNAME NOT LIKE ?");//25、查询不姓“王”的同学记录。
        this.sql1.add("SELECT MAX(SBIRTHDAY),MIN(SBIRTHDAY) FROM STUDENT");//26、查询学生最大和最小的出生日期值。
        this.sql1.add("SELECT TNAME,CNAME FROM TEACHER,TEACH,COURSE WHERE TEACH.TNO=TEACHER.TNO AND TEACH.CNO=COURSE.CNO AND TEACHER.TSEX=?");//27、查询某性别教师及其所上的课程。
        this.sql1.add("SELECT SNAME FROM STUDENT WHERE SSEX = (SELECT SSEX FROM STUDENT WHERE SNAME = ? )");//28、查询和“李军”同性别的所有同学的姓名
        this.sql1.add("SELECT SNAME FROM STUDENT WHERE (SSEX,SCLASS) = (SELECT SSEX,SCLASS FROM STUDENT WHERE SNAME = ?)");//28.2查询和“李军”同性别并同班的同学姓名。
        this.sql1.add("SELECT SNO,CNO,GRADE FROM SCORE WHERE CNO=(SELECT CNO FROM COURSE WHERE CNAME=?) AND SNO IN(SELECT  SNO FROM STUDENT WHERE SSEX=?)");//29、查询所有选修“计算机网络”课程的“男”同学的成绩表。
        return sql1;
    }
}
