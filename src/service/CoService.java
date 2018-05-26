package service;


import dao.CoDao;
import entity.Co;

import java.util.List;

/**
 * Create by PstereoM on 2018/4/27
 **/
public class CoService {
    CoDao coDao=new CoDao();

    public void add(Co co){coDao.add(co);}

    public void delete(String cno){coDao.delete(cno);}

    public Co find(String cno){return coDao.find(cno);}

    public void edit(Co co){coDao.edit(co);}

    public List<Co> findAll(){return coDao.findAll();}

    public List<Co> query(Co co){return coDao.query(co);}
}
