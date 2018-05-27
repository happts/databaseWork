package servlet;

import cn.itcast.servlet.BaseServlet;
import dao.MoreTableQuery;
import domain.domainHome;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.*;

/**
 * 苗建伟 1030616335
 * 多表查询
 * 满足大作业一所有要求
 */

@WebServlet("/MoreServlet")
public class MoreServlet extends BaseServlet{
    private MoreTableQuery moreTableQuery=new MoreTableQuery();//创建与数据库进行操作的对象（此类在dao层）

    /**
     * 多表查询的方法
     * @param request 获得请求中的数据
     * @return 将查询结果转发到前端jsp页面
     */
    public String query2(HttpServletRequest request, HttpServletResponse response)throws SQLException{
        Map params=transToMAP(request.getParameterMap());//request的getParamerterMap方法转化为可以操作的Map

        List<LinkedHashMap<String,String>> list= moreTableQuery.query2(params);//多表查询，可多选多表中多字段，返回查询结果
        request.setAttribute("list",list);//创建属性域，将结果添加到属性域中，用于前端展示数据

        return "/MoreQuery_list.jsp";
    }

    /**
     * 满足大作业一的要求
     * @param request 获得请求中的数据
     * @return 将查询结果转发到前端jsp页面
     */
    public String queryHome(HttpServletRequest request,HttpServletResponse response)throws SQLException{
        LinkedHashMap params=transToMAP(request.getParameterMap());//request的getParamerterMap方法转化为可以操作的Map
        Integer index=Integer.valueOf(request.getParameter("index"));//获得用户具体查询的索引

        domainHome domainhome=new domainHome();//获得大作业一所有要求的对象
        domainhome.addData();//添加大作业一所有sql
        List<LinkedHashMap<String,String>> list= moreTableQuery.queryhome(domainhome.getSql1(index),params);//对用户的具体要求进行查询并返回查询结果
        request.setAttribute("list",list);//创建属性域，将结果添加到属性域中，用于前端展示数据

        return "/MoreQuery_list.jsp";

    }

    /**
     * 将请求中只读的map转换为可操作的map
     * @param parameterMap 请求中的数据（map形式操作）
     * @return 可操作的有序的map
     */
    private LinkedHashMap transToMAP(Map parameterMap){
        // 返回值Map
        LinkedHashMap returnMap = new LinkedHashMap();
        Iterator entries = parameterMap.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return  returnMap;
    }

}
