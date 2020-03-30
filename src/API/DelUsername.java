package api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户退出登录的功能接口
 *
 */

@WebServlet(name = "DelUsername",urlPatterns = "/delUsername")
public class DelUsername extends HttpServlet {

    /**
     * 接收 用户名 参数，在会话中删除该用户名，退出登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String usernamePara=request.getParameter("username");
        System.out.print("用户"+usernamePara+"请求删除用户：");

        String sessionUsername=(String)request.getSession().getAttribute("username");
        System.out.println(sessionUsername);

        if(usernamePara.equals(sessionUsername)){
            request.getSession().invalidate();
            System.out.println("用户"+usernamePara+"退出登录");
        }
    }
}
