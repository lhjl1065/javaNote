package cn.lhjl.service;

import cn.lhjl.domain.Id_SessionId;
import cn.lhjl.domain.PageBean;
import cn.lhjl.domain.QueryVo;
import cn.lhjl.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();
    /**
     * 查询账户和密码匹配
     */
    public User login(User user);

    /**
     * 添加用户
     * @param user
     */
    public void add(User user);

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(int id);

    /**
     * 查找某个用户
     * @param id
     * @return
     */
    User findUser(String id);

    /**
     * 修改某个用户数据
     * @param user
     */
    void update(User user);

    void deleteUsers(String[] ids);


    /**
     * 分页查询
     * @param queryVo
     * @return
     */

    PageBean<User> findUsersByPage(QueryVo queryVo);

    User findByUser(String username);

    void updateSessionIdForAccount(Id_SessionId id_sessionId);

    String querySessionID(int id);
}
