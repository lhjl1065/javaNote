package cn.lhjl.web.servlet;

import cn.lhjl.domain.User;
import cn.lhjl.service.UserService;
import cn.lhjl.service.UserServiceImpl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //1.编码
            request.setCharacterEncoding("utf-8");
            //2.获取参数
            Map<String, String[]> map = request.getParameterMap();
            //3.封装user
            User user = new User();
            BeanUtils.populate(user,map);
            //4.调用UserService的update方法
            UserService userService = new UserServiceImpl();
            userService.update(user);
            //5跳转到userListServlet
            response.sendRedirect(request.getContextPath()+"/findUsersByPageServlet");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
