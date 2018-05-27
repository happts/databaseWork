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
 * 苗建伟 1030616335
 * 此类与数据库直接进行较复杂交互
 */

public class MoreTableQuery {

    /**
     * 多表查询，可多选多表中多字段
     * @param params 用户选择的字段及所要查询的内容
     * @return 返回List集合类型的查询结果
     */
    public  List<LinkedHashMap<String, Object>> query2(Map<String,Object> params) throws SQLException,NullPointerException{
        Connection conn = null;
        try {
            conn =getConnection();//获得一个数据库连接
            StringBuilder sql =new StringBuilder();//创建一个可拼接的String类型sql查询语句
            sql.append("select * from STUDENT,SCORE,COURSE,TEACHER,TEACH where STUDENT.sno=SCORE.sno and SCORE.cno=COURSE.cno and TEACH.tno=TEACHER.tno ");
            //根据参数提供的字段名拼接sql查询语句
            if(!params.get("sno").equals("")){
                sql.append(" and STUDENT.sno="+params.get("sno"));
            }

            if(!params.get("cno").equals("")){
                sql.append(" and SC.cno="+params.get("cno"));
            }

            if(!params.get("tno").equals("")){
                sql.append( "and TEACH.tno="+params.get("tno"));
            }
            //将参数中字段名的值拼接到sql查询语句中
            for (Map.Entry<String,Object> param:params.entrySet()
                    ) {
                if (!(param.getKey().contains("sno"))&&!(param.getKey().contains("cno"))&&!(param.getKey().contains("tno"))&&!(param.getKey().contains("method"))&&!param.getValue().equals("")){
                    sql.append(" and " +param.getKey()+"="+"'"+param.getValue()+"'");
                }

            }
            //通过数据库连接对象创建数据库操作对象
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ResultSet rs;//数据库结果集为resultset
            rs = ps.executeQuery();//数据库操作对象调用executeQuery()方法，将sql查询语句传递到数据库，并返回查询结果
            List<LinkedHashMap<String, Object>> list = convertList(rs);//将查询结果转换为List集合
            return list;
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException("多表查询失败", e1);
        } finally {
            Close(conn);
        }
    }

    /**
     * 用于：添加数据时验证当前表主码是否已存在
     * @param field 当前表主码的字段名
     * @return 返回当前表中所有主码信息
     */
    public List<LinkedHashMap<String, Object>> findField (String field) throws SQLException, NullPointerException {
        Connection conn = null;
        try {
            conn=getConnection();//获得数据库连接对象
            StringBuilder sql = new StringBuilder();
            //拼接有当前表主码为字段名的sql语句
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

            PreparedStatement ps = conn.prepareStatement(sql.toString());//通过数据库连接对象获得数据库操作对象
            ResultSet rs;
            rs = ps.executeQuery();//数据库操作对象将sql传递给数据库，并执行sql语句，返回查询结果
            List<LinkedHashMap<String, Object>> list = convertList(rs);//将查询结果转换为List集合
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
     * @param field 当前表主码字段名
     * @return 返回当前表主码为其他表外键时的所有信息
     */
    public List<LinkedHashMap<String, Object>> findField2 (String field) throws SQLException, NullPointerException {
        Connection conn = null;
        try {
            conn=getConnection();//获得数据库连接对象
            StringBuilder sql = new StringBuilder();
            //选择当前表主码为字段名的sql语句（选定的sql语句为查询当前表主码为其他表外键时的所有信息）
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

            PreparedStatement ps = conn.prepareStatement(sql.toString());//通过数据库连接对象获得数据库操作对象
            ResultSet rs;
            rs = ps.executeQuery();//数据库操作对象将sql传递给数据库，并执行sql语句，返回查询结果
            List<LinkedHashMap<String, Object>> list = convertList(rs);//将查询结果转换为List集合
            return list;
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException(field+"删除查询失败", e1);
        } finally {
            Close(conn);
        }
    }


    /**
     * 满足大作业1的要求
     * @param sql 用户选择的具体查询
     * @param params 用户查询的具体内容
     * @return 返回List集合类型的查询结果
     */
    public List<LinkedHashMap<String, Object>> queryhome(String sql,LinkedHashMap<String,String> params) throws SQLException,NullPointerException{
        Connection conn = null;
        int i=1;
        try {
            conn =getConnection();//获得数据库连接对象
            if (params.get("index").equals("6")){//挑选出特例第七个sql查询，它的参数很特殊！是表或列而不是普通的字段所对应的值
                StringBuilder sql1=new StringBuilder();
                //拼接sql语句，判断参数是否为空
                if (!params.get("tables").trim().isEmpty()&&!params.get("fields1").trim().isEmpty()&&!params.get("fields2").trim().isEmpty()){
                    sql1.append(sql+" "+params.get("tables")+" GROUP BY "+params.get("fields1")+" +0 , "+params.get("fields2")+" "+" +0 DESC");
                }else{//判断数据为空的情况,返回一个空表
                    sql1.append("SELECT*FROM STUDENT WHERE SNO=-1");
                }

                PreparedStatement ps=conn.prepareStatement(sql1.toString());//通过数据库连接对象获得数据库操作对象
                ResultSet rs;
                rs = ps.executeQuery();//数据库操作对象将sql传递给数据库，并执行sql语句，返回查询结果
                List<LinkedHashMap<String, Object>> list = convertList(rs);//将查询结果转换为List集合
                return list;
            }else {
                PreparedStatement ps = conn.prepareStatement(sql);//预处理，检查sql语法，编译。
                for (Map.Entry<String,String> param:params.entrySet()//迭代获得每个参数（所查询的sql语句，所查询得内容）
                        ) {
                    if (!param.getKey().equals("index")&&!param.getKey().equals("method")){
                        ps.setString(i++,param.getValue());//将查询的具体内容传递到当前index下的sql语句中
                    }
                }
                ResultSet rs;
                rs = ps.executeQuery();//数据库操作对象将sql传递给数据库，并执行sql语句，返回查询结果
                List<LinkedHashMap<String, Object>> list = convertList(rs);//将查询结果转换为List集合
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
