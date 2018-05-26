package dao;

import cn.itcast.jdbc.TxQueryRunner;
import entity.Sc;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.List;

public class ScDao {
    private QueryRunner queryRunner=new TxQueryRunner();

    public void add(Sc sc){
        try {
            String sql="insert into SCORE value(?,?,?)";
            Object[] params={sc.getSno(),sc.getCno(),sc.getGrade()};

            queryRunner.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void delete(String sno,String cno){
        try {
            String sql="delete from SCORE where sno=? and cno=?";
            Object[] params={sno,cno};

            queryRunner.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Sc> findAll(){
        try {
            String sql="select * from SCORE";

            return queryRunner.query(sql, new BeanListHandler<Sc>(Sc.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<Sc> query(Sc sc){
        try {
            StringBuilder sql=new StringBuilder("select * from SCORE where 1=1 ");
            List<Object> params=new ArrayList<>();

            String sno=sc.getSno();
            if(sno!=null && !sno.trim().isEmpty()){
                sql.append(" and sno like ? ");
                params.add("%"+sno+"%");
            }


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
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Sc find(String sno,String cno){
        try {
            String sql="select * from SCORE where sno=? and cno=?";

            Object[] params={sno,cno};

            return queryRunner.query(sql,new BeanHandler<Sc>(Sc.class),params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void edit(Sc sc){
        try {
            String sql="update SCORE set grade=? where sno=? and cno=? ";
            Object[] params={sc.getGrade(),sc.getSno(),sc.getCno()};

            queryRunner.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
