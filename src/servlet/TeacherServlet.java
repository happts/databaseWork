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
 *TEACHER对应的Servlet类,处理jsp网页前端的请求
 * @author 黄涛
 **/

/**
 * @webServlet() 指定一组 Servlet 的 URL 匹配模式
 * 用于jsp网页找到对应的Servlet来处理请求
 */
@WebServlet("/TeacherServlet")
public class TeacherServlet extends BaseServlet {
    /**
     * 获得teacher的业务处理类
     */
    private TeacherService teacherService=new TeacherService();

    /**
     * 执行网页的添加数据请求,通过request获得前端对应的键值对, toBean 转为对应的实体类,再进行后续的操作
     * 通过teacherService执行添加
     * 执行结果
     * 返回结果网页
     * @param request
     * @param response
     * @return
     * @throws SQLException
     * @throws IOException
     */
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

    /**
     * 执行删除请求,request获得tno, Delete_Check 检查是否能删除,
     * Service执行删除
     * 返回结果网页
     * @param request
     * @param response
     * @return 结果网页
     * @throws SQLException
     * @throws IOException
     */
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

    /**
     * 执行修改请求
     * 因为ConvertUtils.register函数支持8种基本类型与String自动转换,
     * Date类型不在其中,则需要注册一个Date转换器
     * @param request
     * @param response
     * @return 结果网页
     * @throws SQLException
     * @throws IOException
     */
    public String edit(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException{

        BeanUtilsBean.getInstance().getConvertUtils().register(new SqlDateConverter(null), Date.class);//日期可以为空，避免DATE类型为null时异常。
        Teacher teacher=CommonUtils.toBean(request.getParameterMap(),Teacher.class);
        teacherService.edit(teacher);
        request.setAttribute("msg","编辑教师成功");
        return "/msg.jsp";
    }

    /**
     * 执行查询请求
     * @param request
     * @param response
     * @return 结果列表网页
     * @throws SQLException
     * @throws IOException
     */
    public String query(HttpServletRequest request,HttpServletResponse response)throws SQLException,IOException{
        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);//日期可以为空，避免DATE类型为null时异常。
        Teacher teacher=CommonUtils.toBean(request.getParameterMap(),Teacher.class);
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
