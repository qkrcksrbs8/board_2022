package cg.park.board_2022.comm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Component
public class CookieUtil {

    public static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    /**
     * key &amp; value 쌍을 쿠키에 저장
     *
     * @param response HttpServletResponse
     * @param key 쿠키에 담을 key
     * @param val 쿠키에 담을 value
     */
    public static void setCookie(HttpServletResponse response, String key, String val) {
        setCookie(response, key, val, null);
    }

    /**
     * key &amp; value 쌍을 쿠키에 저장
     *
     * @param response HttpServletResponse
     * @param key 쿠키에 담을 key
     * @param val 쿠키에 담을 value
     * @param domain 쿠키 domain
     */
    public static void setCookie(HttpServletResponse response, String key, String val, String domain) {
        setCookie(response, key, val, domain, -1);
    }

    /**
     * key &amp; value 쌍을 쿠키에 저장
     *
     * @param response HttpServletResponse
     * @param key 쿠키에 담을 key
     * @param val 쿠키에 담을 value
     * @param domain 쿠키 domain
     * @param expiry expiry time
     */
    public static void setCookie(HttpServletResponse response, String key, String val, String domain, int expiry) {
        try {
            if (val == null) return;

            Cookie cookie = new Cookie(key, URLEncoder.encode(val.replaceAll("\r", "").replaceAll("\n", ""), "UTF-8"));
            cookie.setPath("/");
            cookie.setMaxAge(expiry);

            if (domain != null)
                cookie.setDomain(domain);

            response.addCookie(cookie);
        } catch (NullPointerException neo) {
            logger.debug("Utils setCookie NullPointerException : " + neo);
        } catch (Exception e) {
            logger.debug("Utils setCookie Exception : " + e);
        }
    }

    /**
     * 쿠키에서 해당 key의 value를 가져옮.
     *
     * @param request HttpServletRequest
     * @param key 쿠키에서 가져올 key
     * @return 해당 key의 value
     */
    public static String getCookie(HttpServletRequest request, String key) {
        try {
            Cookie cookies[] = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(key)) {
                    return URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
            }
        } catch (Exception e) {
            logger.debug("Utils getCookie Exception");
            return "";
        }

        return "";
    }

    /**
     * 쿠키에서 지정 key를 삭제
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param key 삭제할 키
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie acookie[] = request.getCookies();
        for (int i = 0; i < acookie.length; i++) {
            String filteredName = acookie[i].getName().replaceAll("\r", "").replaceAll("\n", "");
            Cookie cookie = new Cookie(filteredName, "");

            if (cookie.getName().equals(key)) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

}
