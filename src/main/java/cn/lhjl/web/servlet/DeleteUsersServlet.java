package cn.lhjl.web.servlet;

import cn.lhjl.service.UserService;
import cn.lhjl.service.UserServiceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUsersServlet")
public class DeleteUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数ids
        String[] ids = request.getParameterValues("ids");
        //2.调用UserService的deleteUsers方法
        UserService userService = new UserServiceImpl();
        userService.deleteUsers(ids);
        //3.跳转到userListServlet
        response.sendRedirect(request.getContextPath() + "/findUsersByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
