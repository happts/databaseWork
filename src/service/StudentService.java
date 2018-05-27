package service;

import dao.StudentDao;
import entity.Student;

import java.util.List;

/**
 * Service业务层
 * 将数据操作与表示层分隔
 * 只执行业务逻辑
 * Student对应的业务层
 * @author 王浩然
 */
public class StudentService {
    StudentDao studentDao=new StudentDao();

    public void add(Student student){ studentDao.add(student);}

    public void delete(String sno){studentDao.delete(sno);}

    public Student find(String sno){ return studentDao.find(sno);}

    public void edit(Student student){studentDao.edit(student);}

    public List<Student> findAll(){return studentDao.findAll();}

    public List<Student> query(Student student){return studentDao.query(student);}
}
