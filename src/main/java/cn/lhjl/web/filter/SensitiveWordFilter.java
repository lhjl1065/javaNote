package cn.lhjl.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

@WebFilter("/*")
public class SensitiveWordFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(final ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.创建req的代理对象
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //2.增强getParameter方法
                if (method.getName().equals("getParameter")) {
                    if (method.invoke(req, args) != null) {
                        String value = (String) method.invoke(req, args);
                        for (String s : list) {
                            if (value.contains(s)) {
                                return value.replaceAll(s, "**");
                            }
                        }
                    }
                }
                //3.增强getParameterValues()方法
                if (method.getName().equals("getParameterValues")) {
                    String[] values = (String[]) method.invoke(req, args);
                    if (values.length != 0) {
                        for (int i = 0; i < values.length; i++) {
                            if (values[i] != null) {
                                for (String s :
                                        list) {
                                    if (values[i].contains(s)) {
                                        values[i] = values[i].replaceAll(s, "**");
                                    }
                                }
                            }
                        }
                        return values;
                    }
                }
                //增强getParameterMap方法
                if (method.getName().equals("getParameterMap")) {
                    Map<String, String[]> map = (Map<String, String[]>) method.invoke(req,args);
                    Map<String, String[]> mapCopy = new HashMap<>();
                    Set<String> set = map.keySet();
                    for (String s :
                            set) {
                        String[] strings = map.get(s);
                        for (int i = 0; i < strings.length; i++) {
                            for (String s1 :
                                    list) {
                                if (strings[i] != null || strings[i].length() > 0) {
                                    if (strings[i].contains(s1)) {
                                        strings[i]=strings[i].replaceAll(s1,"**");

                                    }
                                }
                            }
                        }
                        mapCopy.put(s,strings);
                    }
                }
                //没有敏感词汇则原样执行
                return method.invoke(req, args);
            }
        });
//放行
        chain.doFilter(proxy_req, resp);
    }

    private List<String> list = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {
        try {
            //1.获取ServletContext对象
            ServletContext context = config.getServletContext();
            //2.获取真实路径
            String realPath = context.getRealPath("/WEB-INF/classes/过滤词汇.txt");
            //3.将文件的每一行数据添加到list中
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            //关闭br
            br.close();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
