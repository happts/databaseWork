
//张仲昊

package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import entity.Teach;
import domain.formTeach;
import service.TeachService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Create by PstereoM on 2018/5/6
 **/
@WebServlet("/TeachServlet")
public class TeachServlet extends BaseServlet{
    private TeachService teachService=new TeachService();//建立teach表

    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        formTeach formteach= CommonUtils.toBean(request.getParameterMap(), domain.formTeach.class);
        if (!formteach.validata()){//违反完整性约束条件，不添加
            request.setAttribute("formteach",formteach);
            return "/add.jsp";
        }
        //反之，设置主码并添加
        Teach teach=CommonUtils.toBean(request.getParameterMap(), Teach.class);
        teach.setId(CommonUtils.uuid());

        teachService.add(teach);
        request.setAttribute("msg","添加TEACH表成功");

        return "/msg.jsp";
    }

    public String delete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String id=request.getParameter("id");//获取teach表主码
//        Delete_Check delete_check=new Delete_Check();
//        if(!delete_check.validata2("cno",cno)){
//            request.setAttribute("msg2","课程号:"+cno+"  为成绩表外键，拒绝删除");
//
//            return "/msg.jsp";
//        }

        teachService.delete(id);//输入删除的主码
        request.setAttribute("msg","删除TEACH成功");

        return "/msg.jsp";
    }


    public String findAll(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        List<Teach> teaches=teachService.findAll();

        request.setAttribute("list",teaches);

        return "/ex_list.jsp";//保存并返回输出
    }

    public String query
            (HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        Teach teach=CommonUtils.toBean(request.getParameterMap(),Teach.class);
        List<Teach> teaches=teachService.query(teach);//执行查询
        request.setAttribute("list",teaches);
        return "/ex_list.jsp";//保存并返回输出
    }
}
