package dao;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.*;

import static utils_ex.DBManager.Close;
import static utils_ex.DBManager.getConnection;
import static utils_ex.ModelConvert.convertList;

/**
 * Create by PstereoM on 2018/4/24 20:11
 **/
public class MoreTableQuery {
    //简单的多表查询
    public  List<LinkedHashMap<String, Object>> query2(Map<String,Object> params) throws SQLException,NullPointerException{
        Connection conn = null;
        try {
            conn =getConnection();
            StringBuilder sql =new StringBuilder();
            sql.append("select * from STUDENT,SCORE,COURSE,TEACHER,TEACH where STUDENT.sno=SCORE.sno and SCORE.cno=COURSE.cno and TEACH.tno=TEACHER.tno ");
            if(!params.get("sno").equals("")){
                sql.append(" and STUDENT.sno="+params.get("sno"));
            }

            if(!params.get("cno").equals("")){
                sql.append(" and SC.cno="+params.get("cno"));
            }

            if(!params.get("tno").equals("")){
                sql.append( "and TEACH.tno="+params.get("tno"));
            }

            for (Map.Entry<String,Object> param:params.entrySet()
                    ) {
                if (!(param.getKey().contains("sno"))&&!(param.getKey().contains("cno"))&&!(param.getKey().contains("tno"))&&!(param.getKey().contains("method"))&&!param.getValue().equals("")){
                    sql.append(" and " +param.getKey()+"="+"'"+param.getValue()+"'");
                }

            }

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ResultSet rs;
            rs = ps.executeQuery();
            List<LinkedHashMap<String, Object>> list = convertList(rs);
            return list;
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException("多表查询失败", e1);
        } finally {
            Close(conn);
        }
    }

    //添加数据验证其主码是否已存在
    public List<LinkedHashMap<String, Object>> findField (String field) throws SQLException, NullPointerException {
        Connection conn = null;
        try {
            conn=getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select distinct ");

            if (field.equals("sno")){
                sql.append("sno from STUDENT");
            }

            if (field.equals("cno")){
                sql.append("cno from COURSE");
            }

            if (field.equals("tno")){
                sql.append("tno from TEACHER");
            }

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ResultSet rs;
            rs = ps.executeQuery();
            List<LinkedHashMap<String, Object>> list = convertList(rs);
            return list;
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException(field+"添加查询失败", e1);
        } finally {
            Close(conn);
        }
    }

    /**
     * 级联操作中查询其具体主码是否为其他表外键,用于验证删除操作，以及验证重复记录。
     */
    public List<LinkedHashMap<String, Object>> findField2 (String field) throws SQLException, NullPointerException {
        Connection conn = null;
        try {
            conn=getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("select distinct ");

            if (field.equals("sno")){
                sql.append("sno,cno from SCORE");
            }

            if (field.equals("cno")){
                sql.append("SCORE.cno from SCORE,TEACH where SCORE.cno=TEACH.cno");
            }

            if (field.equals("tno")){
                sql.append("tno,cno from TEACH");
            }

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ResultSet rs;
            rs = ps.executeQuery();
            List<LinkedHashMap<String, Object>> list = convertList(rs);
            return list;
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException(field+"删除查询失败", e1);
        } finally {
            Close(conn);
        }
    }

    //大作业1的要求
    public List<LinkedHashMap<String, Object>> queryhome(String sql,LinkedHashMap<String,String> params) throws SQLException,NullPointerException{
        Connection conn = null;
        int i=1;
        try {
            conn =getConnection();
            if (params.get("index").equals("6")){//挑选出特例第七个sql查询，它的参数为表或列
                StringBuilder sql1=new StringBuilder();
                if (!params.get("tables").trim().isEmpty()&&!params.get("fields1").trim().isEmpty()&&!params.get("fields2").trim().isEmpty()){
                    sql1.append(sql+" "+params.get("tables")+" GROUP BY "+params.get("fields1")+" +0 , "+params.get("fields2")+" "+" +0 DESC");
                }else{//判断数据为空的情况,返回一个空表
                    sql1.append("SELECT*FROM STUDENT WHERE SNO=-1");
                }

                PreparedStatement ps=conn.prepareStatement(sql1.toString());
                ResultSet rs;
                rs = ps.executeQuery();
                List<LinkedHashMap<String, Object>> list = convertList(rs);
                return list;
            }else {
                PreparedStatement ps = conn.prepareStatement(sql);//预处理，检查sql语法，编译。
                for (Map.Entry<String,String> param:params.entrySet()//获得每个参数
                        ) {
                    if (!param.getKey().equals("index")&&!param.getKey().equals("method")){
                        ps.setString(i++,param.getValue());
                    }
                }
                ResultSet rs;
                rs = ps.executeQuery();
                List<LinkedHashMap<String, Object>> list = convertList(rs);
                return list;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException("作业查询失败", e1);
        } finally {
            Close(conn);
        }
    }
}
