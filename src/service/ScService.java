package service;

import dao.ScDao;
import entity.Sc;

import java.util.List;

public class ScService {
    ScDao scDao=new ScDao();

    public void add(Sc sc){scDao.add(sc);}

    public void delete(String sno,String cno){scDao.delete(sno,cno);}

    public Sc find(String sno,String cno){return scDao.find(sno,cno);}

    public void edit(Sc sc){scDao.edit(sc);}

    public List<Sc> findAll(){return scDao.findAll();}

    public List<Sc> query(Sc sc){return scDao.query(sc);}
}
