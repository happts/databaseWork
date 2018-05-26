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
 **/
public class TeachDao {
    private QueryRunner queryRunner=new TxQueryRunner();

    public void add(Teach teach){
        try {
            String sql="insert into TEACH value(?,?,?)";
            Object[] params={teach.getId(),teach.getTno(),teach.getCno()};

            queryRunner.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(String id){
        try {
            String sql="delete from TEACH where id=?";

            queryRunner.update(sql,id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Teach> findAll(){
        try {
            String sql="select * from TEACH";

            return queryRunner.query(sql, new BeanListHandler<Teach>(Teach.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Teach> query(Teach teach){
        try {
            StringBuilder sql=new StringBuilder("select * from TEACH where 1=1 ");
            List<Object> params=new ArrayList<>();

            String tno=teach.getTno();
            if(tno!=null && !tno.trim().isEmpty()){
                sql.append(" and tno like ? ");
                params.add("%"+tno+"%");
            }


            String cno=teach.getCno();
            if(cno!=null && !cno.trim().isEmpty()){
                sql.append(" and cno like ? ");
                params.add("%"+cno+"%");
            }


            return queryRunner.query(sql.toString(),new BeanListHandler<Teach>(Teach.class),params.toArray());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Teach find(String id){
        try {
            String sql="select * from TEACH where id=?";

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
