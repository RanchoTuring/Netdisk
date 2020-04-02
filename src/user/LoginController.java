package user;

import model.DbUtil;
import utils.Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "LoginController", urlPatterns = "/user/userLogin")
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username = " + username);
        System.out.println("password = " + password);

        //密码加密
        password = Encoder.encodeBase64(password);

        int userCount = DbUtil.selectToInt("select count(username) from user where username=\""
                + username + "\" and password=\"" + password + "\";", "count(username)");
        if (userCount == 0) {
            System.out.println("用户名或密码错误");
            return;
        }

        //保存登录状态
        request.getSession().setAttribute("username", username);
        response.sendRedirect("../disc.html");
    }
}
