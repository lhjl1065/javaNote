import cn.lhjl.dao.UserDao;
import cn.lhjl.domain.Id_SessionId;
import cn.lhjl.domain.QueryVo;
import cn.lhjl.domain.User;
import cn.lhjl.web.servlet.UpdateServlet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDemo {
    private UserDao userDao;
    private SqlSession sqlSession;
    private InputStream in;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void destory() {
        sqlSession.close();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findAllTest() {
        List<User> list = userDao.findAll();
        System.out.println(list);
    }

    @Test
    public void usernameAndPasswordTest() {
        User user = new User();
        user.setUsername("sana");
        user.setPassword("1234");
        User user1 = userDao.findUserByUsernameAndPassword(user);
        System.out.println(user1);
    }

    @Test
    public void add() {
        User user = new User();
        user.setName("mina");
        user.setPassword("123456");
        userDao.add(user);

    }

    @Test
    public void delete() {
        userDao.delete(61);

    }

    @Test
    public void findUser() {
        User user = userDao.findUser(41);
        System.out.println(user);


    }

    @Test
    public void update() {
        User user = new User();
        user.setName("项羽");
        user.setAddress("江东");
        user.setGender("男");
        user.setId(45);
        userDao.update(user);

    }
    @Test
    public void findTotalCount() {
        User user = new User();
        user.setName("阿");
        user.setAddress("");
        user.setGender("男");
        user.setId(45);
        int sum= userDao.findTotalCount(user);
        System.out.println(sum);

    }
    @Test
    public void findUserByPage() {
        User user = new User();
        QueryVo queryVo = new QueryVo();
        queryVo.setCurrentPage(1);
        queryVo.setRows(5);
        queryVo.setCurrentIndex(0);
        queryVo.setUser(user);
        List<User> users = userDao.findUsersByPage(queryVo);
        for (User user1 :
                users) {
            System.out.println(user1);
        }

    }
    @Test
    public void findByUser() {

        User user= userDao.findByUser("sana");
        System.out.println(user);

    }
    @Test
    public void updateSessionIdForAccount() {
        Id_SessionId id_sessionId = new Id_SessionId();
        id_sessionId.setSessionId("1111");
        id_sessionId.setAccount_id(1);
        userDao.updateSessionIdForAccount(id_sessionId);

    }
    @Test
    public void querySessionId() {
        String s = userDao.querySessionId(2);
        System.out.println(s);

    }



}
