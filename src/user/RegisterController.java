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

@WebServlet(name = "RegisterController",urlPatterns = "/user/userRegister")
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //前端验证：密码跟用户名都有时，才能发送请求

        //访问数据库
        Connection connection= DbUtil.getConnection("netdisc");
        try {
            //1.查找是否有重复用户，有则报错
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select count(username) from user where username=\""+username+"\";");

            //遍历
            while (resultSet.next()){
                if(resultSet.getInt("count(username)")>0){
                    System.out.println("用户已存在");
                    return;
                }
            }

            //加密密码
            password=Encoder.encodeBase64(password);

           //将注册用户的信息插入数据库
            statement.execute("insert into user values(0,\""+username+"\",\""+password+"\");");

            //用户创建成功后，为其创建文件表
            statement.execute("create table "+username+"(id int auto_increment not null,filename varchar(50) not null,filesize long not null,time timestamp default current_timestamp,path varchar(100) not null,primary key(id));");

            System.out.println("ok");

            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {




        }







        //2.存

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
