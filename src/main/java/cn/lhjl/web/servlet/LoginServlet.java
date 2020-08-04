package cn.lhjl.web.servlet;

import cn.lhjl.domain.Id_SessionId;
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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入/loginServlet");
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取验证码
        String inputCode = request.getParameter("inputCode");
        //3.判断验证码
        String checkCode = (String) request.getSession().getAttribute("checkCode");
        request.getSession().removeAttribute("checkCode");
        if (checkCode != null && checkCode.equalsIgnoreCase(inputCode)) {
            try {
                //3.00验证码正确
                //3.01封装User对象
                Map<String, String[]> map = request.getParameterMap();
                User loginUser = new User();
                BeanUtils.populate(loginUser,map);
                //3.02.调用Service的login方法
                UserService userService = new UserServiceImpl();
                User user = userService.login(loginUser);
                //3.03判断用户名和密码匹配吗
                if (user!=null){
                    //3.03.00.用户名和密码匹配
                    //3.03.01存储user
                    request.getSession().setAttribute("user",user);
                    System.out.println(user);
                    String sessionId = request.getRequestedSessionId();
                    //3.03.02获取user的id和SessionID，用它更新Account_SessionID表
                    int id = user.getId();
                    Id_SessionId id_sessionId = new Id_SessionId();
                    id_sessionId.setAccount_id(id);
                    id_sessionId.setSessionId(sessionId);
                    userService.updateSessionIdForAccount(id_sessionId);
                    //3.03.03.重定向到index.jsp
                    response.sendRedirect(request.getContextPath()+"/index.jsp");
                }
                else{
                    //3.03.10.用户名和密码不匹配
                    //3.03.10.储存错误原因
                    request.setAttribute("error_msg","用户名或密码错误");
                    //3.03.11.跳转到登录页
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        else{
            //3.10验证码正确
            //3.11存一个错误信息
            request.setAttribute("error_msg","验证码错误");
            //3.12跳转回登录页
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
