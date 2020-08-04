package cn.lhjl.web.servlet;

import cn.lhjl.domain.PageBean;
import cn.lhjl.domain.QueryVo;
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
import java.util.List;
import java.util.Map;

@WebServlet("/findUsersByPageServlet")
public class FindUsersByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setCharacterEncoding("utf-8");
            //1.获取每页记录数和当前页码以及集合map
            String rows = request.getParameter("rows");
            String currentPage = request.getParameter("currentPage");
            Map<String, String[]> map = request.getParameterMap();
            //2.如果这两项参数为空，赋默认值
            if (rows == null || "".equals(rows)) {
                rows = "5";
            }
            if (currentPage == null | "".equals(currentPage)) {
                currentPage = "1";
            }
            //组装Vo对象
            User user = new User();
            QueryVo queryVo = new QueryVo();
            BeanUtils.populate(user, map);
            queryVo.setUser(user);
            queryVo.setCurrentPage(Integer.parseInt(currentPage));
            queryVo.setRows(Integer.parseInt(rows));
            int currentIndex = (Integer.parseInt(currentPage )- 1) * Integer.parseInt(rows);
            queryVo.setCurrentIndex(currentIndex);
            //3.调用UserService的findUsersByPage方法
            UserService userService = new UserServiceImpl();
            PageBean<User> page = userService.findUsersByPage(queryVo);
            //4.将page和map存出入request域
            request.setAttribute("page", page);
            request.setAttribute("map", map);
            //5.转发到list.jsp
            request.getRequestDispatcher("/list.jsp").forward(request, response);
            int a;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
