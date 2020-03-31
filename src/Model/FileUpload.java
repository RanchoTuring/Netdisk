package model;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@MultipartConfig
@WebServlet(name = "FileUpload",urlPatterns = "/upload")
public class FileUpload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //鉴权

        //提取用户名
        String username=(String)request.getSession().getAttribute("username");


        //判断用户是否存在
        if(username==null) {
            System.out.println("用户不存在");
            return;
        }


        //获取文件对象
        Part part=request.getPart("file");

        String filename=part.getSubmittedFileName();

        String path="D://0testFile/"+filename;

        //保存
        part.write(path);

        //-----------------------------------  更新数据库信息  ---------------------

        //获取数据库连接
        Connection connection=DbUtil.getConnection("netdisc");

        try {
            Statement statement=connection.createStatement();
           statement.execute("insert into "+username+" values(0,'"+filename+"',"+part.getSize()+",now(),'"+path+"');");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print(e.getMessage());
            return;
        }
        response.sendRedirect("disc.html");
    }

}
