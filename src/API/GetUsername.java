package api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "getUsername",urlPatterns = "/getUsername")
public class GetUsername extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(request.getSession().getAttribute("username"));

        //Jedis jedis =new Jedis();
        //jedis.set("xx","yyy");
        //jedis.zrangebyscore


    }
}



