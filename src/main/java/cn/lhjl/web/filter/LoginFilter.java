package cn.lhjl.web.filter;


import cn.lhjl.dao.UserDao;
import cn.lhjl.domain.User;
import cn.lhjl.service.UserService;
import cn.lhjl.service.UserServiceImpl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //1.强制转换为HttpServletRequest 和HttpServletResponse
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //1.判断请求的资源是否是登陆相关资源包括css js fonts checCode
        //是则放行
        String servletPath = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(servletPath);
        if (servletPath.contains("/login.jsp") || servletPath.contains("checkCode") || servletPath.contains("/loginServlet")
                || servletPath.contains("/css") || servletPath.contains("/fonts") || servletPath.contains("/js")||servletPath.contains("/checkUsernameServlet")) {

            chain.doFilter(req, resp);
        }
        //不是则检测session中是否有user
        else {
            //没有则跳转到登陆页并提示登陆
            System.out.println("没有则跳转到登陆页并提示登陆");
            if (request.getSession().getAttribute("user") == null) {
                request.setAttribute("error_msg", "请先登录");
                request.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
            //有user则用user的id到数据库查找userId对应的SessionId，与单前浏览器的Session比较，如果不等，说明已经有人
            //在另外的终端登录了这个账号从而刷新了这个账户对应的SessionId
            else {
                User user = (User) request.getSession().getAttribute("user");
                System.out.println(user);
                int id = user.getId();
                String currentSessionId = request.getRequestedSessionId();
                UserServiceImpl userService = new UserServiceImpl();
                String sessionId =userService.querySessionID(id);
                System.out.println("current:"+currentSessionId);
                System.out.println("session:"+sessionId);
                if (currentSessionId.equals(sessionId)){
                    chain.doFilter(req, resp);
                }
                else {
                    response.sendRedirect(request.getContextPath()+"/login.jsp");
                }
            }

        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
