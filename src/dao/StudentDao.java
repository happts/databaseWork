package dao;

import cn.itcast.jdbc.TxQueryRunner;
import entity.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDao {
    private  QueryRunner qr=new TxQueryRunner();

    public void add(Student s){
        try {
            String sql="insert into STUDENT value(?,?,?,?,?)";
            Object[] params={s.getSno(),s.getSname(),s.getSsex(),s.getSclass(),s.getSbirthday()};

            qr.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Student> findAll(){
        try {
            String sql="select * from STUDENT";
            return qr.query(sql,new BeanListHandler<Student>(Student.class));
        } catch (Exception e){
            throw new  RuntimeException(e);
        }
    }

    public List<Student> findAll(HashMap<String,String> params){
        try {
            StringBuilder sql=null;
            sql.append("select * from STUDENT group by ");
            return qr.query(sql.toString(),new BeanListHandler<Student>(Student.class));
        } catch (Exception e){
            throw new  RuntimeException(e);
        }
    }

    public Student find(String sno){
        try {
            String sql="select * from STUDENT where sno=?";
            return qr.query(sql,new BeanHandler<Student>(Student.class),sno);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void edit(Student student){
        try {
            String sql="update STUDENT set sname=?,ssex=?,sclass=?,sbirthday=? where sno=?";
            Object[] params={student.getSname(),student.getSsex(),student.getSclass(),student.getSbirthday(),student.getSno()};

            qr.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(String sno){
        try {
            String sql="delete from STUDENT where sno=?";
            qr.update(sql,sno);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Student>query(Student student){
        try {
            StringBuilder sql=new StringBuilder("select * from STUDENT where 1=1");
            List<Object> params=new ArrayList<>();

            String sno=student.getSno();
            if (sno!=null && !sno.trim().isEmpty()){
                sql.append(" and sno like ? ");
                params.add("%"+sno+"%");
            }


            String sname=student.getSname();
            if (sname!=null && !sname.trim().isEmpty()){
                sql.append(" and sname like ? ");
                params.add("%"+sname+"%");
            }

            String ssex=student.getSsex();
            if (ssex!=null && !ssex.trim().isEmpty()){
                sql.append(" and ssex = ? ");
                params.add(ssex);
            }

            String sclass=student.getSclass();
            if (sclass!=null && !sclass.trim().isEmpty()){
                sql.append(" and sclass like ? ");
                params.add("%"+sclass+"%");
            }

            Date sbirthday=student.getSbirthday();
//            String sbirthday1=null;
//            if (sbirthday!=null){
//                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                sbirthday1 = format.format(sbirthday);
//                sql.append(" and sbirthday like ? ");
//                params.add("%"+sbirthday1+"%");
//            }
            if (sbirthday!=null){
                sql.append(" and sbirthday like ? ");
                params.add("%"+sbirthday+"%");
            }


            return qr.query(sql.toString(),new BeanListHandler<Student>(Student.class),params.toArray());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<String> findSno(){
        try {
            String sql="select sno from student";
            return qr.query(sql,new BeanListHandler<String>(String.class));
        }catch (Exception e){
            throw new RuntimeException("查找学号失败",e);
        }
    }

}
