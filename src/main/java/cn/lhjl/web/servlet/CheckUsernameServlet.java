package cn.lhjl.web.servlet;

import cn.lhjl.domain.User;
import cn.lhjl.service.UserService;
import cn.lhjl.service.UserServiceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/checkUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //创建json解析器和map集合
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<>();
        //设置Mime类型为json，编码为utf-8
        response.setContentType("application/json;charset=utf-8");
        String username = request.getParameter("username");
        System.out.println(username);
        if (username==null||username.length()==0){
            System.out.println("1100");
            map.put("exist",true);
            map.put("msg","请输入用户名");
            objectMapper.writeValue(response.getWriter(),map);
            return;
        }

        UserService userService = new UserServiceImpl();
        User user= userService.findByUser(username);


        if (user==null){
            map.put("exist",false);
            map.put("msg","用户名不存在");
            objectMapper.writeValue(response.getWriter(),map);
            System.out.println(objectMapper.writeValueAsString(map));
        }
        else {
            map.put("exist",true);
            map.put("msg","请输出密码");
            objectMapper.writeValue(response.getWriter(),map);
            System.out.println(objectMapper.writeValueAsString(map));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
