package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 首页访问过滤
 */
@WebFilter(filterName = "Filter2",urlPatterns = "/")
public class Filter implements javax.servlet.Filter {
   //销毁
    public void destroy() {
    }

    /**
     * 从会话中获取 用户名 参数，跳转到对应用户的网盘页面
     * 若不存在，则跳转到登录页面
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)resp;

        String username=(String)request.getSession().getAttribute("username");

        System.out.println("首页跳转过滤");
        if(username==null){
            response.sendRedirect("user/login.html");
        }else {
            response.sendRedirect("disc.html");
        }
    }

    //初始化
    public void init(FilterConfig config) throws ServletException {

    }

}
