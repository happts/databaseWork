package domain;

import dao.MoreTableQuery;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 苗建伟 1030616335
 * 此类用于验证删除数据时，数据的完整性约束
 **/
public class Delete_Check {
    /**
     * 删除数据时，检查是否满足完整性约束
     * @param field 当前数据的主码
     * @param value 当前数据主码的值
     * @return 若当前主码的值在其他表作为外键的值，返回false不能删除，否则返回true可以删除
     */
    public boolean validata2(String field,String value) {

        try {
            MoreTableQuery moreTableQuery = new MoreTableQuery();//创建与数据库进行操作的对象（此类在dao层）
            List<LinkedHashMap<String, Object>> list = moreTableQuery.findField2(field);//获得当前表主码为其他表外键时的所有信息
            for (int i = 0; i < list.size(); i++) {
                String data = list.get(i).get(field).toString();
                if (value.equals(data))return false;//如果当前主码的值在其他表作为外键的值，不能删除
            }
        }catch (SQLException e) {
            throw new RuntimeException("调取sno数据失败", e);
        }

        return true;
    }
}
