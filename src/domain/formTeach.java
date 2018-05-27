//张仲昊

package domain;

import dao.MoreTableQuery;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Create by PstereoM on 2018/5/6
                                    张仲昊
 **/
public class formTeach {//添加课程信息
    private String cno;//课程号
    private String tno;//教师编号
    private String id; //主码
    private HashMap<String,String> error=new HashMap<>();//定义错误

    public void setId(String id) {
        this.id = id;
    }//设置主码编号

    public String getId() {
        return id;
    }//返回编号

    public void setCno(String cno) {
        this.cno = cno;
    }//设置课程号


    public String getCno() {
        return cno;
    }//返回课程号

    public void setTno(String tno) {
        this.tno = tno;
    }//设置教师编号

    public String getTno() {
        return tno;
    }//返回教师编号

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }//设置错误

    public HashMap<String, String> getError() {
        return error;
    }//返回错误

    public Boolean validata(){
        //完整性检查
        boolean result=true;

        if (this.tno == null || this.tno.trim().isEmpty()) {//教师编号为空
            error.put("tno", "编号不能为空");
            result = false;
        } else {
            try {
                int n=0;//标识符：0，表示查询失败；1，表示成功
                MoreTableQuery moreTableQuery=new MoreTableQuery();//多表连接查询
                List<LinkedHashMap<String, Object>> list =moreTableQuery.findField("tno");
                for (int i = 0; i < list.size(); i++) {//依次查询是否有相同的教师编号
                    String data = list.get(i).get("tno").toString();
                    if (this.tno.equals(data)) n++;//查询成功
                }
                if (n==0){
                    error.put("tno", "此教师不存在");//查询失败
                    result=false;
                }
            } catch (Exception e) {
                throw new RuntimeException("调取tno数据失败", e);
            }
        }

        if (this.cno == null || this.cno.trim().isEmpty()) {//课程编号为空
            error.put("cno", "课程号不能为空");
            result = false;
        } else {
            try {
                int n=0;//标识符，同上
                MoreTableQuery moreTableQuery=new MoreTableQuery();
                List<LinkedHashMap<String, Object>> list =moreTableQuery.findField("cno");

                for (int i = 0; i < list.size(); i++) {
                    String data = list.get(i).get("cno").toString();
                    if (this.cno.equals(data))n++;//查询成功
                }
                if (n==0){                        //失败
                    error.put("cno","课程号不存在");
                    result=false;
                }
            } catch (Exception e) {
                throw new RuntimeException("调取cno数据失败", e);
            }
        }

        if (this.cno!=null&&!this.cno.trim().isEmpty()&&this.tno!=null&&!this.tno.trim().isEmpty()){
        //课程，教师编号均不为空
            try {
                MoreTableQuery moreTableQuery=new MoreTableQuery();
                List<LinkedHashMap<String,Object>> list=moreTableQuery.findField2("tno");

                for(int i=0;i<list.size();i++){
                    String data_tno=list.get(i).get("tno").toString();
                    String data_cno=list.get(i).get("cno").toString();
                    //依次找出教师教授课程情况
                    if(this.tno.equals(data_tno)&&this.cno.equals(data_cno)){//比较
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
