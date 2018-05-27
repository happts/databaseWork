package servlet;
/**
 * 贾天豪
 */
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
public class CoServlet extends BaseServlet{//CoServlet是继承自javax.http.baseServlet的一个类
    private CoService coService=new CoService();

    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{//抛出Servlet异常,抛出操作系统异常
        formCo formCo=CommonUtils.toBean(request.getParameterMap(), domain.formCo.class);//将request对象里的map参数转换为fromco表里的类型并保存
        if (!formCo.validata()){  //如果属性值为空
            request.setAttribute("formc",formCo);//在request对象中加入名为formc的属性并赋值
            return "/add.jsp"; //返回到/add.jsp中
        }

        Co co=CommonUtils.toBean(request.getParameterMap(), Co.class);//将request对象里的map参数转换为co表里的类型并保存

        coService.add(co);
        request.setAttribute("msg","添加成绩表成功");//在request对象中加入名为msg的属性并赋值添加成绩表成功

        return "/msg.jsp";//返回到/msg.jsp中
    }

    public String delete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String cno=request.getParameter("cno");//在request对象里获取参数值
        Delete_Check delete_check=new Delete_Check();
        if(!delete_check.validata2("cno",cno)){  //如果数值为空
            request.setAttribute("msg2","课程号:"+cno+"  为成绩表与TEACH表外键，拒绝删除");
            //在request对象中加入名为msg2的属性并赋值课程号:"+cno+"  为成绩表与TEACH表外键，拒绝删除
            return "/msg.jsp";//返回到/msg.jsp中
        }

        coService.delete(cno);
        request.setAttribute("msg","删除课程表成功");//在request对象中加入名为msg的属性并赋值删除课程表成功

        return "/msg.jsp";//返回到/msg.jsp中
    }

    public String preEdit(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String cno=request.getParameter("cno"); //在request对象里获取参数值
        Co co=coService.find(cno); //在coService执行查询操作
        request.setAttribute("co",co);//在request对象中加入名为co的属性并赋值

        return "/co_edit.jsp";//返回到/co_edit.jsp中
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Co co=CommonUtils.toBean(request.getParameterMap(),Co.class);//将request对象里的map参数转换为co表里的类型并


        coService.edit(co);//执行改正操作

        request.setAttribute("msg","编辑成绩表成功");//在request对象中加入名为msg的属性并赋值编辑成绩表成功

        return "/msg.jsp";
    }//返回到/msg.jsp中

    public String findAll(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        List<Co> cos=coService.findAll();  //执行全表查询

        request.setAttribute("coList",cos);//在request对象中加入名为coList属性并赋值cos

        return "/co_list.jsp";//返回到"/co_list.jsp中
    }

    public String query(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        Co co=CommonUtils.toBean(request.getParameterMap(),Co.class);//将request对象里的map参数转换为co表里的类型并


        List<Co> cos=coService.query(co);//执行查询操作
        request.setAttribute("coList",cos);//在request对象中加入名为coList属性并赋值cos
        return "/co_list.jsp";//返回到"/co_list.jsp中
    }
}
