package cn.lhjl.dao;

import cn.lhjl.domain.Id_SessionId;
import cn.lhjl.domain.QueryVo;
import cn.lhjl.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {

    public List<User> findAll();
    public User findUserByUsernameAndPassword(User user);
    public void add(User user);
    public void delete(int id);

    User findUser(int id);

    void update(User user);

    int findTotalCount(User user);

    List<User> findUsersByPage(QueryVo queryVo);

    User findByUser(String username);

    void updateSessionIdForAccount(Id_SessionId id_sessionId);

    String querySessionId(int id);
}
