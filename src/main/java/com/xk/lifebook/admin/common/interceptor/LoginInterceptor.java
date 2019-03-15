package com.xk.lifebook.admin.common.interceptor;

import com.xk.lifebook.admin.common.utils.CheckMobile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录拦截
 */
public class LoginInterceptor implements HandlerInterceptor {
    Logger logger =  LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        logger.info("------preHandle------");
        //获取session
        HttpSession session = request.getSession(true);
        //判断用户ID是否存在，不存在就跳转到登录界面
        if(!check(request,response)&&session.getAttribute("userId") == null){
            logger.info("拦截"+request.getRequestURL());
            logger.info("------:跳转到login页面！");
            response.sendRedirect(request.getContextPath()+"/User/login");
            return false;
        }else{
            session.setAttribute("userId", session.getAttribute("userId"));
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 检查访问方式是否为移动端
     *
     * @Title: check
     * @Date : 2014-7-7 下午03:55:19
     * @param request
     * @throws IOException
     */
    public boolean check(HttpServletRequest request,HttpServletResponse response) throws IOException {
        boolean isFromMobile=false;

        HttpSession session= request.getSession();
        //检查是否已经记录访问方式（移动端或pc端）
        if(null==session.getAttribute("ua")){
            try{
                //获取ua，用来判断是否为移动端访问
                String userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();
                if(null == userAgent){
                    userAgent = "";
                }
                isFromMobile= CheckMobile.check(userAgent);
                //判断是否为移动端访问
                if(isFromMobile){
                    System.out.println("移动端访问");
                    session.setAttribute("ua","mobile");
                } else {
                    System.out.println("pc端访问");
                    session.setAttribute("ua","pc");
                }
            }catch(Exception e){}
        }else{
            isFromMobile=session.getAttribute("ua").equals("mobile");
        }

        return isFromMobile;
    }
}
