package domain;

import dao.MoreTableQuery;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * TEACHER的表单操作
 * @author 黄涛
 *
 * formTeacher类中各属性的值来自网页的表单内容
 * 用来检查表单填写数据的合法性
 **/
public class formTeacher {
    private String tno;
    private String tname;
    private String tsex;
    private String tbirthday=null;
    private String tdepart;
    private String ttitle;

    private HashMap<String,String> error=new HashMap<>();

    public void setTno(String tno){
        this.tno=tno;
    }

    public String getTno(){
        return tno;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTname() {
        return tname;
    }

    public void setTbirthday(String tbirthday) {
        this.tbirthday = tbirthday;
    }

    public String getTbirthday() {
        return tbirthday;
    }

    public void setTdepart(String tdepart) {
        this.tdepart = tdepart;
    }

    public String getTdepart() {
        return tdepart;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }

    public String getTsex() {
        return tsex;
    }

    public void setTtitle(String ttitle) {
        this.ttitle = ttitle;
    }

    public String getTtitle() {
        return ttitle;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }

    public HashMap<String,String> getError(){
        return this.error;
    }

    /**
     * TEACHER表添加操作时,
     * validata用来检查表单合法性
     * @return
     */
    public boolean validata(){
        boolean result=true;

        if (this.tno==null||this.tno.trim().isEmpty()){
            error.put("tno","编号不能为空");
            result=false;
        }else {
            try {
                MoreTableQuery moreTableQuery=new MoreTableQuery();
                List<LinkedHashMap<String,Object>> list=moreTableQuery.findField("tno");
                for (int i=0;i<list.size();i++){
                    String data=list.get(i).get("tno").toString();
                    if (this.tno.equals(data)){
                        error.put("tno","此编号已存在");
                        result=false;
                    }
                }
            }catch (SQLException e){
                throw new RuntimeException("调取tno数据失败",e);
            }
        }

        if (this.tname==null||this.tname.trim().isEmpty()){
            error.put("tname","姓名不能为空");
            result=false;
        }

        if (this.tsex==null){
            error.put("tsex","性别不能为空");
            result=false;
        }

        if (this.tdepart==null||this.tdepart.trim().isEmpty()){
            error.put("tdepart","部门不能为空");
            result=false;
        }

        if (this.tbirthday==null){
            error.put("tbirthday","生日不能为空");
            result=false;
        }else {
            try {
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                format.setLenient(false);
                format.parse(this.tbirthday);
            } catch (Exception e) {

                error.put("tbirthday", "日期格式yyyy-MM-dd");
                result=false;
            }
        }

        if (this.ttitle==null||this.ttitle.trim().isEmpty()){
            error.put("ttitle","职称不能为零");
            result=false;
        }

        return result;
    }
}
