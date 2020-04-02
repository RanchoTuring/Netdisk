package api;

import utils.Authentication;

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
     * 接收 用户名 参数，与会话中保存的用户名对比
     * 两者一致时，销毁会话对象，退出登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(!Authentication.isLogin(request)){
            return;
        }
        String usernamePara=request.getParameter("username");

        String sessionUsername=(String)request.getSession().getAttribute("username");

        if(usernamePara.equals(sessionUsername)){
            request.getSession().invalidate();
            System.out.println("用户"+usernamePara+"退出登录");
        }
    }
}
