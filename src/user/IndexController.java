package user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 首页访问控制器
 */
@WebServlet(name = "IndexController",urlPatterns = "")
public class IndexController extends HttpServlet {
    /**
     * 从会话中获取 用户名 参数，跳转到对应用户的网盘页面
     * 若不存在，则跳转到登录页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=(String)request.getSession().getAttribute("username");

        if(username==null){
            response.sendRedirect("user/login.html");
        }else {
            response.sendRedirect("disc.html");
        }
    }
}
