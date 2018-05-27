package domain;

import dao.MoreTableQuery;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 苗建伟 1030616335
 * 此类用于验证添加course表数据时，参数是否合法
 **/
public class formCo {
    private String cno;
    private String cname;

    private HashMap<String,String> error=new HashMap<>();//存储错误信息

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCno() {
        return cno;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCname() {
        return cname;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }

    public HashMap<String, String> getError() {
        return error;
    }

    /**
     * 验证数据合法性
     * @return 若合法返回true，否则返回false
     */
    public boolean validata() {
        boolean result = true;

        if (this.cno == null || this.cno.trim().isEmpty()) {
            error.put("cno", "课程号不能为空");
            result = false;
        } else {
            try {
                MoreTableQuery moreTableQuery=new MoreTableQuery();//创建与数据库进行操作的对象（此类在dao层）
                List<LinkedHashMap<String, Object>> list =moreTableQuery.findField("cno");//返回当前表中所有主码信息
                for (int i = 0; i < list.size(); i++) {
                    String data = list.get(i).get("cno").toString();
                    //如果数据库主码cno此值已存在，则不能添加
                    if (this.cno.equals(data)) {
                        error.put("cno", "此课程号已存在");
                        result = false;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("调取cno数据失败", e);
            }
        }

        if (this.cname == null || this.cname.trim().isEmpty()) {
            error.put("cname", "课程名不能为空");
            result = false;
        }

        return result;
    }
}
