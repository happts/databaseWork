package servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import domain.Delete_Check;
import entity.Teacher;
import domain.formTeacher;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import service.TeacherService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Create by PstereoM on 2018/4/24
 **/
@WebServlet("/TeacherServlet")
public class TeacherServlet extends BaseServlet {
    private TeacherService teacherService=new TeacherService();

    public String add(HttpServletRequest request, HttpServletResponse response)throws SQLException,IOException{
        formTeacher formTeacher=CommonUtils.toBean(request.getParameterMap(), domain.formTeacher.class);
        if (!formTeacher.validata()){
            request.setAttribute("formt",formTeacher);
            return "/add.jsp";
        }

        Teacher teacher= CommonUtils.toBean(request.getParameterMap(),Teacher.class);
        teacherService.add(teacher);

        request.setAttribute("msg","添加教师成功");
        return "/msg.jsp";
    }

    public String delete(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException{
        String tno=request.getParameter("tno");
        Delete_Check delete_check=new Delete_Check();
        if(!delete_check.validata2("tno",tno)){
            request.setAttribute("msg2","教师编号:"+tno+"  为TEACH表外键拒绝删除");

            return "/msg.jsp";
        }

        teacherService.delete(tno);

        request.setAttribute("msg","删除教师成功");
        return "/msg.jsp";
    }

    public String preEdit(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException{
        String tno=request.getParameter("tno");
        Teacher teacher=teacherService.find(tno);

        request.setAttribute("teacher",teacher);
        return "/te_edit.jsp";
    }

    public String edit(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException{
        BeanUtilsBean.getInstance().getConvertUtils().register(new SqlDateConverter(null), Date.class);//日期可以为空，避免DATE类型为null时异常。
        Teacher teacher=CommonUtils.toBean(request.getParameterMap(),Teacher.class);
        teacherService.edit(teacher);

        request.setAttribute("msg","编辑教师成功");
        return "/msg.jsp";
    }

    public String query(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException{
        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);//日期可以为空，避免DATE类型为null时异常。
        Teacher teacher=CommonUtils.toBean(request.getParameterMap(),Teacher.class);
    //    BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
        List<Teacher> teachers= teacherService.query(teacher);

        request.setAttribute("teaList",teachers);
        return "/te_list.jsp";
    }

    public String findAll(HttpServletRequest request,HttpServletResponse response){
        List<Teacher> teachers=teacherService.findAll();

        request.setAttribute("teaList",teachers);
        return "/te_list.jsp";
    }
}
