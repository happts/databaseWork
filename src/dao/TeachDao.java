package dao;

import cn.itcast.jdbc.TxQueryRunner;
import entity.Teach;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by PstereoM on 2018/5/6
  张仲昊
 **/
public class TeachDao {
    private QueryRunner queryRunner=new TxQueryRunner();

    public void add(Teach teach){
        //添加功能
        try {
            String sql="insert into TEACH value(?,?,?)";//插入sql语句模板
            Object[] params={teach.getId(),teach.getTno(),teach.getCno()};
            //读取sql中添加的课程号或教师编号
            queryRunner.update(sql,params);//组合
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(String id){
        //删除
        try {
            String sql="delete from TEACH where id=?";//删除sql语句模板

            queryRunner.update(sql,id);//组合
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Teach> findAll(){
        ///查询teach表全部信息
        try {
            String sql="select * from TEACH";//查询teach表全部信息对应sql语句

            return queryRunner.query(sql, new BeanListHandler<Teach>(Teach.class));//执行查询
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Teach> query(Teach teach){
        //查询
        try {
            StringBuilder sql=new StringBuilder("select * from TEACH where 1=1 ");
            List<Object> params=new ArrayList<>();

            String tno=teach.getTno();
            if(tno!=null && !tno.trim().isEmpty()){//获得不为空的教师编号
                sql.append(" and tno like ? ");//在sql中加入教师编号
                params.add("%"+tno+"%");
            }

            String cno=teach.getCno();
            if(cno!=null && !cno.trim().isEmpty()){//获得不为空的课程编号
                sql.append(" and cno like ? ");    //在sql中加入课程号
                params.add("%"+cno+"%");
            }

            return queryRunner.query(sql.toString(),new BeanListHandler<Teach>(Teach.class),params.toArray());

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Teach find(String id){
        //找到主编码并放入到sql语句中
        try {
            String sql="select * from TEACH where id=?";
            //sql语句模板
            return queryRunner.query(sql,new BeanHandler<Teach>(Teach.class),id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

//    public void edit(Co co){
//        try {
//            String sql="update COURSE set cname=? where cno=? ";
//            Object[] params={co.getCname(),co.getCno()};
//
//            queryRunner.update(sql,params);
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//
//    }
}
