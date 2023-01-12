package cg.park.board_2022.comm.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpRequestHelper {

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getCurrentResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
    }

    public static HttpSession getCurrentSession() {
        return getCurrentRequest().getSession();
    }

    /**
     * 접속자 IP정보를 가져온다.
     * @return
     */
    public static String getRequestIp() {
        String accessIp = getCurrentRequest().getHeader("X-Forwarded-For");
        return (null == accessIp)
                ? getCurrentRequest().getRemoteAddr()
                : accessIp;
    }
}
