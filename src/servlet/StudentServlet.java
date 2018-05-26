package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import domain.Delete_Check;
import entity.Student;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import service.StudentService;

import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import domain.formStudent;

@WebServlet("/StudentServlet")


public class StudentServlet extends BaseServlet {
    private StudentService studentService=new StudentService();

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        formStudent formStudent=CommonUtils.toBean(request.getParameterMap(), domain.formStudent.class);
        if(!formStudent.validata()){
            request.setAttribute("form",formStudent);

            return "/add.jsp";
        }
        Student student = CommonUtils.toBean(request.getParameterMap(), Student.class);

        studentService.add(student);

        request.setAttribute("msg", "添加学生成功");
        return "/msg.jsp";
    }


    public String delete(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String sno=request.getParameter("sno");
        Delete_Check delete_check=new Delete_Check();
        if(!delete_check.validata2("sno",sno)){
            request.setAttribute("msg2","学号："+sno+"  为成绩表外键，拒绝删除");

            return "/msg.jsp";
        }
        studentService.delete(sno);
        System.out.println(sno);
        request.setAttribute("msg","删除学生成功");

        return "/msg.jsp";
    }

    public String preEdit(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        String sno=request.getParameter("sno");
        Student student=studentService.find(sno);

        request.setAttribute("student",student);

        return "/edit.jsp";
    }


    public String edit(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        BeanUtilsBean.getInstance().getConvertUtils().register(new SqlDateConverter(null), Date.class);//日期可以为空，避免DATE类型为null时异常。
        Student student=CommonUtils.toBean(request.getParameterMap(),Student.class);

        studentService.edit(student);

        request.setAttribute("msg", "编辑学生成功");
        return "/msg.jsp";
    }

    public String findAll(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        List<Student> students=studentService.findAll();

        request.setAttribute("studlist",students);

        return "/list.jsp";
    }


    public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);//日期可以为空，避免DATE类型为null时异常。
        Student student=CommonUtils.toBean(request.getParameterMap(),Student.class);
    //    BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
        List<Student> students=studentService.query(student);

        request.setAttribute("studlist",students);

        return "/list.jsp";

    }
}
