package dao;
/**
 * 贾天豪
 */
import cn.itcast.jdbc.TxQueryRunner;
import entity.Co;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by PstereoM on 2018/4/26
 **/
public class CoDao {
    private QueryRunner queryRunner=new TxQueryRunner();//QueryRunner类的有参构造方法，接收一个连接池对象

    public void add(Co co){    //增加操作
        try {
            String sql="insert into COURSE value(?,?)";//插入sql语句到course表
            Object[] params={co.getCno(),co.getCname()}; //将course中的cno，cname的值存放到可变参数的数组中

            queryRunner.update(sql,params);//进行更新操作
        }catch (Exception e){   //try块出现sqlexception时，这里捕捉到异常并做响应的处理
            throw new RuntimeException(e); //把异常包在运行时异常中抛出
        }
    }

    public void delete(String cno){    //删除操作
        try {
            String sql="delete from COURSE where cno=?";//从course表中删除sql语句
            queryRunner.update(sql,cno); //进行更新操作
        }catch (Exception e){//try块出现sqlexception时，这里捕捉到异常并做响应的处理
            throw new RuntimeException(e);//把异常包在运行时异常中抛出
        }
    }

    /**
     *
     * @return
     */
    public List<Co> findAll(){  //查询全部信息
        try {
            String sql="select * from COURSE";  //查询course表

            return queryRunner.query(sql, new BeanListHandler<Co>(Co.class));//将在course中所查询到的信息返回
        }catch (Exception e){     //try块出现sqlexception时，这里捕捉到异常并做响应的处理
            throw new RuntimeException(e);  //把异常包在运行时异常中抛出
        }
    }

    public List<Co> query(Co co){   //查询学生课程号
        try {
            StringBuilder sql=new StringBuilder("select * from COURSE where 1=1 ");//查询满足该条件的课程信息
            List<Object> params=new ArrayList<>();//创建一个新的List类型对象

            String cno=co.getCno();    //获取course表中的cno
            if(cno!=null && !cno.trim().isEmpty()){   //如果cno为空值
                sql.append(" and cno like ? "); //将满足条件的信息 拼接在一起
                params.add("%"+cno+"%"); //将参数进行拼接，并存放在数组中
            }


            String cname=co.getCname();//获取course表中的cname
            if(cname!=null && !cname.trim().isEmpty()){//如果cname为空值
                sql.append(" and cname like ? "); //将满足条件的信息拼接在一起
                params.add("%"+cname+"%");  //将参数进行拼接，并存放在数组中
            }


            return queryRunner.query(sql.toString(),new BeanListHandler<Co>(Co.class),params.toArray());
        }catch (Exception e){//try块出现sqlexception时，这里捕捉到异常并做响应的处理
            throw new RuntimeException(e);//把异常包在运行时异常中抛出
        }
    }

    public Co find(String cno){     //查询课程号为？的信息
        try {
            String sql="select * from COURSE where cno=?";

            return queryRunner.query(sql,new BeanHandler<Co>(Co.class),cno);//将所查询到的值返回
        }catch (Exception e){//try块出现sqlexception时，这里捕捉到异常并做响应的处理
            throw new RuntimeException(e);//把异常包在运行时异常中抛出
        }
    }

    public void edit(Co co){  //改正操作
        try {
            String sql="update COURSE set cname=? where cno=? "; //更新满足条件的信息值
            Object[] params={co.getCname(),co.getCno()};//将cname，cno的值存放到数组当中

            queryRunner.update(sql,params);  //进行更新操作
        }catch (Exception e){//try块出现sqlexception时，这里捕捉到异常并做响应的处理
            throw new RuntimeException(e);//把异常包在运行时异常中抛出
        }

    }
}
