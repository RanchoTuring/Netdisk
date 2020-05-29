package api;

import model.DbUtil;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;



/**
 * 网盘文件列表接口
 */
@WebServlet(name = "GetFileList", urlPatterns = "/getFileList")
public class GetFileList extends HttpServlet {

    /**
     * 获取 用户名 参数，返回该用户网盘中存储的文件信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        if (!Authentication.isLogin(request)) {
            return;
        }

        try {
            //获取结果集
            ResultSet resultSet = DbUtil.selectToSet("select filename,filesize,time from " +
                    request.getSession().getAttribute("username") + ";");

            //组装json对象
            JSONObject data = new JSONObject();
            while (resultSet.next()) {
                JSONObject file = new JSONObject();
                file.put("filename", resultSet.getString("filename"));
                file.put("filesize", resultSet.getString("filesize"));
                file.put("time", resultSet.getTimestamp("time"));
                data.append("files", file);
            }

            //返回数据
            response.getWriter().print(data);

        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }

    }
}
