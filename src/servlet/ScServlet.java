package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import entity.Sc;
import domain.formSc;
import service.ScService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Create by PstereoM on 2018/4/22
 **/

@WebServlet("/ScServlet")

public class ScServlet extends BaseServlet {
    private ScService scService=new ScService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        formSc formSc=CommonUtils.toBean(request.getParameterMap(), domain.formSc.class);
        if(!formSc.validata()){
            request.setAttribute("formsc",formSc);

            return "/add.jsp";
        }

        Sc sc=CommonUtils.toBean(request.getParameterMap(),Sc.class);
        if(request.getParameter("grade").equals(""))sc.setGrade(null);

        scService.add(sc);
        request.setAttribute("msg","添加成绩表成功");

        return "/msg.jsp";
    }

    public String delete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String sno=request.getParameter("sno");
        String cno=request.getParameter("cno");

        scService.delete(sno,cno);
        request.setAttribute("msg","删除成绩表成功");

        return "/msg.jsp";
    }

    public String preEdit(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String sno=request.getParameter("sno");
        String cno=request.getParameter("cno");
        Sc sc=scService.find(sno,cno);
        request.setAttribute("sc",sc);

        return "/sc_edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Sc sc=new Sc();
        sc=CommonUtils.toBean(request.getParameterMap(),Sc.class);

        if(!request.getParameter("grade").trim().isEmpty()){
            sc.setGrade(Integer.valueOf(request.getParameter("grade")));
        }else sc.setGrade(null);

        scService.edit(sc);

        request.setAttribute("msg","编辑成绩表成功");

        return "/msg.jsp";
    }

    public String findAll(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        List<Sc> scs=scService.findAll();

        request.setAttribute("scList",scs);

        return "/sc_list.jsp";
    }

    public String query(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        Sc sc=new Sc();
        sc=CommonUtils.toBean(request.getParameterMap(),Sc.class);

        if(request.getParameter("grade")!=""){
            sc.setGrade(Integer.valueOf(request.getParameter("grade")));
        }else sc.setGrade(null);


        List<Sc> scs=scService.query(sc);
        request.setAttribute("scList",scs);
        return "/sc_list.jsp";
    }

}
