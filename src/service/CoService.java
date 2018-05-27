package service;
/**
 * 贾天豪
 */

import dao.CoDao;
import entity.Co;

import java.util.List;


public class CoService {
    CoDao coDao=new CoDao();

    public void add(Co co){coDao.add(co);}//在codao中进行增加操作

    public void delete(String cno){coDao.delete(cno);}//在codao中进行删除操作

    public Co find(String cno){return coDao.find(cno);}//将所查询到的值返回

    public void edit(Co co){coDao.edit(co);}//在codao中进行改正操作

    public List<Co> findAll(){return coDao.findAll();}//将所查询到的值返回

    public List<Co> query(Co co){return coDao.query(co);}//在codao中进行查询操作
}
