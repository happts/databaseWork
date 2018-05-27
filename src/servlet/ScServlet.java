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

/**作者：物联1603 齐鹏
 * 交互式地浏览和修改与score表相关的数据，生成动态Web内容
 **/

@WebServlet("/ScServlet")

public class ScServlet extends BaseServlet {
    private ScService scService=new ScService();

    /**
     * 对应前端score表中的添加操作
     * 处理用户的添加请求
     * @param request
     * @param response
     * @return 相应处理结果的提示页面
     * @throws ServletException
     * @throws IOException
     */
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        formSc formSc=CommonUtils.toBean(request.getParameterMap(), domain.formSc.class);
        if(!formSc.validata()){  //用户对添加操作不合法时，
            request.setAttribute("formsc",formSc); //获取formsc中的信息
            return "/add.jsp";  //返回add.jsp的页面并将获取到的信息在add页面中显示出来
        }

        Sc sc=CommonUtils.toBean(request.getParameterMap(),Sc.class);
        if(request.getParameter("grade").equals(""))sc.setGrade(null);
        //当用户添加score表信息时若未添加成绩，则将成绩这一项设为null
        scService.add(sc);  //将用户提交的新的成绩信息记录加入score中
        request.setAttribute("msg","添加成绩表成功"); //把添加成功的提示字符加入msg页面
        return "/msg.jsp"; //返回并显示msg页面信息
    }

    /**
     * 对应前端的删除操作
     * @param request
     * @param response
     * @return 删除操作结果成功与否的提示页面
     * @throws ServletException
     * @throws IOException
     */
    public String delete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String sno=request.getParameter("sno");
        String cno=request.getParameter("cno");

        scService.delete(sno,cno);//将用户要删除的记录在score表中删除
        request.setAttribute("msg","删除成绩表成功");//把删除成功的提示字符加入msg页面
        return "/msg.jsp"; //返回并显示msg页面信息

    }

    /**
     * 预编辑，找到sno和cno对应的元组
     * @param request
     * @param response
     * @return score表编辑页面
     * @throws ServletException
     * @throws IOException
     */
    public String preEdit(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String sno=request.getParameter("sno");
        String cno=request.getParameter("cno");
        Sc sc=scService.find(sno,cno);  //从score表中找到学号课程号分别为sno和cno的元组并赋予变量sc

        request.setAttribute("sc",sc); //将sc加入request对象

        return "/sc_edit.jsp";  //返回score的编辑页面
    }

    /**
     * 编辑score中某一元组的信息，并将修改结果存入数据库
     * @param request
     * @param response
     * @return 编辑结果提示页面
     * @throws ServletException
     * @throws IOException
     */
    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        Sc sc=new Sc();
        sc=CommonUtils.toBean(request.getParameterMap(),Sc.class);  //将sc转化为Javabean

        if(!request.getParameter("grade").trim().isEmpty()){
            //当编辑栏中grade不为空格符时，把grade中的值加入sc中
            sc.setGrade(Integer.valueOf(request.getParameter("grade")));
        }else sc.setGrade(null);//否则把sc中的grade属性值设为null

        scService.edit(sc);  //把修改后的sc变量添加到数据库中

        request.setAttribute("msg","编辑成绩表成功");//把删除成功的提示字符加入msg页面

        return "/msg.jsp";//返回并显示msg页面信息
    }

    /**
     * 查询score表中所有元组
     * @param request
     * @param response
     * @return 查询出的所有score表中的元组并在sc_list页面中显示出来
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        List<Sc> scs=scService.findAll(); //查询出score表中的所有元组并放到scs列表中

        request.setAttribute("scList",scs);  //把scs中的所有信息添加到request对象中

        return "/sc_list.jsp"; //返回并显示sc_list页面
    }

    /**
     * 查询某成绩的同学信息
     * @param request
     * @param response
     * @return 把查询到学号和课程号信息在sc_list页面上显示出来
     * @throws ServletException
     * @throws IOException
     */
    public String query(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        Sc sc=new Sc();
        sc=CommonUtils.toBean(request.getParameterMap(),Sc.class); //将sc转化为Javabean

        if(request.getParameter("grade")!=""){ //从页面中获取grade值
            //如果grade值不为空，将grade的值赋给sc中的对应属性
            sc.setGrade(Integer.valueOf(request.getParameter("grade")));
        }else sc.setGrade(null);//否则，把sc中的grade值设为空


        List<Sc> scs=scService.query(sc); //在score表中找到符合sc条件的所有元组并赋给scs列表
        request.setAttribute("scList",scs);//把scs中的信息加入到request对象中
        return "/sc_list.jsp"; //返回并显示查询到的信息
    }

}
