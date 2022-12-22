package cg.park.board_2022.comm.auth;

import cg.park.board_2022.comm.annotation.RequireLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            RequireLogin annotation = hm.getMethodAnnotation(RequireLogin.class);

//        String sToken = Utils.getCookie(request, MemberConsts.COOKIE_SSO_TOKEN);

        }
        return true;
    }
}
