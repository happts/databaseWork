package domain;

import dao.MoreTableQuery;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Create by PstereoM on 2018/5/1
 **/
public class formCo {
    private String cno;
    private String cname;

    private HashMap<String,String> error=new HashMap<>();

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

    public boolean validata() {
        boolean result = true;

        if (this.cno == null || this.cno.trim().isEmpty()) {
            error.put("cno", "课程号不能为空");
            result = false;
        } else {
            try {
                MoreTableQuery moreTableQuery=new MoreTableQuery();
                List<LinkedHashMap<String, Object>> list =moreTableQuery.findField("cno");
                for (int i = 0; i < list.size(); i++) {
                    String data = list.get(i).get("cno").toString();
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

//        if (this.tno == null || this.tno.trim().isEmpty()) {
//            error.put("tno", "编号不能为空");
//            result = false;
//        } else {
//            try {
//                int n=0;
//                MoreTableQuery moreTableQuery=new MoreTableQuery();
//                List<LinkedHashMap<String, Object>> list =moreTableQuery.findField("tno");
//                for (int i = 0; i < list.size(); i++) {
//                    String data = list.get(i).get("tno").toString();
//                    if (this.tno.equals(data)) n++;
//                }
//                if (n==0){
//                    error.put("tno", "此教师不存在");
//                    result=false;
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException("调取tno数据失败", e);
//            }
//        }

        return result;
    }
}
