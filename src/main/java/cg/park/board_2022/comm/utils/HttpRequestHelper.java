package cg.park.board_2022.comm.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpRequestHelper {

    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        return sra.getRequest();
    }

    public static HttpServletResponse getCurrentResponse() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        return sra.getResponse();
    }

    public static HttpSession getCurrentSession() {
        return getCurrentRequest().getSession();
    }

    /**
     * 접속자 IP정보를 가져온다.
     * @return
     */
    public static String getRequestIp() {
        String _access_ip = getCurrentRequest().getHeader("X-Forwarded-For");
        if(_access_ip  == null){
            _access_ip = getCurrentRequest().getRemoteAddr();
        }

        return _access_ip;
    }
}
