package filters;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编码过滤器，匹配所有请求
 */
@WebFilter(filterName = "Filter1",urlPatterns = "/*")
public class EncodeFilter implements Filter {
    public void destroy() {
    }

    /**
     * 将 请求编码 修改为utf-8编码
     *
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)resp;
        System.out.println("编码过滤");
        request.setCharacterEncoding("utf-8");

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
