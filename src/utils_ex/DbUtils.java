package utils_ex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
//import com.base.annotations.Javabean;
/**
 * Create by PstereoM on 2018/4/23 15:48
 **/
public class DbUtils {

    private Connection connection = null;
    private QueryRunner queryRunner = null;

    public DbUtils(Connection connection) {

        this.connection = connection;
        queryRunner = new QueryRunner();
    }

    /**
     * 将查询的一条数据转换成Array数组对象，如果查询语句返回的是多条Result对象，则返回结果集的第一条记录
     */
    public Object[] queryByArray(String sql, Object... params)
            throws SQLException {

        return query(sql, new ArrayHandler(), params);
    }

    /**
     * 将查询的一条数据转换成Map对象，如果查询语句返回的是多条Result对象，则返回结果集的第一条记录
     */
    public Map<String, Object> queryByMap(String sql, Object... params)
            throws SQLException {

        return query(sql, new MapHandler(), params);
    }

    /**
     * 将查询出来的N条结果集返回为List&lt;Map&gt;&lt;String,Object&gt;&gt;
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> queryAllByMap(String sql, Object... params)
            throws SQLException {

        return query(sql, new MapListHandler(), params);
    }

    /**
     * 将查询的一条记录转换成javabean对象，如果查询语句返回的是多条Result对象，则返回结果集的第一条记录
     */
    public <T> T queryByJavabean(String sql, Class<T> javabean,
                                 Object... params) throws SQLException {

        return query(sql, new BeanHandler<T>(javabean), params);
    }

    /**
     * 将查询的记录数转换成javabean对象
     */
    public <T> List<T> querAllByJavabean(String sql, Class<T> javabean,
                                         Object... params) throws SQLException {

        return query(sql, new BeanListHandler<T>(javabean), params);
    }

    /**
     * 将查询多表返回的map对象，转换为一个javabean
     */
    public <T> T queryManyByJavabean(String sql, Class<T> javabean,
                                     Object... params) throws SQLException {

        return queryMap2Javabean(queryByMap(sql, params), javabean);
    }

    /**
     * 将查询的数据列表转换为List&lt;Javabean&gt;
     *
     * @param <T>
     * @param sql
     * @param javabean
     * @param params
     * @return
     * @throws SQLException
     */
    public <T> List<T> queryAllManyByJavabean(String sql, Class<T> javabean,
                                              Object... params) throws SQLException {

        List<Map<String, Object>> mapList = queryAllByMap(sql, params);
        List<T> list = null;
        if (mapList != null && mapList.isEmpty() == false) {
            list = new ArrayList<T>();
            for (Map<String, Object> map : mapList) {
                T t = queryMap2Javabean(map, javabean);
                list.add(t);
            }
        }
        return list;
    }

    /**
     * 将map集合中数据与javabean中属性绑定
     *
     * @param <T>
     * @param map
     * @param javabean
     * @return
     */
    private <T> T queryMap2Javabean(Map<String, Object> map, Class<T> javabean) {

        if (map != null && map.isEmpty() == false) {
            try {
                T newObject = javabean.newInstance();
                Field fields[] = javabean.getDeclaredFields();
                for (Field field : fields) {
                    if (field.getAnnotation(JavaBean.class) != null) {
                        // 验证该字段是否为Javabean类型
                        Object newSon = field.getType().newInstance();
                        field.setAccessible(true);
                        field.set(newObject, newSon);
                        BeanUtils.populate(newSon, map);
                    }
                }
                BeanUtils.populate(newObject, map);
                return newObject;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 对DbUtils组件查询核心方法封装
     */
    private <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
            throws SQLException {

        return queryRunner.query(connection, sql, rsh, params);
    }

}
