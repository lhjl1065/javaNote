package cn.lhjl.web.servlet;

import cn.lhjl.service.UserService;
import cn.lhjl.service.UserServiceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter("id");
        System.out.println(id);
        //2.调用Service的deleteUser方法
        UserService userService = new UserServiceImpl();
        userService.deleteUser(Integer.parseInt(id));
        //3.跳转到UserListServlet
        response.sendRedirect(request.getContextPath()+"/findUsersByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
