package dao;
/**作者：物联1603 齐鹏
 *  数据库访问层
* 是对数据库操作的封装
* 包括对score表的增删改查等基本操作
* */
import cn.itcast.jdbc.TxQueryRunner;
import entity.Sc;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有对score表的操作的封装
 */
public class ScDao {
    private QueryRunner queryRunner=new TxQueryRunner();  //创建执行数据库操作的类queryRunner
    /**
     * 增加一个score元组
     * @param sc
     */
    public void add(Sc sc){
        try {
            String sql="insert into SCORE value(?,?,?)"; //声明包含占位符的SQL语句字符串
            Object[] params={sc.getSno(),sc.getCno(),sc.getGrade()}; //使params对象获取score表各属性值

            queryRunner.update(sql,params); //将params中的值填入sql字符串中的占位符，并执行SQL语句
        }catch (Exception e){  //抓取异常
            throw new RuntimeException(e);  //抛出异常
        }
    }

    /**
     * 删除一个score元组
     * @param sno
     * @param cno
     */
    public void delete(String sno,String cno){
        try {
            String sql="delete from SCORE where sno=? and cno=?";  //声明包含占位符的SQL语句字符串
            Object[] params={sno,cno}; //使params对象获取传入的参数的值

            queryRunner.update(sql,params); //将params中的值填入sql字符串中的占位符，并执行SQL语句
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询score表中所有元组
     * @return 一组score表记录
     */
    public List<Sc> findAll(){
        try {
            String sql="select * from SCORE";  //声明SQL语句字符串

            return queryRunner.query(sql, new BeanListHandler<Sc>(Sc.class)); //执行SQL语句，返回一组score型的Javabean
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    /**
     * 查询满足某些限制条件的score表中的值
     * @param sc
     * @return 一组score表记录
     */
    public List<Sc> query(Sc sc){
        try {
            StringBuilder sql=new StringBuilder("select * from SCORE where 1=1 ");//声明SQL语句字符串
            List<Object> params=new ArrayList<>();  //定义一个params的数组列表用于存放占位符参数

            String sno=sc.getSno();  //将score表中的sno字段赋给字符串sno
            if(sno!=null && !sno.trim().isEmpty()){  //当字符串不为空且不为空格时执行
                sql.append(" and sno like ? ");  //将引号中的字符串接到原字符串
                params.add("%"+sno+"%");  //把sno字符串中的值填入占位符
            }

            //以下两段代码思路同上
            String cno=sc.getCno();
            if(cno!=null && !sc.getCno().trim().isEmpty()){
                sql.append(" and cno like ? ");
                params.add("%"+cno+"%");
            }

            Integer grade=sc.getGrade();
            if(grade!=null){
                sql.append(" and grade like ? ");
                params.add("%"+grade+"%");
            }

            return queryRunner.query(sql.toString(),new BeanListHandler<Sc>(Sc.class),params.toArray());
            //以Javabean列表的形式返回SQL语句查询出的所有元组
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询某同学选修的某门课的信息
     * @param sno
     * @param cno
     * @return 该同学该门课的信息
     */
    public Sc find(String sno,String cno){
        try {
            String sql="select * from SCORE where sno=? and cno=?"; //声明SQL语句

            Object[] params={sno,cno}; //使params对象获取形参值

            return queryRunner.query(sql,new BeanHandler<Sc>(Sc.class),params);
            //以Javabean的形式返回SQL语句查询出的元组
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改某位同学某门课程的成绩
     * @param sc
     */
    public void edit(Sc sc){
        try {
            String sql="update SCORE set grade=? where sno=? and cno=? "; //声明SQL语句
            Object[] params={sc.getGrade(),sc.getSno(),sc.getCno()};  //使params对象获取score表各属性值

            queryRunner.update(sql,params); //将params中的值填入sql字符串中的占位符，并执行SQL语句
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
