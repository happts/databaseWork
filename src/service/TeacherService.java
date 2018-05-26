package service;

import dao.TeacherDao;
import entity.Teacher;

import java.util.List;

/**
 * Create by PstereoM on 2018/4/24
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
