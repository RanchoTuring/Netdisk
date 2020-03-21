package user;

import Model.DBUtil;
import Utils.Encoder;

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

@WebServlet(name = "LoginController",urlPatterns = "/user/userLogin")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("username = " + username);
        System.out.println("password = " + password);

        //密码加密
        password= Encoder.encodeBase64(password);

        //链接数据库  查询  有没有用户名跟密码相同的用户，返回他的信息
        Connection connection= DBUtil.getConnection("netdisc");
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select count(*) from user where username=\""+username+"\" and password=\""+password+"\";");

            //遍历
            while (resultSet.next()){
                if(resultSet.getInt("count(*)")==0){
                    System.out.println("用户名或密码错误");
                    return;
                }
            }

           //保存登录状态
            request.getSession().setAttribute("username",username);
            response.sendRedirect("../disc.html");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
