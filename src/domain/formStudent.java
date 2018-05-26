package domain;

import dao.MoreTableQuery;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by PstereoM on 2018/4/30
 **/
public class formStudent {
    private String sno = null;
    private String sname = null;
    private String ssex = null;
    private String sclass = null;
    private String sbirthday = null;

    private HashMap<String, String> error = new HashMap<>();

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSno() {
        return sno;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSname() {
        return sname;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSbirthday(String sbirthday) {
        this.sbirthday = sbirthday;
    }

    public String getSbirthday() {
        return this.sbirthday;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }

    public HashMap<String, String> getError() {
        return this.error;
    }

    public boolean validata() {
        boolean result = true;

        if (this.sno == null || this.sno.trim().isEmpty()) {
            error.put("sno", "学号不能为空");
            result = false;
        } else {
            try {
                MoreTableQuery moreTableQuery=new MoreTableQuery();
                List<LinkedHashMap<String, Object>> list = moreTableQuery.findField("sno");
                for (int i = 0; i < list.size(); i++) {
                    String data = list.get(i).get("sno").toString();
                    if (this.sno.equals(data)) {
                        error.put("sno", "此学号已存在");
                        result = false;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("调取sno数据失败", e);
            }
        }

        if (this.sname == null || this.sname.trim().isEmpty()) {
            error.put("sname", "姓名不能为空");
            result = false;
        }

        if (this.ssex == null) {
            error.put("ssex", "性别不能为空");
            result = false;
        }

        if (this.sclass == null || this.sclass.trim().isEmpty()) {
            error.put("sclass", "班级不能为空");
            result = false;
        }

        if (this.sbirthday == null) {
            error.put("sbirthday", "生日不能为空");
            result = false;
        } else {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                format.setLenient(false);
                format.parse(this.sbirthday);
            } catch (Exception e) {

                error.put("sbirthday", "日期格式yyyy-MM-dd");
                result = false;
            }
        }

        return result;
    }
}
