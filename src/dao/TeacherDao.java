package dao;

import cn.itcast.jdbc.TxQueryRunner;
import entity.Teacher;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据操作层
 * 数据库中TEACHER表的操作类
 * 增删改查
 * 
 * @author 黄涛
 * 
 **/
public class TeacherDao {

    /**
     * 连接数据库,通过c3p0,获得c3p0连接池
     * 配置信息见 : c3p0-config.xml
     */
    private QueryRunner queryRunner = new TxQueryRunner();

    /**
     * 将Teacher实体添加到数据库中
     * 带参数的sql语句,? 为占位符,按顺序对应数组params的内容
     * @see Teacher
     */
    public void add(Teacher teacher){
        try {
            String sql="insert into TEACHER values(?,?,?,?,?,?)";
            Object[] params={teacher.getTno(),teacher.getTname(),teacher.getTsex(),teacher.getTbirthday(),teacher.getTdepart(),teacher.getTtitle()};
            queryRunner.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException("添加老师失败",e);
        }
    }

    /**
     * 删除teacher表的一条数据,通过参数tno(主码)找到
     * @param tno
     */
    public void delete(String tno){
        try {
            String sql="delete from TEACHER where tno=?";
            queryRunner.update(sql,tno);
        }catch (Exception e){
            throw new RuntimeException("删除老师失败",e);
        }
    }

    /**
     *通过主码tno找到特定的一条数据,转为TEACHER的实体类,作为返回值
     * @param tno
     */
    public Teacher find(String tno){
        try {
            String sql="select * from TEACHER where tno=?";
            return queryRunner.query(sql,new BeanHandler<Teacher>(Teacher.class),tno);
        }catch (Exception e){
            throw new RuntimeException("预编辑失败",e);
        }
    }

    /**
     * 与添加数据类似,通过teacher类的tno找到此条数据,各个属性修改为teacher类中的值
     * @param teacher
     */
    public void edit(Teacher teacher){
        try{
            String sql="update TEACHER set tname=?,tsex=?,tbirthday=?,tdepart=?,ttitle=? where tno=?";
            Object[] params={teacher.getTname(),teacher.getTsex(),teacher.getTbirthday(),teacher.getTdepart(),teacher.getTtitle(),teacher.getTno()};
            queryRunner.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException("编辑失败",e);
        }
    }

    /**
     * 查询TEACHER表中的全部数据,返回teacher类数组
     * @return
     */
    public List<Teacher> findAll(){
        try{
            String sql="select * from TEACHER";
            return queryRunner.query(sql,new BeanListHandler<Teacher>(Teacher.class));
        }catch (Exception e){
            throw new RuntimeException("查找列表失败",e);
        }
    }

    /**
     * 按条件查询TEACHER表中的数据,使用StringBuilder,进行SQL语句的拼接
     * @param teacher
     * @return
     */
    public List<Teacher> query(Teacher teacher){
        try{
            StringBuilder sql=new StringBuilder();
            sql.append("select * from TEACHER where 1=1");
            List<Object> params =new ArrayList<>();

            String tno=teacher.getTno();
            if(tno!=null&&!tno.trim().isEmpty()){
                sql.append(" and tno like ?");
                params.add("%"+tno+"%");
            }

            String tname=teacher.getTname();
            if(tname!=null&&!tname.trim().isEmpty()){
                sql.append(" and tname like ?");
                params.add("%"+tname+"%");
            }

            String tsex=teacher.getTsex();
            if(tsex!=null&&!tsex.trim().isEmpty()){
                sql.append(" and tsex = ?");
                params.add(tsex);
            }

            Date tbirthday=teacher.getTbirthday();
            if(tbirthday!=null){
                sql.append(" and tbirthday like ?");
                params.add("%"+tbirthday+"%");
            }

            String tdepart=teacher.getTdepart();
            if(tdepart!=null&&!tdepart.trim().isEmpty()){
                sql.append(" and tdepart like ?");
                params.add("%"+tdepart+"%");
            }

            String ttitle=teacher.getTtitle();
            if(ttitle!=null&&ttitle.trim().isEmpty()){
                sql.append(" and tdepart like ?");
                params.add("%"+ttitle+"%");
            }

            return queryRunner.query(sql.toString(),new BeanListHandler<Teacher>(Teacher.class),params.toArray());
        }catch (Exception e){
            throw new RuntimeException("单表查询失败",e);
        }
    }
}

