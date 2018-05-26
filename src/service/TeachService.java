package service;

import dao.TeachDao;
import entity.Teach;

import java.util.List;

/**
 * Create by PstereoM on 2018/5/6
 **/
public class TeachService {
    TeachDao teachDao=new TeachDao();

    public void add(Teach teach){teachDao.add(teach);}

    public void delete(String id){teachDao.delete(id);}

    public Teach find(String id){return teachDao.find(id);}


    public List<Teach> findAll(){return teachDao.findAll();}

    public List<Teach> query(Teach teach){return teachDao.query(teach);}
}

