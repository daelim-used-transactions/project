package com.daelim.transactions.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

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
}
