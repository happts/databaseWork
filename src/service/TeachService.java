
//张仲昊

package service;

import dao.TeachDao;
import domain.Teach;

import java.util.List;

/**
 * Create by PstereoM on 2018/5/6
 **/
public class TeachService {
    //增删改查，通过dao层实现

    TeachDao teachDao=new TeachDao();

    public void add(Teach teach){teachDao.add(teach);}//插入

    public void delete(String id){teachDao.delete(id);}//删除

    public Teach find(String id){return teachDao.find(id);}//寻找主码

    public List<Teach> findAll(){return teachDao.findAll();}//全部查询

    public List<Teach> query(Teach teach){return teachDao.query(teach);}//查询
}

