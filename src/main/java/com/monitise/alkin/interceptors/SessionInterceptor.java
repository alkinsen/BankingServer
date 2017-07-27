package com.monitise.alkin.interceptors;

import com.monitise.alkin.core.Constants;
import com.monitise.alkin.core.MessageUtil;
import com.monitise.alkin.exceptions.ForbiddenAccessException;
import com.monitise.alkin.exceptions.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor{

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession httpSession = httpServletRequest.getSession();

        if(httpSession.isNew() || httpSession.getAttribute(Constants.USER_ID) == null){
            throw new ForbiddenAccessException(messageUtil.getMessage("err.msg.forbidden.access"));
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
