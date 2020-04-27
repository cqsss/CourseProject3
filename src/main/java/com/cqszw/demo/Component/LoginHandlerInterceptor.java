package com.cqszw.demo.Component;

import com.cqszw.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆检查，对未登录的操作进行拦截
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行执行prehandle
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser==null){
            //若为空，说明没有登陆,进行拦截,强制返回登陆页面
            request.setAttribute("msg","没有权限，请先登陆");//显示错误消息
            request.getRequestDispatcher("/").forward(request,response);
            return  false;
        }else{
            //否则放行请求
            return  true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
