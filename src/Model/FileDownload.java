package model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "FileDownload", urlPatterns = "/downloadFile")
public class FileDownload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //处理cookie,获取用户名参数
        String username=null;

        Cookie[] cookies=request.getCookies();

        for(Cookie cookie:cookies){
            if("username".equals(cookie.getName())){
                username=cookie.getValue();
            }
        }

        if(username==null){
            System.out.println("cookie 中 username 参数不存在");
            return;
        }

        //获取文件名
        String filename = request.getParameter("filename");

        if (filename == null) {
            System.out.println("无 filename  参数");
            return;
        }

        System.out.println("filename = " + filename);


        //查找该用户的数据库，在数据库中找到文件对应的下载地址

        Connection connection=DbUtil.getConnection("netdisc");

        try {
            Statement statement=connection.createStatement();



            ResultSet resultSet=statement.executeQuery(
                    "select filename,path from "+username+" where filename='"+filename+"';");

            String path=null;
            while (resultSet.next()){
                path=resultSet.getString("path");
            }

            File file=new File(path);

            if (file.exists()) {
                // 配置响应头，指定响应类型为附件，对中文文件名进行编码
                response.addHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));

                //添加文件大小信息
                response.addHeader("Content-Length", "" + file.length());

                InputStream inputStream = new FileInputStream(file);

                byte[] chars = new byte[1024];
                int len = -1;
                OutputStream out = response.getOutputStream();
                while ((len = inputStream.read(chars)) != -1) {
                    out.write(chars, 0, len);
                }
                inputStream.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }




    }


}



