package domain;

import dao.MoreTableQuery;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by PstereoM on 2018/5/1
 **/
public class Delete_Check {
    public boolean validata2(String field,String value) {

        try {
            MoreTableQuery moreTableQuery = new MoreTableQuery();
            List<LinkedHashMap<String, Object>> list = moreTableQuery.findField2(field);
            for (int i = 0; i < list.size(); i++) {
                String data = list.get(i).get(field).toString();
                if (value.equals(data))return false;
            }
        }catch (SQLException e) {
            throw new RuntimeException("调取sno数据失败", e);
        }

        return true;
    }
}
