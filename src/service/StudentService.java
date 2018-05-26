package service;

import dao.StudentDao;
import entity.Student;

import java.util.List;

public class StudentService {
    StudentDao studentDao=new StudentDao();

    public void add(Student student){ studentDao.add(student);}

    public void delete(String sno){studentDao.delete(sno);}

    public Student find(String sno){ return studentDao.find(sno);}

    public void edit(Student student){studentDao.edit(student);}

    public List<Student> findAll(){return studentDao.findAll();}

    public List<Student> query(Student student){return studentDao.query(student);}
}
