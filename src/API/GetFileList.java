package api;

import model.DbUtil;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


/**
 *  网盘文件列表接口
 */
@WebServlet(name = "GetFileList",urlPatterns = "/getFileList")
public class GetFileList extends HttpServlet {

    /**
     * 获取 用户名 参数，返回该用户网盘中存储的文件信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        String username=(String)request.getSession().getAttribute("username");
        //判断用户是否存在
        if(username==null) {
            System.out.println("用户不存在");
            return;
        }

        //访问该用户对应的文件数据表
        Connection connection=DbUtil.getConnection("netdisc");

        try {
            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("select filename,filesize,time from "+username+";");

            JSONObject data=new JSONObject();

            while(resultSet.next()){

                JSONObject file=new JSONObject();
                file.put("filename",resultSet.getString("filename"));
                file.put("filesize",resultSet.getString("filesize"));
                file.put("time",resultSet.getTimestamp("time"));
                data.append("files",file);
            }
            response.getWriter().print(data);

        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }

    }
}
