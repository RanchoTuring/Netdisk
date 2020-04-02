package utils;

import javax.servlet.http.HttpServletRequest;


/**
 * 鉴权模块，验证用户是否有操作权限
 */
public class Authentication {

    /**
     * 传入请求对象做参数，从请求所属的会话中，获取用户名属性
     * @param request
     * @return
     */
    public static boolean isLogin(HttpServletRequest request){
        String username=(String)request.getSession().getAttribute("username");

        //判断用户是否存在
        return username != null;
    }
}
