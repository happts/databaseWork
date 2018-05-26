package service;

import dao.TeacherDao;
import entity.Teacher;

import java.util.List;

/**
 * Service业务层
 * 将数据操作与表示层(前端)分隔开
 * 只执行业务逻辑,不进行数据操作
 *
 * TEACHER对应的业务层
 * @author 黄涛
 * @see TeacherDao
 **/
public class TeacherService {
    private TeacherDao teacherDao=new TeacherDao();

    public void add(Teacher teacher){teacherDao.add(teacher);}

    public void delete(String tno){teacherDao.delete(tno);}

    public void edit(Teacher teacher){teacherDao.edit(teacher);}

    public Teacher find(String tno){return teacherDao.find(tno);}

    public List<Teacher> findAll(){return teacherDao.findAll();}

    public List<Teacher> query(Teacher teacher){return teacherDao.query(teacher);}
}
