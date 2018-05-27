package domain;

import dao.MoreTableQuery;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 苗建伟 1030616335
 * 此类用于验证添加score表数据时，参数是否合法
 **/
public class formSc {
    private String sno;
    private String cno;
    private Integer grade=null;
    private HashMap<String,String> error=new HashMap<>();//存储错误信息

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSno() {
        return sno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCno() {
        return cno;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
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
    public Boolean validata(){
        boolean result=true;

        if (this.sno==null||this.sno.trim().isEmpty()){
            error.put("sno","学号不能为空");
            result=false;
        }else {
            try {
                int n=0;
                MoreTableQuery moreTableQuery=new MoreTableQuery();//创建与数据库进行操作的对象（此类在dao层）
                List<LinkedHashMap<String,Object>> list=moreTableQuery.findField("sno");//返回当前表中所有主码信息
                for (int i=0;i<list.size();i++){
                    String data=list.get(i).get("sno").toString();
                    //如果数据库中score表的sno没有此值，不满足约束条件，不能添加此数据
                    if (this.sno.equals(data)) n++;
                }
                if (n==0){
                    error.put("sno","此学号不存在");
                    result=false;
                }
            }catch (SQLException e){
                throw new RuntimeException("调取sno数据失败",e);
            }
        }

        if (this.cno == null || this.cno.trim().isEmpty()) {
            error.put("cno", "课程号不能为空");
            result = false;
        } else {
            try {
                int n=0;
                MoreTableQuery moreTableQuery=new MoreTableQuery();//创建与数据库进行操作的对象（此类在dao层）
                List<LinkedHashMap<String, Object>> list =moreTableQuery.findField("cno");//返回当前表中所有主码信息
                for (int i = 0; i < list.size(); i++) {
                    String data = list.get(i).get("cno").toString();
                    //如果数据库中score表的cno没有此值，不满足约束条件，不能添加此数据
                    if (this.cno.equals(data)) n++;
                }
                if (n==0){
                    error.put("cno", "此课程号不存在");
                    result = false;
                }
            } catch (SQLException e) {
                throw new RuntimeException("调取cno数据失败", e);
            }
        }

        if (this.cno!=null&&!this.cno.trim().isEmpty()&&this.sno!=null&&!this.sno.trim().isEmpty()){
            try {
                MoreTableQuery moreTableQuery=new MoreTableQuery();//创建与数据库进行操作的对象（此类在dao层）
                List<LinkedHashMap<String,Object>> list=moreTableQuery.findField2("sno");//返回当前表主码为其他表外键时的所有信息
                for(int i=0;i<list.size();i++){
                    String data_sno=list.get(i).get("sno").toString();
                    String data_cno=list.get(i).get("cno").toString();
                    //如果此学生的该门课已记录，sno，cno共同决定score表，不能重复，不能添加
                    if(this.sno.equals(data_sno)&&this.cno.equals(data_cno)){
                        error.put("sno_cno","此学生的该门课已记录");
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
