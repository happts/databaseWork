package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import entity.Co;
import domain.Delete_Check;
import domain.formCo;
import service.CoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Create by PstereoM on 2018/4/27
 **/
@WebServlet("/CoServlet")
public class CoServlet extends BaseServlet{
    private CoService coService=new CoService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        formCo formCo=CommonUtils.toBean(request.getParameterMap(), domain.formCo.class);
        if (!formCo.validata()){
            request.setAttribute("formc",formCo);
            return "/add.jsp";
        }

        Co co=CommonUtils.toBean(request.getParameterMap(), Co.class);

        coService.add(co);
        request.setAttribute("msg","添加成绩表成功");

        return "/msg.jsp";
    }

    public String delete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String cno=request.getParameter("cno");
        Delete_Check delete_check=new Delete_Check();
        if(!delete_check.validata2("cno",cno)){
            request.setAttribute("msg2","课程号:"+cno+"  为成绩表与TEACH表外键，拒绝删除");

            return "/msg.jsp";
        }

        coService.delete(cno);
        request.setAttribute("msg","删除课程表成功");

        return "/msg.jsp";
    }

    public String preEdit(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String cno=request.getParameter("cno");
        Co co=coService.find(cno);
        request.setAttribute("co",co);

        return "/co_edit.jsp";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Co co=CommonUtils.toBean(request.getParameterMap(),Co.class);


        coService.edit(co);

        request.setAttribute("msg","编辑成绩表成功");

        return "/msg.jsp";
    }

    public String findAll(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        List<Co> cos=coService.findAll();

        request.setAttribute("coList",cos);

        return "/co_list.jsp";
    }

    public String query(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        Co co=CommonUtils.toBean(request.getParameterMap(),Co.class);


        List<Co> cos=coService.query(co);
        request.setAttribute("coList",cos);
        return "/co_list.jsp";
    }
}
