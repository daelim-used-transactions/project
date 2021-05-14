package com.daelim.transactions.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String idxId = (String) request.getSession().getAttribute("idxId");
        String str = (String) request.getSession().getAttribute("str");
        if(str == null){
           request.getSession().setAttribute("str","");
        }
        if(idxId==null){
            idxId = UUID.randomUUID().toString().replaceAll("-", "");
            Cookie cookie = new Cookie("idxId", idxId);
            cookie.setPath("/");
            cookie.setMaxAge(24*60*60);
            response.addCookie(cookie);
            request.getSession().setAttribute("idxId", idxId);
        }

        return true;
    }

<<<<<<< Updated upstream
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
=======


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

>>>>>>> Stashed changes
        response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
    }
}
