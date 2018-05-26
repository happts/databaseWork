package dao;

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
    private QueryRunner queryRunner=new TxQueryRunner();

    public void add(Co co){
        try {
            String sql="insert into COURSE value(?,?)";
            Object[] params={co.getCno(),co.getCname()};

            queryRunner.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(String cno){
        try {
            String sql="delete from COURSE where cno=?";

            queryRunner.update(sql,cno);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Co> findAll(){
        try {
            String sql="select * from COURSE";

            return queryRunner.query(sql, new BeanListHandler<Co>(Co.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Co> query(Co co){
        try {
            StringBuilder sql=new StringBuilder("select * from COURSE where 1=1 ");
            List<Object> params=new ArrayList<>();

            String cno=co.getCno();
            if(cno!=null && !cno.trim().isEmpty()){
                sql.append(" and cno like ? ");
                params.add("%"+cno+"%");
            }


            String cname=co.getCname();
            if(cname!=null && !cname.trim().isEmpty()){
                sql.append(" and cname like ? ");
                params.add("%"+cname+"%");
            }


            return queryRunner.query(sql.toString(),new BeanListHandler<Co>(Co.class),params.toArray());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Co find(String cno){
        try {
            String sql="select * from COURSE where cno=?";

            return queryRunner.query(sql,new BeanHandler<Co>(Co.class),cno);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void edit(Co co){
        try {
            String sql="update COURSE set cname=? where cno=? ";
            Object[] params={co.getCname(),co.getCno()};

            queryRunner.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
