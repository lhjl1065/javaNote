package cn.lhjl.service.UserServiceImpl;

import cn.lhjl.dao.UserDao;
import cn.lhjl.domain.Id_SessionId;
import cn.lhjl.domain.PageBean;
import cn.lhjl.domain.QueryVo;
import cn.lhjl.domain.User;
import cn.lhjl.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDao userDao;
    static {
        try {
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSession sqlSession = builder.build(in).openSession(true);
            userDao = sqlSession.getMapper(UserDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User login(User user) {
        return userDao.findUserByUsernameAndPassword(user);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.delete(id);
    }

    @Override
    public User findUser(String id) {
        return userDao.findUser(Integer.parseInt(id));
    }

    @Override
    public void update(User user) {
        //调用UserDao的update方法
        userDao.update(user);
    }

    @Override
    public void deleteUsers(String[] ids) {

        for (String id:
             ids) {
            userDao.delete(Integer.parseInt(id));
        }
    }


    @Override
    public PageBean<User> findUsersByPage(QueryVo queryVo) {
        //1.字符串转型为int
        //如果当前页码为0则置为1,为最大页码+1则置为最大页码
        int currentPage = queryVo.getCurrentPage();
        int rows = queryVo.getRows();
        User user = queryVo.getUser();
        if (currentPage==0){
            queryVo.setCurrentPage(1);
        }

        //2.封装PageBean
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setCurrentPage(currentPage);
        //查询总记录数
        int totalCount=userDao.findTotalCount(user);
        pageBean.setTotalCount(totalCount);
        int totalPage=(totalCount%rows)==0?totalCount/rows:totalCount/rows+1;
        pageBean.setTotalPage(totalPage);
        //查询当前页数据
        List<User> list=userDao.findUsersByPage(queryVo);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public User findByUser(String username) {
        return userDao.findByUser(username);
    }

    @Override
    public void updateSessionIdForAccount(Id_SessionId id_sessionId) {
        userDao.updateSessionIdForAccount(id_sessionId);
    }

    @Override
    public String querySessionID(int id) {
        return userDao.querySessionId(id);
    }
}
