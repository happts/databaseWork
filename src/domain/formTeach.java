package domain;

import dao.MoreTableQuery;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Create by PstereoM on 2018/5/6
 **/
public class formTeach {
    private String cno;
    private String tno;
    private String id;
    private HashMap<String,String> error=new HashMap<>();

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCno() {
        return cno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTno() {
        return tno;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }

    public HashMap<String, String> getError() {
        return error;
    }

    public Boolean validata(){
        boolean result=true;

        if (this.tno == null || this.tno.trim().isEmpty()) {
            error.put("tno", "编号不能为空");
            result = false;
        } else {
            try {
                int n=0;
                MoreTableQuery moreTableQuery=new MoreTableQuery();
                List<LinkedHashMap<String, Object>> list =moreTableQuery.findField("tno");
                for (int i = 0; i < list.size(); i++) {
                    String data = list.get(i).get("tno").toString();
                    if (this.tno.equals(data)) n++;
                }
                if (n==0){
                    error.put("tno", "此教师不存在");
                    result=false;
                }
            } catch (Exception e) {
                throw new RuntimeException("调取tno数据失败", e);
            }
        }

        if (this.cno == null || this.cno.trim().isEmpty()) {
            error.put("cno", "课程号不能为空");
            result = false;
        } else {
            try {
                int n=0;
                MoreTableQuery moreTableQuery=new MoreTableQuery();
                List<LinkedHashMap<String, Object>> list =moreTableQuery.findField("cno");
                for (int i = 0; i < list.size(); i++) {
                    String data = list.get(i).get("cno").toString();
                    if (this.cno.equals(data))n++;
                }
                if (n==0){
                    error.put("cno","课程号不存在");
                    result=false;
                }
            } catch (Exception e) {
                throw new RuntimeException("调取cno数据失败", e);
            }
        }

        if (this.cno!=null&&!this.cno.trim().isEmpty()&&this.tno!=null&&!this.tno.trim().isEmpty()){
            try {
                MoreTableQuery moreTableQuery=new MoreTableQuery();
                List<LinkedHashMap<String,Object>> list=moreTableQuery.findField2("tno");
                for(int i=0;i<list.size();i++){
                    String data_tno=list.get(i).get("tno").toString();
                    String data_cno=list.get(i).get("cno").toString();
                    if(this.tno.equals(data_tno)&&this.cno.equals(data_cno)){
                        error.put("tno_cno","该教师教授该门课已记录");
                        result=false;
                    }
                }
            }catch (Exception e){
                throw new RuntimeException("sc双主码查询失败",e);
            }
        }

        return result;
    }
}
