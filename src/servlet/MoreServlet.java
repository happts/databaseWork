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
 * Create by PstereoM on 2018/4/24 20:14
 **/

@WebServlet("/MoreServlet")
public class MoreServlet extends BaseServlet{
    MoreTableQuery moreTableQuery=new MoreTableQuery();


    public String query2(HttpServletRequest request, HttpServletResponse response)throws SQLException{
        Map params=transToMAP(request.getParameterMap());//request的getParamerterMap方法转化为只读的Map，无法操作

        List<LinkedHashMap<String,String>> list= moreTableQuery.query2(params);
        request.setAttribute("list",list);

        return "/MoreQuery_list.jsp";
    }

    public String queryHome(HttpServletRequest request,HttpServletResponse response)throws SQLException{
        LinkedHashMap params=transToMAP(request.getParameterMap());
        Integer index=Integer.valueOf(request.getParameter("index"));

        domainHome domainhome=new domainHome();
        domainhome.addData();
        List<LinkedHashMap<String,String>> list= moreTableQuery.queryhome(domainhome.getSql1(index),params);
        request.setAttribute("list",list);

        return "/MoreQuery_list.jsp";

    }

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
